<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive, unref } from 'vue'
import { Table } from '@/components/Table'
import { Dialog } from '@/components/Dialog'
import Write from './components/Write.vue'
import {
  getAuthority,
  getAuthorityList,
  batchDeleteAuthority,
  putAuthority,
  addAuthority
} from '@/api/authority'
import { useCrudSchemas } from '@/hooks/web/useCrudSchemas'
import { useTable } from '@/hooks/web/useTable'
import { useI18n } from '@/hooks/web/useI18n'
import { ElButton } from 'element-plus'
import { Authority, AuthorityTree } from '@/api/authority/types'

const dialog = ref({
  visiable: false,
  title: '添加'
})
const treeData = ref<any>()

const { t } = useI18n()

const { register, tableObject, methods } = useTable<Authority>({
  getListApi: getAuthorityList,
  delListApi: batchDeleteAuthority,
  response: {
    list: 'data',
    total: 'total'
  }
})

const { delList, getSelections, setSearchParams } = methods

const columns = reactive<TableColumn[]>([
  {
    field: 'index',
    label: '序号',
    type: 'index',
    form: {
      show: false
    }
  },
  {
    field: 'title',
    label: '名称',
    form: {
      show: true
    }
  },
  {
    field: 'value',
    label: '权限值'
  },
  {
    field: 'path',
    label: '路由'
  },
  {
    field: 'icon',
    label: '图标'
  },
  {
    field: 'authorityType',
    label: '菜单类型',
    formatter: ({ authorityType }) => {
      return authorityType === 'API' ? '权限' : '菜单'
    },
    form: {
      component: 'Select',
      componentProps: {
        style: {
          width: '100%'
        },
        options: [
          {
            label: '权限',
            value: 'API'
          },
          {
            label: '菜单',
            value: 'ROUTER'
          }
        ]
      }
    }
  },
  {
    field: 'component',
    label: '组件'
  },

  {
    field: 'createTime',
    label: '创建时间',
    form: {
      show: false
    }
  },
  {
    field: 'action',
    width: '250px',
    label: '操作',
    form: {
      show: false
    }
  }
])

const { allSchemas } = useCrudSchemas(columns)

const data = ref<Authority>()

const loading = ref(false)

/**
 * 删除
 * @param row
 * @param multiple
 */
const delData = async (row: Authority | null, multiple: boolean) => {
  tableObject.currentRow = row
  const selections = await getSelections()
  loading.value = true
  await delList(
    multiple ? selections.map((v) => v.id) : [tableObject.currentRow?.id as number],
    multiple
  ).finally(() => {
    loading.value = false
  })
}

/**
 * 添加或修改数据
 * @param row
 */
const editData = async (row?: Authority) => {
  dialog.value.visiable = true
  //修改
  if (row) {
    tableObject.currentRow = row as Authority
    dialog.value.title = t('exampleDemo.edit')
    const { data: res } = await getAuthority(row.id?.toString())
    data.value = res
  } else {
    tableObject.currentRow = null
    dialog.value = {
      visiable: true,
      title: t('exampleDemo.add')
    }
  }
}

const writeRef = ref<ComponentRef<typeof Write>>()

/**
 * 保存数据
 */
const saveData = async () => {
  const write = unref(writeRef)
  await write?.elFormRef?.validate(async (isValid) => {
    if (isValid) {
      loading.value = true
      const data = (await write?.getFormData()) as Authority
      if (data.id) {
        await putAuthority(data)
      } else {
        await addAuthority(data)
      }
      loading.value = false
      dialog.value.visiable = false
    }
    methods.getList()
  })
}

/**
 * 处理数据
 */
function processData(items: any): AuthorityTree[] {
  // 创建一个Map来存储所有项，方便查找
  const itemMap = new Map<number, AuthorityTree>()

  // 首先将所有项添加到Map中，并初始化children数组
  items.forEach((item: AuthorityTree) => {
    itemMap.set(item.id, { ...item, children: [] })
  })

  // 构建树形结构
  const result: AuthorityTree[] = []

  items.forEach((item: AuthorityTree) => {
    const currentItem = itemMap.get(item.id)
    if (item.parentId === 0) {
      // 如果是顶级节点，直接加入结果数组
      result.push(currentItem!)
    } else {
      // 如果不是顶级节点，找到其父节点并添加到children中
      const parentItem = itemMap.get(item.parentId as number)
      if (parentItem) {
        parentItem.children!.push(currentItem!)
      }
    }
  })

  // 清理空的children数组
  const cleanEmptyChildren = (items: AuthorityTree[]) => {
    items.forEach((item) => {
      if (item.children && item.children.length === 0) {
        delete item.children
      } else if (item.children) {
        cleanEmptyChildren(item.children)
      }
    })
  }

  cleanEmptyChildren(result)
  treeData.value = result
  return result
}
//查询所有数据
tableObject.size = 1000

/**
 * 请求数据
 */
methods.getList()
</script>

<template>
  <ContentWrap>
    <Search :schema="allSchemas.searchSchema" @search="setSearchParams" />
    <div class="mb-10px">
      <ElButton type="success" v-hasPermission="['sys:sysUser:add']" @click="editData()">
        <Icon icon="material-symbols:add" />{{ t('exampleDemo.add') }}
      </ElButton>
    </div>
    <Table
      :columns="columns"
      :data="processData(tableObject.tableList)"
      v-model:pageSize="tableObject.size"
      v-model:currentPage="tableObject.current"
      :row-key="(row) => row.id"
      :border="true"
      :loading="loading"
      :stripe="true"
      @register="register"
    >
      <template #action="{ row }">
        <ElButton
          type="danger"
          plain
          v-hasPermission="['sys:sysUser:delete']"
          @click="delData(row, false)"
        >
          {{ t('exampleDemo.del') }}
        </ElButton>
        <ElButton type="primary" v-hasPermission="['sys:sysUser:update']" @click="editData(row)">
          {{ t('exampleDemo.edit') }}
        </ElButton>
      </template>
    </Table>
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialog.visiable"
    :title="dialog.title"
    maxHeight="60%"
    @closed="dialog.visiable = false"
    style="width: 40%; min-width: 375px; max-width: 600px"
  >
    <Write
      ref="writeRef"
      :form-schema="allSchemas.formSchema"
      :current-row="tableObject.currentRow"
      :tree-data="treeData"
    />

    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" @click="saveData()">
        {{ t('dialogDemo.submit') }}
      </ElButton>
      <el-button @click="dialog.visiable = false">{{ t('dialogDemo.close') }}</el-button>
    </template>
  </Dialog>
</template>
