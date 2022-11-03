<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { h, ref, unref, reactive, onMounted, toRefs } from 'vue'
import { Table } from '@/components/Table'
import {
  PaginationQuery,
  addUser,
  byIddeleteUser,
  putUser,
  byIdgetUser,
  getRole,
  getAuthority,
  batchRoleAuthoritys
} from '@/api/role'
import { Dialog } from '@/components/Dialog'
import {
  ElButton,
  ElTag,
  ElForm,
  ElFormItem,
  ElInput,
  ElSelect,
  ElOption,
  ElRadioGroup,
  ElRadio,
  ElMessage,
  ElMessageBox,
  ElTabs,
  ElTabPane,
  ElRow,
  ElCol,
  ElPagination,
  ElCheckbox
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import Write from './components/Write.vue'
import { values } from 'lodash'
import { number } from 'vue-types'

// 是否显示弹窗
const dialogVisible = ref(false)

// 表头
const columns = reactive<TableColumn[]>([
  {
    field: 'index',
    label: '序号',
    type: 'index'
  },
  {
    field: 'name',
    label: '角色名称'
  },
  {
    field: 'createTime',
    label: '创建时间'
  },
  {
    field: 'action',
    width: '260px',
    label: '操作',
    form: {
      show: false
    },
    detail: {
      show: false
    }
  }
])

//  user列表数据
let tabledata = ref('')

// 是否覆盖
const isCover = ref('true')
// 弹窗标题
const dialogTitle = ref('添加角色')
// 点击编辑，点击分配权限的 id 存放
const editactionid = ref('')
// 表单的实例
const diaLogForm = ref<FormInstance>()
// 添加角色参数
const addUserdata = reactive({
  name: ''
})
// 分配权限
const checked1 = ref(true)
const checked2 = ref(false)
// 所有权限列表数据
let jurisdictionList = ref('')
// 批量添加参数
let batchParameter: any[] = []
// 表格分页
let total = ref(0)
// 分页数据
let Paginationdata: {
  current: number
  size: number
} = {
  // 当前页
  current: 1,
  // 每页条数
  size: 10
}
//  全部角色信息数据
let RoleList = ref('')
// 选择角色信息获得焦点
const selectFocus = async () => {
  const res = await getRole()
  RoleList.value = res.data
  // console.log(res.data)
}
// 分页查询函数
const _PaginationQuery = async () => {
  const { data: res } = await PaginationQuery(Paginationdata)
  let newreslist = res.data
  newreslist.forEach((e) => {
    if (e.sex === 0) {
      e.sex = '男'
    } else {
      e.sex = '女'
    }
  })
  tabledata.value = newreslist
  total.value = res.total
  // console.log(total.value)
}
// 修改每页显示多少条数
const handleSizeChange = (val: number) => {
  Paginationdata.size = val
  // console.log(Paginationdata.size)
  _PaginationQuery() // 跟新列表
}
// 切换到某页
const handleCurrentChange = (val: number) => {
  Paginationdata.current = val
  _PaginationQuery() // 跟新列表
}

const currentPage = ref(1)
const pageSize = ref(10)
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 每页显示数目
let _PageSize = ref(10)
_PaginationQuery() // 刷新列表

// 添加角色按钮
const tianjiajiekoubtn = () => {
  dialogTitle.value = '添加角色'
  // console.log(111)
  dialogVisible.value = true
}
// 编辑角色按钮
const editaction = async (row) => {
  dialogTitle.value = '编辑角色'
  // console.log(row.id)
  editactionid.value = row.id
  dialogVisible.value = true
  const { data: res } = await byIdgetUser(row.id)
  addUserdata.name = res.name
}

// 分配权限按钮
const Assign = ref(false)
const AssignPermissions = async (row) => {
  Assign.value = true // 显示分配权限表单
  dialogTitle.value = '分配角色'
  // console.log(row.id)
  editactionid.value = row.id
  dialogVisible.value = true // 是否显示弹窗
  // 请求权限列表
  const res = await getAuthority()
  const RoleResList = res.data
  // 给权限加一个state选中状态
  RoleResList.forEach((e) => {
    e.state = false
    // console.log(e)
  })
  jurisdictionList.value = RoleResList
  // console.log(RoleResList)
}
// 添加角色函数
const _addUser = async (data) => {
  const res = await addUser(data)
  // console.log(res)
}
// 删除角色按钮
const deleteaction = async (row) => {
  try {
    const res = await ElMessageBox.confirm('确定要删除此角色吗？该操作将不可恢复！', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    // 点击了确定
    if (res === 'confirm') {
      const res = await byIddeleteUser(row.id)
      ElMessage({
        message: res.message,
        type: 'success'
      })
    }
  } catch (error) {
    ElMessage.error(error)
  }
  _PaginationQuery() // 跟新列表
}

//
// list=
// const isTrue = (id) => {
//   list.array.forEach((element) => {
//     if (element.authorId == id) {
//       return true
//     }
//   })
//   return false
// }

// 关闭弹窗
const close = (formEl: FormInstance | undefined) => {
  dialogVisible.value = false
  // 重置表单
  if (!formEl) return
  formEl.resetFields()
  addUserdata.name = ''
  Assign.value = false // 还原是否是分配权限
}
// 点击保存按钮
const save = async (formEl: FormInstance | undefined) => {
  if (Assign.value === false) {
    // 添加和编辑
    if (!formEl) return
    formEl.validate(async (valid) => {
      if (valid) {
        // 表单验证通过
        if (dialogTitle.value === '添加角色') {
          try {
            await _addUser(addUserdata)
            ElMessage({
              message: '添加角色成功!',
              type: 'success'
            })
          } catch (error) {
            ElMessage.error(error)
          }
        } else {
          // 编辑
          try {
            addUserdata.id = editactionid.value
            // console.log(addUserdata.id)
            await putUser(addUserdata)
            ElMessage({
              message: '操作成功!',
              type: 'success'
            })
          } catch (error) {}
        }

        // 关闭弹窗  刷新列表
        close()
        _PaginationQuery() // 刷新列表
      } else {
        return false
      }
    })
  } else {
    // 分配权限
    jurisdictionList.value.forEach((e) => {
      if (e.state) {
        batchParameter.push({
          authorityId: e.id,
          roleId: editactionid.value
        })
      }
    })
    try {
      const res = await batchRoleAuthoritys(batchParameter)
      if (res.state) {
        ElMessage({
          message: res.message,
          type: 'success'
        })
      }
    } catch (error) {}
    // 关闭弹窗  刷新列表
    close()
    batchParameter = []
    _PaginationQuery() // 刷新列表
  }
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">添加角色</ElButton>
      <ElButton :loading="delLoading" type="danger">删除</ElButton>
    </div>
    <Table :columns="columns" :data="tabledata">
      <template #action="{ row }">
        <ElButton type="primary" :loading="delLoading" @click="AssignPermissions(row)">
          分配权限
        </ElButton>
        <ElButton type="danger" :loading="delLoading" @click="deleteaction(row)"> 删除 </ElButton>
        <ElButton type="primary" :loading="delLoading" @click="editaction(row)"> 编辑 </ElButton>
      </template>
    </Table>
    <el-pagination
      v-model:currentPage="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 50, 100, 400]"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      page-size="Paginationdata.size"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialogVisible"
    :title="dialogTitle"
    maxHeight="60%"
    style="width: 40%; max-width: 600px"
  >
    <!-- 分配权限表单 -->
    <el-form ref="diaLogForm" :model="addUserdata" v-if="Assign" label-width="80px">
      <el-form-item label="分配权限" prop="name">
        <el-checkbox
          v-for="item in jurisdictionList"
          :key="item.id"
          v-model="item.state"
          :label="item.value"
          size="large"
          value
          >{{ item.name }}</el-checkbox
        >
      </el-form-item>
    </el-form>
    <!-- 添加编辑表单 -->
    <el-form ref="diaLogForm" :model="addUserdata" v-else label-width="65px">
      <el-form-item
        label="角色名"
        prop="name"
        :rules="[{ required: true, message: '角色名不能为空！' }]"
      >
        <el-input v-model="addUserdata.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <ElButton
        type="primary"
        style="margin-left: 38%"
        :loading="loading"
        @click="save(diaLogForm)"
      >
        确定
      </ElButton>
      <el-button @click="close(diaLogForm)">关闭</el-button>
    </template>
  </Dialog>
</template>
