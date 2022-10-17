<script setup lang="ts">
import { ContentWrap } from '@/components/ContentWrap'
import { h, ref, unref, reactive, onMounted } from 'vue'
import { Table } from '@/components/Table'
import {
  PermissionList,
  addPermission,
  DeletePermissions,
  EditPermissions,
  GetPermissionById,
  ParameterPermission
} from '@/api/permission'
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
  ElCol
} from 'element-plus'
import type { FormInstance } from 'element-plus'
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
    field: 'name',
    label: '权限名'
  },
  {
    field: 'value',
    label: 'value'
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

//  权限列表数据
let tabledata = ref('')

// 可生成实体列表
let keshengchenglist = ref('')
// 添加权限的输入数据
const numberForm = reactive({
  namejurisdiction: '',
  valuejurisdiction: '',
  // 带详情参数添加权限list数据
  entityFields: {
    description: '',
    fieldType: 'BOOLEAN',
    filedName: ''
  }
})
// 是否覆盖
const isCover = ref('true')
// 弹窗标题
const dialogTitle = ref('添加权限')
// 点击编辑，id存放
const editactionid = ref('')
// 表单的实例
const diaLogForm = ref<FormInstance>()
// 带详情参数添加权限list数据
// const entityFields = reactive({
//   description: '',
//   fieldType: '',
//   filedName: ''
// })

// 权限列表
const _PermissionList = async () => {
  const res = await PermissionList()
  // state.Tablelist = res.data
  tabledata.value = res.data
  // console.log(res)
}

// 添加权限
const _addPermission = async (name, value) => {
  await addPermission({
    name: name,
    value: value
  })
}
// 添加权限-带详情参数
const _ParameterPermission = async (entityFields, name, value) => {
  await ParameterPermission({
    entityFields: entityFields,
    name: name,
    value: value
  })
}
// 删除权限
const _DeletePermissions = async (id) => {
  await DeletePermissions(id)
}
// 编辑权限
const _EditPermissions = async (name, value, id) => {
  await EditPermissions({
    name: name,
    value: value,
    id: id
  })
}
// 通过id查询一条权限
const _GetPermissionById = async (id) => {
  const res = await GetPermissionById(id)
  // console.log(res)
  numberForm.namejurisdiction = res.data.name
  numberForm.valuejurisdiction = res.data.value
}
onMounted(async () => {
  await _PermissionList()
  // await _Cangenerateentitylist()
})
// 添加接口按钮
const tianjiajiekoubtn = () => {
  dialogTitle.value = '添加权限'
  // console.log(111)
  dialogVisible.value = true
}

// 弹窗的left的确定按钮
const save = (formEl: FormInstance | undefined) => {
  // console.log(formEl)
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      // 通过验证
      if (dialogTitle.value === '添加权限') {
        try {
          await _addPermission(numberForm.namejurisdiction, numberForm.valuejurisdiction)
          ElMessage({
            message: '添加权限成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      } else {
        // 编辑权限
        try {
          await _EditPermissions(
            numberForm.namejurisdiction,
            numberForm.valuejurisdiction,
            editactionid.value
          )
          ElMessage({
            message: '编辑权限成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      }
      // console.log(dinputvalue.value)
      // dialogVisible.value = false
      close()
      _PermissionList() // 跟新列表数据
    } else {
      // 表单不通过验证
      return false
    }
  })
}

// 弹窗的right的确定按钮
const RightSave = (formEl: FormInstance | undefined) => {
  console.log(formEl)
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      // 通过验证
      if (dialogTitle.value === '添加权限') {
        try {
          await _addPermission(numberForm.namejurisdiction, numberForm.valuejurisdiction)
          ElMessage({
            message: '添加权限成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      } else {
        // 编辑权限
        try {
          await _EditPermissions(
            numberForm.namejurisdiction,
            numberForm.valuejurisdiction,
            editactionid.value
          )
          ElMessage({
            message: '编辑权限成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      }
      close()
      _PermissionList() // 跟新列表数据
    } else {
      // 表单不通过验证
      return false
    }
  })
}
// 关闭按钮
const close = () => {
  dialogVisible.value = false
  // 重置表单
  ;(numberForm.namejurisdiction = ''), (numberForm.valuejurisdiction = '')
}
// 删除按钮
const deleteaction = async (row) => {
  console.log(row.id)
  try {
    const res = await ElMessageBox.confirm('确定要删除此权限吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    console.log(res)
    // 点击了确定
    if (res === 'confirm') {
      await _DeletePermissions(row.id)
      ElMessage({
        message: '操作成功!',
        type: 'success'
      })
    }
  } catch (error) {
    ElMessage.error(error)
  }
  _PermissionList() // 跟新列表数据
}
// 编辑按钮
const editaction = async (row) => {
  try {
    await _GetPermissionById(row.id)
    dialogTitle.value = '编辑权限'
    dialogVisible.value = true
    // console.log(row.id)
    editactionid.value = row.id
  } catch (error) {
    console.log(reeor)
  }
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <ElButton type="primary" @click="tianjiajiekoubtn">添加权限</ElButton>
      <!-- <ElButton :loading="delLoading" type="danger">删除</ElButton> -->
    </div>
    <Table :columns="columns" :data="tabledata" pageSize="3">
      <template #action="{ row }">
        <ElButton type="danger" :loading="delLoading" @click="deleteaction(row)"> 删除 </ElButton>
        <ElButton type="primary" :loading="delLoading" @click="editaction(row)"> 编辑 </ElButton>
      </template>
    </Table>
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog v-model="dialogVisible" :title="dialogTitle" maxHeight="200px" style="width: 30%">
    <!-- 左边 -->
    <el-form ref="diaLogForm" :model="numberForm">
      <el-form-item
        label="权限名"
        prop="namejurisdiction"
        :rules="[{ required: true, message: '权限名不能为空！' }]"
      >
        <el-input v-model="numberForm.namejurisdiction" autocomplete="off" />
      </el-form-item>
      <el-form-item
        prop="valuejurisdiction"
        label="权限点"
        :rules="[{ required: true, message: '权限点不能为空！' }]"
      >
        <el-input v-model="numberForm.valuejurisdiction" autocomplete="off" />
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
      <el-button @click="close">关闭</el-button>
    </template>
  </Dialog>
</template>
