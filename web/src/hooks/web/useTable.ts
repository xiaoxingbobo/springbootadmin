import { Table, TableExpose } from '@/components/Table'
import { ElTable, ElMessageBox, ElMessage } from 'element-plus'
import { ref, reactive, watch, computed, unref, nextTick } from 'vue'
import { get } from 'lodash-es'
import type { TableProps } from '@/components/Table/src/types'
import { useI18n } from '@/hooks/web/useI18n'
import { de, el } from 'element-plus/es/locale'

const { t } = useI18n()

interface TableResponse<T = any> {
  total: number
  list: T[]
  pageNumber: number
  size: number
}

interface UseTableConfig<T = any> {
  getListApi: (option: any) => Promise<IResponse<TableResponse<T>>>
  delListApi?: (option: any) => Promise<IResponse>
  // 返回数据格式配置
  response: {
    list: string
    total?: string
  }
  props?: TableProps
}

interface TableObject<T = any> {
  size: number
  current: number
  total: number
  tableList: T[]
  params: any
  loading: boolean
  currentRow: Nullable<T>
}

export const useTable = <T = any>(config?: UseTableConfig<T>) => {
  const tableObject = reactive<TableObject<T>>({
    // 页数
    size: 10,
    // 当前页
    current: 1,
    // 总条数
    total: 10,
    // 表格数据
    tableList: [],
    // AxiosConfig 配置
    params: {},
    // 加载中
    loading: true,
    // 当前行的数据
    currentRow: null
  })

  const paramsObj = computed(() => {
    return {
      ...tableObject.params,
      size: tableObject.size,
      current: tableObject.current
    }
  })

  watch(
    () => tableObject.current,
    () => {
      methods.getList()
    }
  )

  watch(
    () => tableObject.size,
    () => {
      // 当前页不为1时，修改页数后会导致多次调用getList方法
      if (tableObject.current === 1) {
        methods.getList()
      } else {
        tableObject.current = 1
        methods.getList()
      }
    }
  )

  // Table实例
  const tableRef = ref<typeof Table & TableExpose>()

  // ElTable实例
  const elTableRef = ref<ComponentRef<typeof ElTable>>()

  const register = (ref: typeof Table & TableExpose, elRef: ComponentRef<typeof ElTable>) => {
    tableRef.value = ref
    elTableRef.value = unref(elRef)
  }

  const getTable = async () => {
    await nextTick()
    const table = unref(tableRef)
    if (!table) {
      console.error('The table is not registered. Please use the register method to register')
    }
    return table
  }

  const delData = async (ids: string[] | number[] | any) => {
    const res = await (config?.delListApi && config?.delListApi(ids))
    if (res) {
      ElMessage.success(t('common.delSuccess'))

      // 计算出临界点
      const current =
        tableObject.total % tableObject.size === ids.length || tableObject.size === 1
          ? tableObject.current > 1
            ? tableObject.current - 1
            : tableObject.current
          : tableObject.current

      tableObject.current = current
      methods.getList()
    }
  }

  const methods = {
    getList: async () => {
      tableObject.loading = true
      const res = await config?.getListApi(unref(paramsObj)).finally(() => {
        tableObject.loading = false
      })
      if (res) {
        tableObject.tableList = get(res.data || {}, config?.response.list as string)
        tableObject.total = get(res.data || {}, config?.response?.total as string) || 0
      }
    },
    setProps: async (props: TableProps = {}) => {
      const table = await getTable()
      table?.setProps(props)
    },
    setColumn: async (columnProps: TableSetPropsType[]) => {
      const table = await getTable()
      table?.setColumn(columnProps)
    },
    getSelections: async () => {
      const table = await getTable()
      return (table?.selections || []) as T[]
    },
    // 与Search组件结合
    setSearchParams: (data: Recordable) => {
      tableObject.current = 1
      // 把data对象重新修改为{'field':'','value':''}的形式, 去除data[key]为空的,并移除元素
      const arr: any[] = []
      Object.keys(data).forEach((key) => {
        if (data[key]) {
          arr.push({
            field: key,
            keyword: data[key]
          })
        }
      })
      tableObject.params = Object.assign(tableObject.params, {
        size: tableObject.size,
        current: tableObject.current,
        searches: Object.keys(data)
          .map((key) => {
            if (data[key]) {
              return {
                field: key,
                keyword: data[key]
              }
            }
          })
          .filter((item) => item)
      })
      methods.getList()
    },
    // 删除数据
    delList: async (ids: string[] | number[], multiple: boolean, message = true) => {
      const tableRef = await getTable()
      if (multiple) {
        if (!tableRef?.selections.length) {
          ElMessage.warning(t('common.delNoData'))
          return
        }
      } else {
        if (!tableObject.currentRow) {
          ElMessage.warning(t('common.delNoData'))
          return
        }
      }
      if (message) {
        ElMessageBox.confirm(t('common.delMessage'), t('common.delWarning'), {
          confirmButtonText: t('common.delOk'),
          cancelButtonText: t('common.delCancel'),
          type: 'warning'
        }).then(async () => {
          await delData(ids)
        })
      } else {
        await delData(ids)
      }
    }
  }

  config?.props && methods.setProps(config.props)

  return {
    register,
    elTableRef,
    tableObject,
    methods
  }
}
