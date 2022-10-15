<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { h, ref, unref, reactive, onMounted } from 'vue'
import { Table } from '@/components/Table'
import { Entitygenerationrecord, Cangenerateentitylist, Singleentitygeneration } from '@/api/table'
import { Dialog } from '@/components/Dialog'
import { ElButton, ElTag, ElForm, ElFormItem, ElInput, ElSelect, ElOption } from 'element-plus'
import Write from './components/Write.vue'
import { values } from 'lodash'

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
    field: 'entityName',
    label: '已生成接口'
  },
  {
    field: 'createTime',
    label: '创建时间'
  },
  {
    field: 'action',
    label: 'action'
  }
])

// 可生成记录数据
let tabledata = ref('')
// 可生成实体列表
let keshengchenglist = ref('')
// 选择实体类输入框的值
const dinputvalue = ref('')
// 选择框弹出的数据
const cities = [
  {
    value: 'Beijing',
    label: 'Beijing'
  },
  {
    value: 'Shanghai',
    label: 'Shanghai'
  }
]

// 实体生成记录
const _Entitygenerationrecord = async () => {
  const res = await Entitygenerationrecord()
  // state.Tablelist = res.data
  tabledata.value = res.data
  // console.log(tabledata.value)
}
// 可生成实体列表
const _Cangenerateentitylist = async () => {
  const res = await Cangenerateentitylist()
  // console.log(res)
  keshengchenglist.value = res.data
  // console.log(keshengchenglist.value)
}
// 单个实体生成
const _Singleentitygeneration = async (entityName) => {
  await Singleentitygeneration({
    entityName
  })
}

onMounted(async () => {
  await _Entitygenerationrecord()
  // await _Cangenerateentitylist()
})
// 添加接口按钮
const tianjiajiekoubtn = () => {
  // console.log(111)
  dialogVisible.value = true
}
// 弹窗的确定按钮
const save = async () => {
  dialogVisible.value = false
  console.log(dinputvalue.value)
  // const dindata = toString(dinputvalue.value)
  // const res = await _Singleentitygeneration('sss')
  const res = await _Singleentitygeneration(dinputvalue.value)
  console.log(res)
}
// select获得焦距
const selectfocus = () => {
  // console.log(11111)
  _Cangenerateentitylist()
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">添加接口</ElButton>
      <ElButton :loading="delLoading" type="danger">删除</ElButton>
    </div>
    <Table :columns="columns" :data="tabledata" />
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog v-model="dialogVisible" title="生成实体类">
    <el-form>
      <el-form-item label="选择实体类">
        <el-select v-model="dinputvalue" placeholder="Select" @focus="selectfocus">
          <el-option v-for="(item, index) in keshengchenglist" :key="index" :value="item">
            <span style="float: left">{{ item }}</span>
            <!-- <span style="float: right; color: var(--el-text-color-secondary); font-size: 13px">{{
              item
            }}</span> -->
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="选择实体类">
        <el-radio-group v-model="radio1" class="ml-4">
          <el-radio label="1" size="large">Option 1</el-radio>
          <el-radio label="2" size="large">Option 2</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <template #footer>
      <ElButton type="primary" :loading="loading" @click="save"> 确定 </ElButton>
      <el-button @click="dialogVisible = false">关闭</el-button>
    </template>
  </Dialog>
</template>
