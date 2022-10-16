<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { h, ref, unref, reactive, onMounted } from 'vue'
import { Table } from '@/components/Table'
import {
  Entitygenerationrecord,
  Cangenerateentitylist,
  Singleentitygeneration,
  RevokeEntity
} from '@/api/table'
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
  ElMessageBox
} from 'element-plus'
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
    label: '创建时间',
    width: '260px'
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

// 实体生成记录数据
let tabledata = ref('')
// 可生成实体列表
let keshengchenglist = ref('')
// 选择实体类输入框的值
const dinputvalue = ref('')
// 是否覆盖
const isCover = ref('true')

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
const _Singleentitygeneration = async (entityName, isCover) => {
  await Singleentitygeneration({
    entityName,
    isCover: isCover
  })
}
// 撤销实体生成
const _RevokeEntity = async (id) => {
  await RevokeEntity(id)
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
  // console.log(dinputvalue.value)
  try {
    await _Singleentitygeneration(dinputvalue.value, isCover.value)
    ElMessage({
      message: '生成接口成功!',
      type: 'success'
    })
  } catch (error) {
    ElMessage.error(error)
  }
  _Entitygenerationrecord() // 跟新列表数据
}
// select获得焦距
const selectfocus = () => {
  // console.log(11111)
  _Cangenerateentitylist()
}
// 删除按钮
const action = async (row) => {
  console.log(row.id)
  try {
    const res = await ElMessageBox.confirm('确定要删除此接口吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    console.log(res)
    // 点击了确定
    if (res === 'confirm') {
      await _RevokeEntity(row.id)
      ElMessage({
        message: '操作成功!',
        type: 'success'
      })
    }
  } catch (error) {
    ElMessage.error(error)
  }

  _Entitygenerationrecord() // 跟新列表数据
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">生成接口</ElButton>
      <ElButton :loading="delLoading" type="danger">删除</ElButton>
    </div>
    <Table :columns="columns" :data="tabledata">
      <template #action="{ row }">
        <ElButton type="danger" :loading="delLoading" @click="action(row)"> 撤销 </ElButton>
      </template>
    </Table>
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog v-model="dialogVisible" title="生成接口" maxHeight="350px">
    <el-form>
      <el-form-item label="选择实体类">
        <el-select v-model="dinputvalue" placeholder="请选择实体名" @focus="selectfocus">
          <el-option v-for="(item, index) in keshengchenglist" :key="index" :value="item">
            <span style="float: left">{{ item }}</span>
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否覆盖">
        <el-radio-group v-model="isCover" class="ml-4">
          <el-radio label="true" size="large">是</el-radio>
          <el-radio label="false" size="large">否</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <template #footer>
      <ElButton type="primary" :loading="loading" @click="save"> 确定 </ElButton>
      <el-button @click="dialogVisible = false">关闭</el-button>
    </template>
  </Dialog>
</template>
