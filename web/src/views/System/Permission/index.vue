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
  ParameterPermission,
  PaginationQuery,
  menuList
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
  ElCol,
  ElPagination,
  ElTree,
  ElTreeSelect,
  ElTable,
  ElTableColumn
} from 'element-plus'
import type { FormInstance } from 'element-plus'
import Write from './components/Write.vue'
import { values } from 'lodash'
import { tranListToTreeData } from '@/utils/tree'

// 是否显示弹窗
const dialogVisible = ref(false)
// 添加菜单弹窗
const isAddMenu = ref(false) // 是否是添加菜单

// 表头
// const columns = reactive<TableColumn[]>([
//   {
//     field: 'index',
//     label: '序号',
//     type: 'index'
//   },
//   {
//     field: 'title',
//     label: '权限名'
//   },
//   {
//     field: 'value',
//     label: 'value'
//   },
//   {
//     field: 'createTime',
//     label: '创建时间'
//   },
//   {
//     field: 'action',
//     width: '160px',
//     label: '操作',
//     form: {
//       show: false
//     },
//     detail: {
//       show: false
//     }
//   }
// ])

//  权限列表数据
let tabledata = ref('')
// 树形权限列表数据
let treetabledata = ref([])
// 菜单列表数据—树形
let menuTableList = ref([])
// 全部权限列表数据
let allTabledata = ref([])

// 可生成实体列表
let keshengchenglist = ref('')
// 添加权限的输入数据
const numberForm = reactive({
  namejurisdiction: '',
  valuejurisdiction: '',
  parentIdjurisdiction: 0,
  authorityType: 'API' // 表示为权限
})
// 添加菜单的输入数据
const numberFormmenu = reactive({
  namejurisdiction: '',
  valuejurisdiction: '',
  parentIdjurisdiction: 0,
  name: '',
  path: '',
  component: 'Layout',
  icon: null,
  authorityType: 'ROUTER' // 表示为菜单
})
// 上级菜单选择的树形指向
const defaultProps = {
  children: 'children',
  label: 'title'
}
// 是否覆盖
const isCover = ref('true')
// 弹窗标题
const dialogTitle = ref('添加权限')
// 点击编辑，id存放
const editactionid = ref(0)
// 添加权限表单的实例
const diaLogForm = ref<FormInstance>()
// 添加菜单表单的实例
const diaLogFormmenu = ref<FormInstance>()

// 树形上级id选择
// 当前选择的树形权限的上级id
const treeparentId = ref(null)

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
}
// 查询全部权限列表-转化成树形
const _PermissionList = async () => {
  const { data: treeres } = await PermissionList()
  const treerespromiseLIst = ref(treeres)
  treetabledata.value = tranListToTreeData(treerespromiseLIst.value, 0) // 将数组转化成树形
  _PermissionListmenu() // 查询全部菜单列表-转化成树形
}

// 查询全部菜单列表-转化成树形
const _PermissionListmenu = async () => {
  const { data: treemenu } = await PermissionList()
  // 筛选出菜单
  let treeresmenuList = ref([]) // 只要菜单的数据列表
  treemenu.forEach((emenu) => {
    if (emenu.authorityType === 'ROUTER') {
      treeresmenuList.value.push(emenu)
      console.log(treeresmenuList.value)
    }
  })
  menuTableList.value = tranListToTreeData(treeresmenuList.value, 0)
}

