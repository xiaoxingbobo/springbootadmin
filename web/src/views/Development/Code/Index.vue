<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { ref, reactive, onMounted } from 'vue'
import { Table } from '@/components/Table'
import {
  Entitygenerationrecord,
  Cangenerateentitylist,
  Singleentitygeneration,
  RevokeEntity,
  ParameterPermission,
  PaginationQuery
} from '@/api/table'
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
  ElTabs,
  ElTabPane,
  ElRow,
  ElCol,
  ElPagination
} from 'element-plus'
import type { FormInstance } from 'element-plus'

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
let tabledata: any = ref('')
// 可生成实体列表
let keshengchenglist: any = ref('')
// 选择实体类输入框的值
const dinputvalue: any = ref('')
// 是否覆盖
const isCover: any = ref('true')
// tabs标签索引
let evalue: any = ref('')
// 字段枚举
const fileTypeList = ref([
  {
    key: 1,
    value: 'BIGDECIMAL',
    name: 'BigDecimal'
  },
  {
    key: 2,
    value: 'BOOLEAN',
    name: 'Boolean'
  },
  {
    key: 3,
    value: 'DATETIME',
    name: 'Datetime'
  },
  {
    key: 4,
    value: 'DOUBLE',
    name: 'Double'
  },
  {
    key: 5,
    value: 'FLOAT',
    name: 'Float'
  },
  {
    key: 6,
    value: 'INTEGER',
    name: 'Integer'
  },
  {
    key: 7,
    value: 'LONG',
    name: 'Long'
  },
  {
    key: 8,
    value: 'STRING',
    name: 'String'
  },
  {
    key: 9,
    value: 'SHORT',
    name: 'Short'
  }
])

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

// 动态添加删除表单开始------
const formRef = ref<FormInstance>()
const dynamicValidateForm: any = reactive<{
  domains: DomainItem[]
  entityFields: {
    description: string
    fieldType: string
    filedName: string
  }
  email: string
}>({
  // 不可增删的数据绑定
  entityFields: {
    description: '',
    fieldType: '',
    filedName: ''
  },
  // 动态增删的数据
  domains: [
    {
      key: 1,
      description: '',
      fieldType: '',
      filedName: ''
    }
  ],
  email: ''
})

interface DomainItem {
  key: number
  description: string
  fieldType: string
  filedName: string
}

const removeDomain = (item: DomainItem) => {
  const index = dynamicValidateForm.domains.indexOf(item)
  if (index !== -1) {
    dynamicValidateForm.domains.splice(index, 1)
  }
}

// 添加新选项按钮
const addDomain = () => {
  dynamicValidateForm.domains.push({
    key: Date.now(),
    description: '',
    fieldType: '',
    filedName: ''
  })
}
// 动态提交代码生成函数
const _ParameterPermission = async (data: any, entityName: any, isCover: any) => {
  const Parameterdata: any = {
    entityFields: data,
    entityName: entityName,
    isCover: isCover
  }
  await ParameterPermission(Parameterdata)
}

// tabs标签事件
const tabClick = (e) => {
  console.log(e.index)
  // console.log(w)
  evalue.value = e.index
}
// ------动态添加删除表单结束

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
const save = async (formEl: FormInstance | undefined) => {
  if (evalue.value === '1') {
    // 带参数
    // console.log('带参数')
    if (!formEl) return
    formEl.validate((valid) => {
      if (valid) {
        // 表单通过验证
        // console.log(dynamicValidateForm.domains)
        try {
          const dataList: any = [].concat(
            dynamicValidateForm.entityFields,
            dynamicValidateForm.domains
          )
          // console.log(dataList)
          _ParameterPermission(dataList, dinputvalue.value, isCover.value)
          ElMessage.success('操作成功！')
          closeDialog() // 关闭弹窗
        } catch (error) {
          console.log(error)
        }
      } else {
        console.log('error submit!')
        return false
      }
      _Entitygenerationrecord() // 跟新列表数据
    })
  } else {
    // 不带参数
    // console.log('不带参数')
    try {
      await _Singleentitygeneration(dinputvalue.value, isCover.value)
      ElMessage({
        message: '生成接口成功!',
        type: 'success'
      })
      dialogVisible.value = false // 关闭弹窗
      _Entitygenerationrecord() // 跟新列表数据
    } catch (error: any) {
      ElMessage.error(error)
    }
  }
  // console.log(dinputvalue.value)
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
  } catch (error: any) {
    ElMessage.error(error)
  }
  _Entitygenerationrecord() // 跟新列表数据
}

