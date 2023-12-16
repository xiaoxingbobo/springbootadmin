<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import { addUser, putUser, getUser, getRoleAll, getUserList, batchDeleteUser } from '@/api/user'
import { Dialog } from '@/components/Dialog'
import Write from './components/Write.vue'
import { ElButton, ElMessage } from 'element-plus'
import { Search } from '@/components/Search'
import { User } from '@/api/user/types'
import { useCrudSchemas } from '@/hooks/web/useCrudSchemas'
import { useI18n } from '@/hooks/web/useI18n'
import { useTable } from '@/hooks/web/useTable'
import { unref } from 'vue'

/**
 * 获取角色信息
 */
const roleList = ref<Array<any>>([])
const getRoleList = async () => {
  const { data: res } = await getRoleAll()
  roleList.value = []
  res.data.forEach((e: any) => {
    roleList.value.push({
      label: e.name,
      value: e.id
    })
  })
}

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
      value: 0,
      componentProps: {
        style: {
          width: '100%'
        },
        options: [
          {
            label: '男',
            value: 1
          },
          {
            label: '女',
            value: 0
          }
        ]
      }
    }
  },
  {
    field: 'password',
    label: '登录密码',
    search: {
      show: false
    },
    form: {
      component: 'InputPassword',
      show: true
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
    label: '操作',
    form: {
      show: false
    }
  }
])

const { allSchemas } = useCrudSchemas(columns)

const data = ref<User>()

const loading = ref(false)

/**
 * 删除
 * @param row
 * @param multiple
 */
const delData = async (row: User | null, multiple: boolean) => {
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
const editData = async (row?: User) => {
  getRoleList()
  dialog.value.visiable = true
  //修改
  if (row) {
    tableObject.currentRow = row as User
    dialog.value.title = '修改'
    const { data: res } = await getUser(row.id?.toString())
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
      const data = (await write?.getFormData()) as User
      if (data.id) {
        await putUser(data)
        ElMessage.success(t('common.success'))
      } else {
        await addUser(data)
        ElMessage.success(t('common.success'))
      }
      loading.value = false
      dialog.value.visiable = false
    }
    methods.getList()
  })
}

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
    />
    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" @click="saveData()">
        {{ t('dialogDemo.submit') }}
      </ElButton>
      <el-button @click="dialog.visiable = false">{{ t('dialogDemo.close') }}</el-button>
    </template>
  </Dialog>
</template>
<style scoped></style>
