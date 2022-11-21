<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import {
  PaginationQuery,
  addUser,
  byIddeleteUser,
  putUser,
  byIdgetUser,
  getAuthority,
  batchRoleAuthoritys,
  RoleAuthoritysPaged
} from '@/api/role'
import { Dialog } from '@/components/Dialog'
import {
  ElButton,
  ElForm,
  ElFormItem,
  ElInput,
  ElMessage,
  ElMessageBox,
  ElPagination,
  ElTree
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import { tranListToTreeData } from '@/utils/tree'
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
let tabledata: any = ref('')

// 弹窗标题
const dialogTitle = ref('添加角色')
// 点击编辑，点击分配权限的 id 存放
const editactionid = ref('')
// 表单的实例
const diaLogForm = ref<FormInstance>()
const permTree = ref() // eltree树形

// 添加角色参数
const addUserdata: any = reactive({
  name: ''
})

// 分配权限复选框最新的权限id列表
const newtcheckedIdLIst: any = ref()
// 所有权限列表数据
let jurisdictionList: any = ref('')
// 批量添加参数
let batchParameter: any[] = []

// 角色权限表分页筛选参数
let RoleParameter = ref({
  condition: {
    roleId: 1
  },
  current: 1,
  size: 9999
})
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
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
_PaginationQuery() // 刷新列表

// 添加角色按钮
const tianjiajiekoubtn = () => {
  dialogTitle.value = '添加角色'
  // console.log(111)
  dialogVisible.value = true
}
// 编辑角色按钮
const editaction = async (row) => {
  Assign.value = false
  dialogTitle.value = '编辑角色'
  // console.log(row.id)
  editactionid.value = row.id
  dialogVisible.value = true
  const { data: res } = await byIdgetUser(row.id)
  addUserdata.name = res.name
}
// 角色拥有的权限列表id列表，用于树形显示
let tcheckedIdLIst: any = ref([])
// 分配权限按钮
const Assign = ref(false)
const AssignPermissions = async (row) => {
  Assign.value = true // 显示分配权限表单
  dialogTitle.value = '分配权限'
  // console.log(row.id)
  editactionid.value = row.id // 当前项id
  dialogVisible.value = true // 是否显示弹窗
  // 请求权限列表
  const res = await getAuthority()
  const RoleResList = res.data
  // console.log(RoleResList)
  // 给权限加一个state选中状态
  RoleResList.forEach((e) => {
    e.state = false
  })

  // 设置请求角色权限分页筛选的id参数
  RoleParameter.value.condition.roleId = row.id
  // console.log(RoleParameter.value)
  // // 请求角色权限表分页筛选
  const { data: res2 } = await RoleAuthoritysPaged(RoleParameter.value)
  // console.log(res2.data)
  const PagedresList = res2.data // 当前角色的角色权限列表
  // console.log(PagedresList)
  PagedresList.forEach((e: any) => {
    // console.log(e.authorityId) // 角色拥有的权限列表id
    tcheckedIdLIst.value.push(e.authorityId)
  })

  // 把数组修改为树形
  const RoleResList2 = tranListToTreeData(RoleResList, 0)
  jurisdictionList.value = RoleResList2
}
// 添加角色函数
const _addUser = async (data) => {
  await addUser(data)
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
      const res: any = await byIddeleteUser(row.id)
      ElMessage({
        message: res.message,
        type: 'success'
      })
    }
  } catch (error: any) {
    ElMessage.error(error)
  }
  _PaginationQuery() // 跟新列表
}

// 关闭弹窗
const dclose = (formEl?: FormInstance | undefined) => {
  Assign.value = false // 还原是否是分配权限
  tcheckedIdLIst.value = [] // 重置树形的选中项
  dialogVisible.value = false
  // 重置表单
  if (!formEl) return
  formEl.resetFields()
  addUserdata.name = ''
}

// 点击复选框后的事件
const demonodeclick = () => {
  // 点击复选框后最新选中的权限id列表
  newtcheckedIdLIst.value = permTree.value.getCheckedKeys()
  // console.log(newtcheckedIdLIst.value)
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
          } catch (error: any) {
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
        dclose()
        _PaginationQuery() // 刷新列表
      } else {
        return false
      }
    })
  } else {
    // 确定分配权限
    // 给角色分配权限参数赋值
    // 判断有没有点击复选框
    if (newtcheckedIdLIst.value) {
      // console.log(newtcheckedIdLIst.value)
      newtcheckedIdLIst.value.forEach((element) => {
        batchParameter.push({
          authorityId: element,
          roleId: editactionid.value // 角色id
        })
      })
      console.log(batchParameter)
    } else {
      ElMessage({
        message: '数据没有变更!',
        type: 'error'
      })
      dclose()
    }

    try {
      const res: any = await batchRoleAuthoritys(batchParameter)
      if (res.state) {
        ElMessage({
          message: res.message,
          type: 'success'
        })
      }
    } catch (error) {
      console.log(error)
    }
    // 关闭弹窗  刷新列表
    dclose()
    batchParameter = []
    _PaginationQuery() // 刷新列表
  }
}

// 树形的显示
const defaultProps = {
  children: 'children',
  label: 'title'
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="success" v-hasPermission="['sys:role:add']" @click="tianjiajiekoubtn"
        >添加角色</ElButton
      >
      <ElButton v-hasPermission="['sys:role:delete']" type="danger">
        <Icon icon="fluent:delete-28-regular" />删除</ElButton
      >
    </div>
    <Table :columns="columns" :data="tabledata">
      <template #action="{ row }">
        <ElButton
          type="success"
          v-hasPermission="['sys:role:update']"
          @click="AssignPermissions(row)"
        >
          授权
        </ElButton>
        <ElButton type="danger" v-hasPermission="['sys:role:delete']" @click="deleteaction(row)">
          删除
        </ElButton>
        <ElButton type="primary" v-hasPermission="['sys:role:update']" @click="editaction(row)">
          编辑
        </ElButton>
      </template>
    </Table>
    <el-pagination
      v-model:currentPage="currentPage"
      :page-sizes="[10, 50, 100, 400]"
      :small="small"
      :disabled="disabled"
      :background="background"
      style="margin-top: 20px"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      :page-size="Paginationdata.size"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialogVisible"
    :title="dialogTitle"
    maxHeight="60%"
    style="width: 40%; min-width: 375px; max-width: 600px"
    @closed="dclose"
  >
    <!-- 分配权限表单 -->
    <el-form ref="diaLogForm" :model="addUserdata" v-if="Assign" label-width="80px">
      <el-form-item label="分配权限" prop="name">
        <el-tree
          ref="permTree"
          :data="jurisdictionList"
          default-expand-all
          show-checkbox
          check-on-click-node
          node-key="id"
          :default-checked-keys="tcheckedIdLIst"
          :props="defaultProps"
          @check="demonodeclick"
        />
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
      <ElButton type="primary" style="margin-left: 38%" @click="save(diaLogForm)"> 确定 </ElButton>
      <el-button @click="dclose(diaLogForm)">关闭</el-button>
    </template>
  </Dialog>
</template>