// 关闭弹窗 重置表单数据
const closeDialog = () => {
  dialogVisible.value = false
  dinputvalue.value = []
  // 重置表单数据
  dynamicValidateForm.entityFields = [
    {
      description: '',
      fieldType: '',
      filedName: ''
    }
  ]
  dynamicValidateForm.domains = [
    {
      key: 1,
      description: '',
      fieldType: '',
      filedName: ''
    }
  ]
  _Entitygenerationrecord() // 跟新列表数据
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" v-hasPermission="['sys:code:add']" @click="tianjiajiekoubtn"
        >生成</ElButton
      >
    </div>
    <Table :columns="columns" :data="tabledata">
      <template #action="{ row }">
        <ElButton type="danger" v-hasPermission="['sys:code:undo']" @click="action(row)">
          撤销
        </ElButton>
      </template>
    </Table>
    <!-- 分页 -->
    <div class="mt-10px">
      <ElPagination
        v-model:currentPage="currentPage"
        :page-sizes="[10, 50, 100, 400]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        :page-size="Paginationdata.size"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialogVisible"
    title="生成接口"
    maxHeight="350px"
    style="width: 40%; min-width: 375px; max-width: 600px"
    @closed="closeDialog"
  >
    <ElTabs type="border-card" @tab-click="tabClick">
      <!-- 左边 -->
      <el-tab-pane label="已有实体类生成API接口">
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
      </el-tab-pane>
      <!-- 右边 -->
      <el-tab-pane label="生成实体类、API、管理后台">
        <el-form>
          <el-form-item label="实体类名">
            <el-input placeholder="请输入实体类名" v-model="dinputvalue" />
          </el-form-item>
          <el-form-item label="是否覆盖">
            <el-radio-group v-model="isCover" class="ml-4">
              <el-radio label="true" size="large">是</el-radio>
              <el-radio label="false" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>

        <!-- 动态添加删除 -->
        <el-form ref="formRef" :model="dynamicValidateForm" class="demo-dynamic">
          <el-form-item>
            <el-button @click="addDomain" type="primary">添加字段</el-button>
          </el-form-item>
          <el-form-item
            prop="entityFields.description"
            label="字段1"
            :rules="[
              {
                required: true,
                message: '该选项不能为空！',
                trigger: 'blur'
              }
            ]"
          >
            <!-- <el-input v-model="dynamicValidateForm.email" /> -->
            <el-row :gutter="5">
              <el-col :span="8"
                ><el-input
                  placeholder="字段名"
                  v-model="dynamicValidateForm.entityFields.filedName"
              /></el-col>
              <el-col :span="8">
                <el-select
                  v-model="dynamicValidateForm.entityFields.fieldType"
                  placeholder="字段类型"
                >
                  <el-option
                    :label="item.name"
                    v-for="(item, index) in fileTypeList"
                    :key="index"
                    :value="item.value"
                  >
                    <span style="float: left">{{ item.name }}</span>
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="8"
                ><el-input
                  placeholder="字段说明"
                  v-model="dynamicValidateForm.entityFields.description"
              /></el-col>
            </el-row>
          </el-form-item>
          <el-form-item
            v-for="(domain, index) in dynamicValidateForm.domains"
            :key="domain.key"
            :prop="'domains.' + index + '.description'"
            :label="'字段' + (index + 2)"
            :rules="[
              {
                required: true,
                message: '该选项不能为空！',
                trigger: 'blur'
              }
            ]"
          >
            <el-row :gutter="5">
              <el-col :span="7"
                ><el-input placeholder="字段名" v-model="domain.filedName"
              /></el-col>
              <el-col :span="7">
                <el-select v-model="domain.fieldType" placeholder="字段类型">
                  <el-option
                    :label="item.name"
                    v-for="item in fileTypeList"
                    :key="item.key"
                    :value="item.value"
                  >
                    <span style="float: left">{{ item.name }}</span>
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="8"
                ><el-input placeholder="字段说明" v-model="domain.description"
              /></el-col>

              <el-col :span="2">
                <el-button
                  class="mt-2"
                  style="height: 35px; margin-top: -2px"
                  @click.prevent="removeDomain(domain)"
                  >-</el-button
                >
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </ElTabs>

    <template #footer>
      <ElButton type="primary" @click="save(formRef)"> 确定 </ElButton>
      <ElButton @click="closeDialog">关闭</ElButton>
    </template>
  </Dialog>
</template>
