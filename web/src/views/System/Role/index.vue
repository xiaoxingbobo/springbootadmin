<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import { addRole, putRole, getRole, getRoleList, batchDeleteRole } from '@/api/role'
import { Dialog } from '@/components/Dialog'
import Write from './components/Write.vue'
import { ElButton } from 'element-plus'
import { Search } from '@/components/Search'
import { useCrudSchemas } from '@/hooks/web/useCrudSchemas'
import { useI18n } from '@/hooks/web/useI18n'
import { useTable } from '@/hooks/web/useTable'
import { unref } from 'vue'
import { Role } from '@/api/role/types'
import WriteRoleAuthority from './components/WriteRoleAuthority.vue'
import { getRoleAuthorityByRoleId } from '@/api/roleAuthority'
import { getAllAuthority } from '@/api/authority'
import type { Authority } from '@/api/authority/types'
import { batchRoleAuthority } from '@/api/roleAuthority'

const dialog = ref({
  visiable: false,
  title: '添加'
})

const dialogRoleAuthority = ref({
  visiable: false,
  title: '授权'
})

const { t } = useI18n()

const { register, tableObject, methods } = useTable<Role>({
  getListApi: getRoleList,
  delListApi: batchDeleteRole,
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
    field: 'name',
    label: '角色名称',
    search: {
      show: true
    }
  },
  {
    field: 'createTime',
    label: '创建时间',
    search: {
      show: false
    },
    form: {
      show: false
    }
  },
  {
    field: 'updateTime',
    label: '修改时间',
    search: {
      show: false
    },
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

const data = ref<Role>()

const loading = ref(false)

/**
 * 删除
 * @param row
 * @param multiple
 */
const delData = async (row: Role | null, multiple: boolean) => {
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
const editData = async (row?: Role) => {
  dialog.value.visiable = true
  //修改
  if (row) {
    tableObject.currentRow = row as Role
    dialog.value.title = '修改'
    const { data: res } = await getRole(row.id?.toString())
    data.value = res
  } else {
    tableObject.currentRow = null
    dialog.value = {
      visiable: true,
      title: t('exampleDemo.add')
    }
  }
}

/**
 * 获取所有权限
 */
const authorityList = ref<Authority[]>([])
const currentAuthorityList = ref<Authority[]>([])
const selectedKeys = ref<number[]>([])

/**
 * 授权
 * @param row
 */
const editRoleAuthorityData = async (row?: Role) => {
  dialogRoleAuthority.value.visiable = true
  //修改
  if (row) {
    tableObject.currentRow = row as Role
    dialogRoleAuthority.value.title = '授权'
    const { data: res } = await getRoleAuthorityByRoleId(row.id) //获取当前row的权限
    currentAuthorityList.value = res
    const { data: res2 } = await getAllAuthority()
    authorityList.value = res2.data // Extract the data array from the response
    selectedKeys.value = res.map((v) => v.authorityId)
  }
}
const writeRef = ref<ComponentRef<typeof Write>>()
const writeRoleAuthorityRef = ref<ComponentRef<typeof WriteRoleAuthority>>()

/**
 * 保存数据
 */
const saveData = async () => {
  const write = unref(writeRef)
  await write?.elFormRef?.validate(async (isValid) => {
    if (isValid) {
      loading.value = true
      const data = (await write?.getFormData()) as Role
      if (data.id) {
        await putRole(data)
      } else {
        await addRole(data)
      }
      loading.value = false
      dialog.value.visiable = false
    }
    methods.getList()
  })
}

/**
 * 保存授权
 */
const saveRoleAuthorityData = async () => {
  const write = unref(writeRoleAuthorityRef)
  console.log(write)
  const selectedKeys = await write?.getSelectedKeys()
  if (selectedKeys) {
    await batchRoleAuthority(
      selectedKeys.map((v) => ({ authorityId: v, roleId: tableObject.currentRow?.id }))
    ).then(() => {
      dialogRoleAuthority.value.visiable = false
    })
  }
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
        <ElButton
          type="success"
          v-hasPermission="['sys:sysUser:update']"
          @click="editRoleAuthorityData(row)"
        >
          {{ t('permission.permission') }}
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
  <!-- 授权弹窗 -->
  <Dialog
    v-model="dialogRoleAuthority.visiable"
    :title="dialogRoleAuthority.title"
    maxHeight="60%"
    @closed="dialogRoleAuthority.visiable = false"
    style="width: 40%; min-width: 375px; max-width: 600px"
  >
    <WriteRoleAuthority
      ref="writeRoleAuthorityRef"
      :authority-list="authorityList"
      :selected-keys="selectedKeys"
    />

    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" @click="saveRoleAuthorityData">
        {{ t('dialogDemo.submit') }}
      </ElButton>
      <el-button @click="dialogRoleAuthority.visiable = false">{{
        t('dialogDemo.close')
      }}</el-button>
    </template>
  </Dialog>
</template>
<style scoped></style>
