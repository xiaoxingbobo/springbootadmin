<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import {
  pagedQueryUser,
  addUser,
  deleteUser,
  putUser,
  getUser,
  getRole,
  pagedSearchUser
} from '@/api/User'
import { Page } from '@/api/Common/types'
import { Dialog } from '@/components/Dialog'
import {
  ElButton,
  ElForm,
  ElFormItem,
  ElInput,
  ElSelect,
  ElOption,
  ElRadioGroup,
  ElRadio,
  ElMessage,
  ElMessageBox,
  ElPagination
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import { setNull, setValue } from '@/utils/index'
import { User } from '@/api/User/types'
import { SearchField } from '@/api/Common/types'

/**
 * 声明data
 */
// 数据列表
const tableData = ref({
  data: [],
  total: 0,
  currentRow: []
})
const dialog = ref({
  visiable: false,
  title: '添加'
})

// 表单的实例
const diaLogForm = ref<FormInstance>()
//  全部角色信息数据
let roleList: any = ref({})
// 表头
const columns = reactive<TableColumn[]>([
  {
    field: 'index',
    label: '序号',
    type: 'index'
  },
  {
    field: 'username',
    label: '用户名',
    search: true
  },
  {
    field: 'name',
    label: '名称',
    search: true
  },
  {
    field: 'nickname',
    label: '昵称',
    search: true
  },
  {
    field: 'age',
    label: '年龄',
    search: true
  },
  {
    field: 'sex',
    label: '性别',
    search: true
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

// 添加的实体
const userPayload: User = reactive({
  age: 18,
  email: null,
  id: 0,
  name: null,
  nickname: null,
  password: null,
  username: null,
  roleId: null,
  sex: 0
})

// 分页数据
let pagedPlayload: Page = {
  // 当前页
  current: 1,
  // 每页条数
  size: 10
}

// 搜索负载
const searchPlayload: {
  condition: SearchField
  current: number
  size: number
} = {
  condition: [],
  current: pagedPlayload.current,
  size: pagedPlayload.size
}

/**
 * 初始化函数
 */
const initData = async () => {
  //搜索字段清空
  columns.forEach((i) => {
    i.keyword = null
  })
  searchPlayload.condition = []
  const { data: res } = await pagedQueryUser(pagedPlayload)
  res.data.forEach((e) => {
    e.sex === 1 ? (e.sex = '男') : (e.sex = '女')
  })
  tableData.value = { ...res }
}
initData()
/**
 * 切换页大小
 * @param val
 */
const handleSizeChange = () => {
  searchPlayload.size = pagedPlayload.size
  if (searchPlayload.condition.length > 0) {
    pagedSearches(searchPlayload)
  } else {
    initData() // 跟新列表
  }
}
/**
 * 查询所有角色
 */
const queryRole = async () => {
  const sysRole = await getRole()
  roleList.value = sysRole.data
}

/**
 * 添加
 */
const clickAdd = () => {
  dialog.value = {
    title: '添加',
    visiable: true
  }
  queryRole()
}

/**
 * 编辑
 * @param row
 */
const clickEdit = async (row) => {
  userPayload.id = row.id //设置id
  const { data: res } = await getUser(row.id)
  queryRole()
  dialog.value = {
    title: '编辑',
    visiable: true
  }
  setValue(userPayload, res)
}

/**
 * 删除
 * @param row
 */
const clickDelete = async (row) => {
  const res: any = await ElMessageBox.confirm('确定要删除此用户吗？该操作将不可恢复！', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  // 点击了确定
  if (res === 'confirm') {
    const res: any = await deleteUser(row.id)
    ElMessage({
      message: res.message,
      type: 'success'
    })
  }
  initData() // 跟新列表
}

/**
 * 关闭弹窗
 * @param formEl
 */
const clickClose = (formEl: FormInstance | undefined = undefined) => {
  dialog.value.visiable = false
  setNull(userPayload)
  // 重置表单
  if (!formEl) return
  formEl.resetFields()
}
/**
 * 保存
 * @param formEl
 */
const clickSave = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      // 表单验证通过
      if (userPayload.id === null) {
        await addUser(userPayload)
      } else {
        // 编辑
        await putUser(userPayload)
      }
      ElMessage({
        message: '操作成功!',
        type: 'success'
      })
      // 关闭弹窗  刷新列表
      clickClose()
      initData() // 刷新列表
    }
  })
}

/**
 * 分页搜索
 * @param data
 */
const pagedSearches = async (data) => {
  const { data: res } = await pagedSearchUser(data)
  if (res.data.length !== 0) {
    res.data.forEach((e) => {
      e.sex == 1 ? (e.sex = '男') : (e.sex = '女')
    })
    tableData.value = {
      ...res
    }
  }
}
/**
 * 切换页码
 * @param val
 */
const handleCurrentChange = () => {
  if (searchPlayload.condition.length > 0) {
    searchPlayload.current = pagedPlayload.current
    pagedSearches(searchPlayload)
  } else {
    initData() // 跟新列表
  }
}

/**
 * 分页查询
 */
const clickQuery = async () => {
  columns.forEach((i) => {
    if (i.keyword != null) {
      searchPlayload.condition.push(i)
    }
  })

  if (searchPlayload.condition.length === 0) {
    initData()
  } else {
    pagedSearches(searchPlayload)
  }
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="success" v-hasPermission="['sys:sysUser:add']" @click="clickAdd">
        <Icon icon="material-symbols:add" />添加
      </ElButton>
      <ElButton v-hasPermission="['sys:sysUser:delete']" type="danger">
        <Icon icon="fluent:delete-28-regular" />删除
      </ElButton>
    </div>
    <div class="mb-10px">
      <el-form :inline="true" label-width="55px">
        <el-form-item
          :label="item.label"
          v-for="(item, index) in columns.filter((i) => {
            return i.search
          })"
          :key="index"
        >
          <el-input style="width: 230px" v-model="item.keyword" :placeholder="item.label" />
        </el-form-item>
        <el-form-item>
          <ElButton type="primary" v-hasPermission="['sys:sysUser:select']" @click="clickQuery">
            <Icon icon="bi:search" /> 查询
          </ElButton>
          <ElButton type="primary" @click="initData"> <Icon icon="bx:reset" /> 重置 </ElButton>
        </el-form-item>
      </el-form>
    </div>
    <Table
      :columns="columns"
      :data="tableData.data"
      :current-page="pagedPlayload.current"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pagedPlayload.size"
    >
      <template #action="{ row }">
        <ElButton type="danger" v-hasPermission="['sys:sysUser:delete']" @click="clickDelete(row)">
          删除
        </ElButton>
        <ElButton type="primary" v-hasPermission="['sys:sysUser:update']" @click="clickEdit(row)">
          编辑
        </ElButton>
      </template>
    </Table>
    <el-pagination
      v-model:currentPage="pagedPlayload.current"
      v-model:page-size="pagedPlayload.size"
      :page-sizes="[10, 20, 50, 100]"
      style="margin-top: 20px"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableData.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialog.visiable"
    :title="dialog.title"
    maxHeight="60%"
    @closed="clickClose"
    style="width: 40%; min-width: 375px; max-width: 600px"
  >
    <!-- 表单 -->
    <el-form ref="diaLogForm" :model="userPayload" label-width="65px">
      <el-form-item
        label="用户名"
        prop="username"
        :rules="[{ required: true, message: '用户名不能为空！' }]"
      >
        <el-input v-model="userPayload.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userPayload.password" autocomplete="off" />
      </el-form-item>
      <el-form-item
        label="角色"
        prop="roleId"
        :rules="[{ required: true, message: '请选择角色！' }]"
      >
        <el-select v-model="userPayload.roleId" placeholder="请选择角色">
          <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id">
            <span style="float: left">{{ item.name }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="userPayload.nickname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="userPayload.age" autocomplete="off" />
      </el-form-item>
      <el-form-item label="email" prop="email">
        <el-input v-model="userPayload.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" prop="sex" :rules="[{ required: true, message: '请选择性别！' }]">
        <el-radio-group v-model="userPayload.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <ElButton type="primary" style="margin-left: 38%" @click="clickSave(diaLogForm)">
        确定
      </ElButton>
      <el-button @click="clickClose(diaLogForm)">关闭</el-button>
    </template>
  </Dialog>
</template>
<style scoped></style>