// 点击权限树形的节点
const treenodeClick = (e) => {
  numberForm.parentIdjurisdiction = e.id
}
// 点击菜单树形的节点
const treenodeClickmenu = (e) => {
  numberFormmenu.parentIdjurisdiction = e.id
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

// 添加权限
const _addPermission = async (title, value, parentId) => {
  await addPermission({
    title: title,
    value: value,
    parentId: parentId
  })
}
// 添加菜单
const _addPermissionmenu = async (menudata) => {
  const data = {
    title: menudata.namejurisdiction,
    parentId: menudata.parentIdjurisdiction,
    value: menudata.valuejurisdiction,
    name: menudata.name,
    path: menudata.path,
    component: menudata.component,
    icon: menudata.icon,
    authorityType: 'ROUTER'
  }
  await addPermission(data)
}

// 添加权限-带详情参数
const _ParameterPermission = async (entityFields, name, value) => {
  await ParameterPermission({
    entityFields: entityFields,
    title: name,
    value: value
  })
}
// 删除权限
const _DeletePermissions = async (id) => {
  await DeletePermissions(id)
}
// 编辑权限
const _EditPermissions = async (name, value, parentId, id) => {
  await EditPermissions({
    title: name,
    value: value,
    parentId: parentId,
    id: id
  })
}
// 通过id查询一条权限
const _GetPermissionById = async (id) => {
  const res = await GetPermissionById(id)
  // console.log(res)
  // 给编辑的输入框赋值
  numberForm.namejurisdiction = res.data.title
  numberForm.valuejurisdiction = res.data.value
  numberForm.parentIdjurisdiction = res.data.parentId
  // 通过父id，遍历所有权限，父id=id,就找出父权限的名字
  allTabledata.value.forEach((elall) => {
    if (res.data.parentId === elall.id) {
      // console.log(elall.name)
      treeparentId.value = elall.title // 上级权限名字
    }
  })
}
onMounted(async () => {
  // await _PaginationQuery() // 跟新列表
  _PermissionList() // 跟新列表
  // _PermissionListmenu() // 请求菜单列表
})
// 添加权限按钮
const tianjiajiekoubtn = () => {
  dialogTitle.value = '添加权限'
  dialogVisible.value = true
}
// 添加菜单按钮
const tianjiamenu = () => {
  dialogTitle.value = '添加菜单'
  isAddMenu.value = true // 显示添加菜单弹窗
}

// 权限弹窗的确定按钮
const save = (formEl: FormInstance | undefined) => {
  // console.log(formEl)
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      // 通过验证
      if (dialogTitle.value === '添加权限') {
        try {
          await _addPermission(
            numberForm.namejurisdiction,
            numberForm.valuejurisdiction,
            numberForm.parentIdjurisdiction
          )
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
            numberForm.parentIdjurisdiction,
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
      dialogVisible.value = false
      close()
      // _PaginationQuery() // 跟新列表
      _PermissionList() // 跟新列表
    } else {
      // 表单不通过验证
      return false
    }
  })
}

