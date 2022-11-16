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

//  权限列表数据
let tabledata = ref('')
// 树形权限列表数据
let treetabledata = ref([])
// 菜单列表数据—树形
let menuTableList = ref([])
// 全部权限列表数据
let allTabledata = ref([])
// 编辑选择框里面的数据类型
const radioauthorityType = ref(1)
// 添加权限的输入数据
const numberForm = reactive({
  namejurisdiction: '',
  valuejurisdiction: '',
  parentIdjurisdiction: 0,
  authorityType: 'API' // 表示为权限
})
// 是权限还是菜单
const isauthorityType = ref('ROUTER')
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
// 点击编辑，当前的id存放,
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

// 查询全部权限列表-转化成树形
const _PermissionList = async () => {
  const { data: treeres } = await PermissionList()
  allTabledata.value = treeres
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
      // console.log(treeresmenuList.value)
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

// 添加权限-菜单
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

// 删除权限/菜单
const _DeletePermissions = async (id) => {
  await DeletePermissions(id)
}
// 编辑权限-菜单
const _EditPermissions = async (name, value, parentId, id, data) => {
  await EditPermissions({
    title: name,
    value: value,
    parentId: parentId,
    id: id,
    // 下面是编辑菜单时候的参数
    name: data.name,
    path: data.path,
    component: data.component,
    icon: data.icon,
    authorityType: data.authorityType
  })
}
// 通过id查询一条权限
const _GetPermissionById = async (id) => {
  const res = await GetPermissionById(id)
  // console.log(res.data)
  // 给编辑的输入框赋值
  numberFormmenu.namejurisdiction = res.data.title
  numberFormmenu.valuejurisdiction = res.data.value
  numberFormmenu.parentIdjurisdiction = res.data.parentId
  numberFormmenu.name = res.data.name
  numberFormmenu.path = res.data.path
  numberFormmenu.component = res.data.component
  numberFormmenu.icon = res.data.icon
  numberFormmenu.component = res.data.component
  isauthorityType.value = res.data.authorityType // 判断是权限还是菜单
  // 判断数据是权限还是菜单，绑定给编辑单选框的的数值（1：权限，2：菜单）
  if (res.data.authorityType === 'ROUTER') {
    radioauthorityType.value = 2
  } else {
    radioauthorityType.value = 1
  }
  // 通过父id，遍历所有权限，父id=id,就找出父权限的名字
  allTabledata.value.forEach((elall) => {
    if (res.data.parentId === elall.id) {
      treeparentId.value = elall.title // 上级权限名字
    }
  })
}
onMounted(async () => {
  // await _PaginationQuery() // 跟新列表
  _PermissionList() // 跟新列表
  // _PermissionListmenu() // 请求菜单列表
})

// 添加权限和菜单按钮
const tianjiamenu = () => {
  dialogTitle.value = '添加(权限/菜单)'
  isAddMenu.value = true // 显示添加菜单弹窗
}

// 菜单弹窗确定按钮--点击编辑弹出的是菜单的弹窗
const savemenu = (formEl2: FormInstance | undefined) => {
  // console.log(formEl)
  if (!formEl2) return
  formEl2.validate(async (valid) => {
    if (valid) {
      // 通过验证
      if (dialogTitle.value === '添加(权限/菜单)') {
        try {
          await _addPermissionmenu(numberFormmenu)
          ElMessage({
            message: '添加成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      } else {
        // 编辑权限
        const Permissions_data = {
          name: numberFormmenu.name,
          path: numberFormmenu.path,
          component: isauthorityType.value === 'ROUTER' ? numberFormmenu.component : '', // 如果修改的是权限就传''
          icon: numberFormmenu.icon,
          authorityType: radioauthorityType.value === 1 ? 'API' : 'ROUTER'
        }
        try {
          await _EditPermissions(
            numberFormmenu.namejurisdiction,
            numberFormmenu.valuejurisdiction,
            numberFormmenu.parentIdjurisdiction,
            editactionid.value,
            Permissions_data
          )
          ElMessage({
            message: '编辑成功!',
            type: 'success'
          })
        } catch (error) {
          ElMessage.error(error)
        }
      }
      closemenu()
    } else {
      // 表单不通过验证
      return false
    }
  })
}

// 关闭按钮
const closemenu = () => {
  // 重置数据
  treeparentId.value = null
  isAddMenu.value = false
  radioauthorityType.value = 1
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
  try {
    const res = await ElMessageBox.confirm('确定要删除此权限吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
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
    dialogTitle.value = '编辑'
    isAddMenu.value = true
    editactionid.value = row.id
  } catch (error) {
    console.log(error)
  }
}
</script>

<template>
  <ContentWrap>
    <div class="mb-10px">
      <!-- <ElButton type="success" @click="tianjiajiekoubtn">添加权限</ElButton> -->
      <ElButton type="success" @click="tianjiamenu">添加</ElButton>
    </div>
    <!-- 树形table -->
    <el-table :data="treetabledata" style="width: 100%; margin-bottom: 20px" row-key="id" border>
      <el-table-column prop="title" label="名称" sortable />
      <el-table-column prop="value" label="权限值" sortable />
      <el-table-column prop="path" label="path" sortable />
      <el-table-column prop="icon" label="icon" sortable />
      <el-table-column prop="authorityType" label="菜单" sortable />
      <el-table-column prop="createTime" label="创建时间" sortable />
      <el-table-column label="操作" width="180px">
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
  <!-- 添加菜单 -->
  <Dialog
    v-model="isAddMenu"
    :title="dialogTitle"
    maxHeight="400px"
    style="width: 40%; min-width: 375px; max-width: 700px"
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
      <el-form-item label="name" prop="name">
        <el-input v-model="numberFormmenu.name" autocomplete="off" />
      </el-form-item>

      <!-- path -->
      <el-form-item label="path" prop="path">
        <el-input
          v-model="numberFormmenu.path"
          placeholder="children的path前缀不加/"
          autocomplete="off"
        />
      </el-form-item>

      <!-- component -->
      <el-form-item label="component" prop="component">
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
      <!-- 选择数据的类型 -->
      <el-form-item label="数据类型">
        <el-radio-group v-model="radioauthorityType">
          <el-radio :label="1">权限</el-radio>
          <el-radio :label="2">菜单</el-radio>
        </el-radio-group>
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
