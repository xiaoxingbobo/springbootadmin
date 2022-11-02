<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { h, ref, unref, reactive, onMounted } from 'vue'
import { Table } from '@/components/Table'
import { PaginationQuery, addUser } from '@/api/user'
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
    field: 'nickname',
    label: '昵称'
  },
  {
    field: 'name',
    label: '角色'
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
    field: 'id',
    label: 'id'
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
  name: '',
  nickname: '',
  password: '123456',
  roleId: 0,
  sex: 0,
  username: 'xingyue888'
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
  tabledata.value = res.data
  total.value = res.total
  console.log(total.value)
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
let _PageSize = ref(2)
_PaginationQuery()

// 添加用户按钮
const tianjiajiekoubtn = () => {
  // dialogTitle.value = '添加用户'
  console.log(111)
  dialogVisible.value = true
}
// 添加用户函数
const _addUser = async () => {
  const res = await addUser(addUserdata)
  console.log(res)
}

// 关闭弹窗
const close = () => {
  dialogVisible.value = false
}
// 点击保存按钮
const save = async () => {
  const res = await addUser(addUserdata)
  console.log(res)
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">添加用户</ElButton>
      <ElButton :loading="delLoading" type="danger">删除</ElButton>
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
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      page-size="Paginationdata.size"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog v-model="dialogVisible" :title="dialogTitle" maxHeight="200px" style="width: 40%">
    <!-- 表单 -->
    <el-form ref="diaLogForm" :model="addUserdata">
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
        <el-input v-model="addUserdata.roleId" autocomplete="off" />
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
        <el-input v-model="addUserdata.email" autocomplete="off" /> </el-form-item
      ><el-form-item label="性别" prop="sex">
        <el-input v-model="addUserdata.sex" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" :loading="loading" @click="save">
        确定
      </ElButton>
      <el-button @click="close">关闭</el-button>
    </template>
  </Dialog>
</template>
