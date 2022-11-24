<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive } from 'vue'
import { Table } from '@/components/Table'
import {
  pagedQueryPeople,
  addPeople,
  deletePeople,
  putPeople,
  getPeople,
  pagedSearchPeople
} from '@/api/people'
import { Page } from '@/api/common/types'
import { Dialog } from '@/components/Dialog'
import {
  ElButton,
  ElForm,
  ElFormItem,
  ElInput,
  ElMessage,
  ElMessageBox,
  ElPagination
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import { setNull, setValue } from '@/utils/index'
import { People } from '@/api/people/types'
import { SearchField } from '@/api/common/types'

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
// 表头
const columns = reactive<TableColumn[]>([
  {
    field: 'index',
    label: '序号',
    type: 'index'
  },
  {
    field: 'tests',
    label: '测试1',
    search: true
  },
  {
    field: 'testt',
    label: '测试二',
    search: true
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
const peoplePayload: People = reactive({
  id: null,
  createTime: null,
  updateTime: null,
  tests: null,
  testt: null
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
  const { data: res } = await pagedQueryPeople(pagedPlayload)
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
 * 添加
 */
const clickAdd = () => {
  dialog.value = {
    title: '添加',
    visiable: true
  }
}

/**
 * 编辑
 * @param row
 */
const clickEdit = async (row) => {
  peoplePayload.id = row.id //设置id
  const { data: res } = await getPeople(row.id)
  dialog.value = {
    title: '编辑',
    visiable: true
  }
  setValue(peoplePayload, res)
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
    const res: any = await deletePeople(row.id)
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
  setNull(peoplePayload)
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
      if (peoplePayload.id === null) {
        await addPeople(peoplePayload)
      } else {
        // 编辑
        await putPeople(peoplePayload)
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
  const { data: res } = await pagedSearchPeople(data)
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
      <ElButton type="success" v-hasPermission="['sys:people:add']" @click="clickAdd">
        <Icon icon="material-symbols:add" />添加
      </ElButton>
      <ElButton v-hasPermission="['sys:people:delete']" type="danger">
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
          <ElButton type="primary" v-hasPermission="['sys:people:select']" @click="clickQuery">
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
        <ElButton type="danger" v-hasPermission="['sys:people:delete']" @click="clickDelete(row)">
          删除
        </ElButton>
        <ElButton type="primary" v-hasPermission="['sys:people:update']" @click="clickEdit(row)">
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
    <el-form ref="diaLogForm" :model="peoplePayload" label-width="65px">
      <el-form-item label="测试1" prop="tests">
        <el-input v-model="peoplePayload.tests" autocomplete="off" />
      </el-form-item>
      <el-form-item label="测试二" prop="testt">
        <el-input v-model="peoplePayload.testt" autocomplete="off" />
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