// 菜单弹窗确定按钮
const savemenu = (formEl2: FormInstance | undefined) => {
  // console.log(formEl)
  if (!formEl2) return
  formEl2.validate(async (valid) => {
    if (valid) {
      // 通过验证
      if (dialogTitle.value === '添加菜单') {
        try {
          await _addPermissionmenu(numberFormmenu)
          ElMessage({
            message: '添加菜单成功!',
            type: 'success'
          })
          console.log(numberFormmenu)
        } catch (error) {
          ElMessage.error(error)
        }
      } else {
        // 编辑权限
        try {
          await _EditPermissions(
            numberFormmenu.namejurisdiction,
            numberFormmenu.valuejurisdiction,
            numberFormmenu.parentIdjurisdiction,
            editactionid.value
          )
          ElMessage({
            message: '编辑菜单成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      }
      // console.log(dinputvalue.value)
      dialogVisible.value = false
      closemenu()
      _PermissionList() // 跟新列表
    } else {
      // 表单不通过验证
      return false
    }
  })
}

// 关闭按钮
const close = () => {
  // 重置数据
  treeparentId.value = null
  dialogVisible.value = false
  editactionid.value = 0
  // 重置表单
  ;(numberForm.namejurisdiction = ''),
    (numberForm.valuejurisdiction = ''),
    (numberForm.parentIdjurisdiction = 0)
}
// 关闭按钮
const closemenu = () => {
  // 重置数据
  treeparentId.value = null
  isAddMenu.value = false
  _PermissionList() // 跟新列表
  editactionid.value = 0
  // 重置表单
  ;(numberFormmenu.namejurisdiction = ''),
    (numberFormmenu.valuejurisdiction = ''),
    (numberFormmenu.parentIdjurisdiction = 0),
    (numberFormmenu.name = ''),
    (numberFormmenu.path = ''),
    (numberFormmenu.component = 'Layout'),
    (numberFormmenu.icon = null)
}
// 删除按钮
const deleteaction = async (row) => {
  // console.log(row.id)
  // console.log(index)
  try {
    const res = await ElMessageBox.confirm('确定要删除此权限吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    // console.log(res)
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
  // _PaginationQuery() // 跟新列表
  _PermissionList() // 跟新列表
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
      <ElButton type="success" @click="tianjiajiekoubtn">添加权限</ElButton>
      <ElButton type="success" @click="tianjiamenu">添加菜单</ElButton>
    </div>
    <!-- 树形table -->
    <el-table :data="treetabledata" style="width: 100%; margin-bottom: 20px" row-key="id" border>
      <el-table-column prop="title" label="名称" sortable />
      <el-table-column prop="value" label="权限值" sortable />
      <el-table-column prop="path" label="path" sortable />
      <el-table-column prop="icon" label="icon" sortable />
      <el-table-column prop="authorityType" label="菜单" sortable />
      <el-table-column prop="createTime" label="创建时间" sortable />
      <el-table-column label="操作">
        <template #default="scope">
          <ElButton type="danger" :loading="delLoading" @click="deleteaction(scope.row)">
            删除
          </ElButton>
          <ElButton type="primary" :loading="delLoading" @click="editaction(scope.row)">
            编辑
          </ElButton>
        </template>
      </el-table-column>
    </el-table>
  </ContentWrap>
  <!-- 弹窗 -->
  <Dialog
    v-model="dialogVisible"
    :title="dialogTitle"
    maxHeight="200px"
    style="width: 30%; min-width: 375px; max-width: 600px"
    @closed="close"
  >
    <!-- 添加权限 -->
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
        label="权限值"
        :rules="[{ required: true, message: '权限值不能为空！' }]"
      >
        <el-input v-model="numberForm.valuejurisdiction" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="treeparentId" label="上级权限">
        <el-tree-select
          v-model="treeparentId"
          :data="treetabledata"
          check-strictly
          value-key="title"
          :render-after-expand="false"
          placeholder="请选择角色"
          @node-click="treenodeClick"
        />
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

  <!-- 添加菜单 -->
  <Dialog
    v-model="isAddMenu"
    :title="dialogTitle"
    maxHeight="400px"
    style="width: 30%; min-width: 575px; max-width: 700px"
    @closed="closemenu"
  >
    <!-- 添加菜单 -->
    <el-form ref="diaLogFormmenu" :model="numberFormmenu" label-width="100px">
      <el-form-item
        label="菜单名"
        prop="namejurisdiction"
        :rules="[{ required: true, message: '菜单名不能为空！' }]"
      >
        <el-input v-model="numberFormmenu.namejurisdiction" autocomplete="off" />
      </el-form-item>
      <el-form-item
        prop="valuejurisdiction"
        label="权限值"
        :rules="[{ required: true, message: '权限值不能为空！' }]"
      >
        <el-input v-model="numberFormmenu.valuejurisdiction" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="treeparentId" label="上级菜单">
        <el-tree-select
          v-model="treeparentId"
          :data="menuTableList"
          check-strictly
          value-key="id"
          :render-after-expand="false"
          placeholder="请选择上级菜单"
          :props="defaultProps"
          @node-click="treenodeClickmenu"
        />
      </el-form-item>

      <!-- name -->
      <el-form-item
        label="name"
        prop="name"
        :rules="[{ required: true, message: '菜单名不能为空！' }]"
      >
        <el-input v-model="numberFormmenu.name" autocomplete="off" />
      </el-form-item>

      <!-- path -->
      <el-form-item
        label="path"
        prop="path"
        :rules="[{ required: true, message: '菜单名不能为空！' }]"
      >
        <el-input
          v-model="numberFormmenu.path"
          placeholder="children的path前缀不加/"
          autocomplete="off"
        />
      </el-form-item>

      <!-- component -->
      <el-form-item
        label="component"
        prop="component"
        :rules="[{ required: true, message: '菜单名不能为空！' }]"
      >
        <el-input
          v-model="numberFormmenu.component"
          placeholder="一级菜单：Layout，子菜单为导入组件的地址"
          autocomplete="off"
        />
      </el-form-item>

      <!-- icon -->
      <el-form-item label="icon" prop="namejurisdiction">
        <el-input v-model="numberFormmenu.icon" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <ElButton
        type="primary"
        style="margin-left: 38%"
        :loading="loading"
        @click="savemenu(diaLogFormmenu)"
      >
        确定
      </ElButton>
      <el-button @click="closemenu">关闭</el-button>
    </template>
  </Dialog>
</template>
