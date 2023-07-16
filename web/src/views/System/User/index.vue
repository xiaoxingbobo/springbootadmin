<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import { addUser, putUser, getUser, getRole, getUserList, batchDeleteUser } from '@/api/user'
import { Dialog } from '@/components/Dialog'
import Write from './components/Write.vue'
import { ElButton } from 'element-plus'
import { Search } from '@/components/Search'
import { User } from '@/api/user/types'
import { useCrudSchemas } from '@/hooks/web/useCrudSchemas'
import { useI18n } from '@/hooks/web/useI18n'
import { useTable } from '@/hooks/web/useTable'

/**
 * 获取角色信息
 */
const roleList = ref<Array<any>>([])
const getRoleList = async () => {
  const { data: res } = await getRole()
  res.forEach((e: any) => {
    roleList.value.push({
      label: e.name,
      value: e.id
    })
  })
}
getRoleList()
const dialog = ref({
  visiable: false,
  title: '添加'
})
const { t } = useI18n()

const { register, tableObject, methods } = useTable<User>({
  getListApi: getUserList,
  delListApi: batchDeleteUser,
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
    field: 'username',
    label: '用户名',
    search: {
      show: true
    }
  },
  {
    field: 'name',
    label: '名称',
    search: {
      show: true
    }
  },
  {
    field: 'nickname',
    label: '昵称',
    search: {
      show: true
    }
  },
  {
    field: 'age',
    label: '年龄',
    search: {
      show: true
    }
  },
  {
    field: 'roleId',
    label: '角色',
    search: {
      show: true
    },
    form: {
      component: 'Select',
      componentProps: {
        style: {
          width: '100%'
        },
        options: roleList
      }
    }
  },
  {
    field: 'sex',
    label: '性别',
    search: {
      show: true
    },
    form: {
      component: 'Select',
      value: '0',
      componentProps: {
        style: {
          width: '100%'
        },
        options: [
          {
            label: '男',
            value: '1'
          },
          {
            label: '女',
            value: '0'
          }
        ]
      }
    }
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
    width: '160px',
    label: '操作'
  }
])

const { allSchemas } = useCrudSchemas(columns)
const data = ref<User>({})
// const searches = reactive<Array<SearchType>>([])
// const page = reactive<Page<User>>({
//   current: 1,
//   size: 10,
//   condition: null,
//   searches: searches
// })
const loading = ref(false)

const delLoading = ref(false)

const delData = async (row: User | null, multiple: boolean) => {
  tableObject.currentRow = row
  const selections = await getSelections()
  delLoading.value = true
  await delList(
    multiple ? selections.map((v) => v.id) : [tableObject.currentRow?.id as number],
    multiple
  ).finally(() => {
    delLoading.value = false
  })
}
/**
 * 添加数据或修改数据
 *
 */
const editData = async (row?: User) => {
  getRoleList()
  dialog.value.visiable = true
  if (row) {
    dialog.value.title = '修改'
    const { data: res } = await getUser(row.id?.toString())
    data.value = res
  } else {
    dialog.value.title = '添加'
    dialog.value.visiable = true
  }
}

/**
 * 保存数据
 */
const saveData = async () => {
  if (data.value.id) {
    loading.value = true
    await putUser(data.value)
  } else {
    await addUser(data.value)
  }
  dialog.value.visiable = false
}
/**
 * 搜索
 */
// ;(params: User) => {
//   const searches: Array<SearchType> = []
//   for (const key in params) {
//     if (params[key]) {
//       searches.push({
//         field: key,
//         keyword: params[key]
//       })
//     }
//   }
//   page.searches = searches
// }

const actionType = ref('')
dialog.value.title = t('exampleDemo.add')
const AddAction = () => {
  dialog.value.title = t('exampleDemo.add')
  // tableData.value.currentRow = null
  dialog.value.visiable = true
  actionType.value = ''
}
methods.getList()
</script>

<template>
  <ContentWrap>
    <Search :schema="allSchemas.searchSchema" @search="setSearchParams" />
    <div class="mb-10px">
      <ElButton type="success" v-hasPermission="['sys:sysUser:add']" @click="AddAction()">
        <Icon icon="material-symbols:add" />{{ t('exampleDemo.add') }}
      </ElButton>
      <ElButton type="danger" v-hasPermission="['sys:sysUser:delete']" @click="delData(null, true)">
        <Icon icon="fluent:delete-28-regular" />{{ t('exampleDemo.del') }}
      </ElButton>
    </div>
    <Table
      :columns="columns"
      :data="tableObject.tableList"
      :page-sizes="[10, 20, 50, 100]"
      v-model:pageSize="tableObject.size"
      v-model:currentPage="tableObject.current"
      :row-key="(row) => row.id"
      :selection="true"
      :border="true"
      :loading="loading"
      :stripe="true"
      :pagination="{
        background: true,
        total: tableObject.total
      }"
      @register="register"
    >
      <template #action="{ row }">
        <ElButton
          type="danger"
          plain
          v-hasPermission="['sys:sysUser:delete']"
          @click="delData(row, false)"
        >
          删除
        </ElButton>
        <ElButton type="primary" v-hasPermission="['sys:sysUser:update']" @click="editData(row)">
          编辑
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
    <Write v-if="actionType !== 'detail'" ref="writeRef" :form-schema="allSchemas.formSchema" />
    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" @click="saveData()"> 确定 </ElButton>
      <el-button @click="dialog.visiable = false">关闭</el-button>
    </template>
  </Dialog>
</template>
<style scoped></style>
