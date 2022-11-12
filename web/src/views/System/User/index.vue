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
  pagedSearches
} from '@/api/user'
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
  ElPagination
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import Write from './components/Write.vue'
import { values } from 'lodash'
import { number } from 'vue-types'
import { useCache } from '@/hooks/web/useCache'

const { wsCache } = useCache('localStorage')

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
    field: 'username',
    label: '用户名'
  },
  {
    field: 'name',
    label: 'name'
  },
  {
    field: 'nickname',
    label: '昵称'
  },
  {
    field: 'age',
    label: '年龄'
  },
  {
    field: 'sex',
    label: '性别'
  },
  {
    field: 'createTime',
    label: '创建时间'
  },
  {
    field: 'action',
    width: '160px',
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
const dialogTitle = ref('添加用户')
// 点击编辑，id存放
const editactionid = ref('')
// 表单的实例
const diaLogForm = ref<FormInstance>()
// 添加用户参数
const addUserdata = reactive({
  age: 18,
  email: '',
  id: 0,
  name: '',
  nickname: '',
  password: '',
  roleId: null,
  sex: 0,
  username: ''
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

// 添加用户按钮
const tianjiajiekoubtn = () => {
  dialogTitle.value = '添加用户'
  // console.log(111)
  dialogVisible.value = true
}
// 编辑用户按钮
const editaction = async (row) => {
  dialogTitle.value = '编辑用户'
  // console.log(row.id)
  editactionid.value = row.id
  dialogVisible.value = true
  const { data: res } = await byIdgetUser(row.id)
  addUserdata.age = res.age
  addUserdata.username = res.username
  addUserdata.email = res.email
  addUserdata.roleId = res.roleId
  addUserdata.nickname = res.nickname
  addUserdata.sex = res.sex
}
// 添加用户函数
const _addUser = async (data) => {
  const res = await addUser(data)
  // console.log(res)
}
// 删除用户按钮
const deleteaction = async (row) => {
  try {
    const res = await ElMessageBox.confirm('确定要删除此用户吗？该操作将不可恢复！', '提示', {
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

// 关闭弹窗
const close = (formEl: FormInstance | undefined) => {
  dialogVisible.value = false
  // 重置表单
  if (!formEl) return
  formEl.resetFields()
  addUserdata.age = 18
  addUserdata.username = ''
  addUserdata.password = ''
  addUserdata.email = ''
  addUserdata.roleId = null
  addUserdata.nickname = ''
  addUserdata.sex = 0
}
// 点击保存按钮
const save = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      // 表单验证通过
      if (dialogTitle.value === '添加用户') {
        try {
          await _addUser(addUserdata)
          ElMessage({
            message: '添加用户成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      } else {
        // 编辑
        console.log('点击了编辑的按钮')
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
}

// 搜索功能
const inputUsername = ref(null)
const inputName = ref(null)
const inputAge = ref(null)
// 筛选的参数
const dataSearchesArr = ref({
  condition: [],
  current: 1, // 页码
  size: 999 // 返回多少条数
})

// 筛选搜索函数
const _pagedsearches = async (data) => {
  const resseach = await pagedSearches(data)
  console.log(resseach)
  if (resseach.data.length !== 0) {
    // 查询到数据
    let newreslist2 = resseach.data.data
    newreslist2.forEach((e) => {
      if (e.sex === 0) {
        e.sex = '男'
      } else {
        e.sex = '女'
      }
    })
    tabledata.value = newreslist2
    total.value = resseach.data.total
  }
}

// 点击查询按钮
const inputBlur = async () => {
  // if(dataSearchesArr.value.)
  if (inputUsername.value) {
    dataSearchesArr.value.condition[0] = {
      field: 'username',
      keyword: inputUsername.value
    }
  }
  if (inputName.value) {
    if (dataSearchesArr.value.condition[0] == null) {
      dataSearchesArr.value.condition[0] = {
        field: 'name',
        keyword: inputName.value
      }
    } else {
      dataSearchesArr.value.condition[1] = {
        field: 'name',
        keyword: inputName.value
      }
    }
  }
  if (inputAge.value) {
    if (dataSearchesArr.value.condition[0] == null) {
      dataSearchesArr.value.condition[0] = {
        field: 'age',
        keyword: inputAge.value
      }
    } else if (dataSearchesArr.value.condition[1] == null) {
      dataSearchesArr.value.condition[1] = {
        field: 'age',
        keyword: inputAge.value
      }
    } else {
      dataSearchesArr.value.condition[2] = {
        field: 'age',
        keyword: inputAge.value
      }
    }
  }
  if (dataSearchesArr.value.condition.length === 0) {
    _PaginationQuery() //跟新列表
    ElMessage({
      message: '你好像没有输入数据！',
      type: 'error'
    })
    return
  }
  // 发起请求
  _pagedsearches(dataSearchesArr.value)
  ElMessage({
    message: '请求成功！',
    type: 'success'
  })
  dataSearchesArr.value.condition = []
  console.log(dataSearchesArr.value.condition)
}

// 请求路由列表
const res8 = wsCache.get('screenRouters')
console.log(res8)
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">添加用户</ElButton>
      <ElButton :loading="delLoading" type="danger">删除</ElButton>
    </div>
    <div class="mb-10px">
      <el-form :inline="true" label-width="55px" size="large">
        <el-form-item label="用户名">
          <el-input
            style="width: 230px"
            v-model="inputUsername"
            class="w-50 m-2"
            size="small"
            placeholder="username"
          />
        </el-form-item>
        <el-form-item label="name">
          <el-input
            style="width: 230px"
            v-model="inputName"
            class="w-50 m-2"
            size="small"
            placeholder="name"
          />
        </el-form-item>
        <el-form-item label="年龄">
          <el-input
            style="width: 230px"
            v-model="inputAge"
            class="w-50 m-2"
            size="small"
            placeholder="age"
          />
        </el-form-item>
        <el-form-item>
          <el-button size="small" type="primary" @click="inputBlur"
            ><svg
              t="1668148758475"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="5802"
              width="16"
              height="16"
            >
              <path
                d="M869.2 856.9L734.6 722.3c0.6-0.6 1.3-1.2 1.9-1.9 31.6-31.6 56.4-68.4 73.8-109.4 18-42.4 27.1-87.5 27.1-134s-9.1-91.5-27.1-134c-17.3-41-42.2-77.8-73.8-109.4-31.6-31.6-68.4-56.4-109.4-73.8-42.4-18-87.5-27.1-134-27.1s-91.5 9.1-134 27.1c-41 17.3-77.8 42.2-109.4 73.8S193.3 302 175.9 343c-18 42.4-27.1 87.5-27.1 134s9.1 91.5 27.1 134c17.3 41 42.2 77.8 73.8 109.4s68.4 56.4 109.4 73.8c42.4 18 87.5 27.1 134 27.1s91.5-9.1 134-27.1c27.8-11.8 53.7-27 77.4-45.4l136.4 136.4c3.9 3.9 9 5.9 14.1 5.9s10.2-2 14.1-5.9c7.9-7.8 7.9-20.4 0.1-28.3z m-376.1-75.6c-167.7 0-304.2-136.5-304.2-304.2s136.5-304.2 304.2-304.2 304.2 136.5 304.2 304.2-136.4 304.2-304.2 304.2z"
                p-id="5803"
                fill="#ffffff"
              /></svg
            >查询</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <Table :columns="columns" :data="tabledata">
      <template #action="{ row }">
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
      style="margin-top: 20px"
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
    style="width: 40%; min-width: 375px; max-width: 600px"
  >
    <!-- 表单 -->
    <el-form ref="diaLogForm" :model="addUserdata" label-width="65px">
      <el-form-item
        label="用户名"
        prop="username"
        :rules="[{ required: true, message: '用户名不能为空！' }]"
      >
        <el-input v-model="addUserdata.username" autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="密码"
        prop="password"
        :rules="[{ required: true, message: '密码不能为空！' }]"
      >
        <el-input v-model="addUserdata.password" autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="角色"
        prop="roleId"
        :rules="[{ required: true, message: '请选择角色！' }]"
      >
        <!-- <el-input v-model="addUserdata.roleId" autocomplete="off" /> -->
        <el-select v-model="addUserdata.roleId" placeholder="请选择角色" @focus="selectFocus">
          <el-option v-for="item in RoleList" :key="item.id" :label="item.name" :value="item.id">
            <span style="float: left">{{ item.name }}</span>
            <!-- <span style="float: right; color: var(--el-text-color-secondary); font-size: 13px">{{
              item.id
            }}</span> -->
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item
        label="昵称"
        prop="nickname"
        :rules="[{ required: true, message: '请输入昵称！' }]"
      >
        <el-input v-model="addUserdata.nickname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="addUserdata.age" autocomplete="off" /> </el-form-item
      ><el-form-item label="email" prop="email">
        <el-input v-model="addUserdata.email" autocomplete="off" />
      </el-form-item>
      <el-radio-group v-model="addUserdata.sex">
        <el-radio :label="0">男</el-radio>
        <el-radio :label="1">女</el-radio>
      </el-radio-group>
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
<style scoped></style>