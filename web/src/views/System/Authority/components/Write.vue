<script setup lang="ts">
import { PropType, reactive, watch, ref, computed } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { Authority, AuthorityTree } from '@/api/authority/types'
import { ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElTreeSelect } from 'element-plus'

const { required } = useValidator()

const props = defineProps({
  currentRow: {
    type: Object as PropType<Nullable<Authority>>,
    default: () => null
  },
  formSchema: {
    type: Array as PropType<any[]>,
    default: () => []
  },
  treeData: {
    type: Array as PropType<AuthorityTree[]>,
    default: () => []
  }
})

const formRef = ref()
const formData = ref<Authority>({
  id: 0,
  title: '',
  value: '',
  authorityType: 'ROUTER',
  parentId: 0,
  path: '',
  icon: '',
  component: ''
})

const rules = reactive({
  title: [required()],
  value: [required()],
  authorityType: [required()]
})

// 添加顶级菜单选项
const treeDataWithTop = computed(() => {
  return [
    {
      id: 0,
      title: '顶级菜单',
      children: props.treeData
    }
  ]
})

// 监听当前行变化
watch(
  () => props.currentRow,
  (currentRow) => {
    if (!currentRow) {
      // 新建时设置默认值
      formData.value = {
        id: 0,
        title: '',
        value: '',
        authorityType: 'ROUTER',
        parentId: 0,
        path: '',
        icon: '',
        component: ''
      }
      return
    }
    // 编辑时设置当前行数据
    formData.value = {
      id: currentRow?.id ?? '',
      title: currentRow?.title ?? '',
      value: currentRow?.value ?? '',
      authorityType: currentRow?.authorityType ?? 'ROUTER',
      parentId: currentRow?.parentId ?? 0,
      path: currentRow?.path ?? '',
      icon: currentRow?.icon ?? '',
      component: currentRow?.component ?? ''
    }
  },
  {
    deep: true,
    immediate: true
  }
)

// 监听父级菜单变化
watch(
  () => formData.value.parentId,
  (newParentId) => {
    if (newParentId === 0) {
      // 如果是顶级菜单，设置默认组件为Layout
      formData.value.component = 'Layout'
    }
  }
)

defineExpose({
  elFormRef: formRef,
  getFormData: () => formData.value
})
</script>

<template>
  <ElForm ref="formRef" :model="formData" :rules="rules" label-width="100px" class="demo-ruleForm">
    <ElFormItem label="名称" prop="title">
      <ElInput v-model="formData.title" placeholder="请输入名称" />
      <div class="form-tip">菜单显示的名称，如：系统管理</div>
    </ElFormItem>
    <ElFormItem label="权限值" prop="value">
      <ElInput v-model="formData.value" placeholder="请输入权限值" />
      <div class="form-tip">权限标识，如：sys:user:list</div>
    </ElFormItem>
    <ElFormItem label="菜单类型" prop="authorityType">
      <ElSelect v-model="formData.authorityType" placeholder="请选择菜单类型" style="width: 100%">
        <ElOption label="菜单" value="ROUTER" />
        <ElOption label="权限" value="API" />
      </ElSelect>
      <div class="form-tip">菜单：显示在左侧菜单栏；权限：用于接口权限控制</div>
    </ElFormItem>
    <ElFormItem label="上级菜单" prop="parentId">
      <ElTreeSelect
        v-model="formData.parentId"
        :data="treeDataWithTop"
        node-key="id"
        :default-expanded-keys="[formData.parentId]"
        :props="{
          label: 'title',
          value: 'id',
          children: 'children'
        }"
        check-strictly
        placeholder="请选择上级菜单"
        style="width: 100%"
      />
      <div class="form-tip">选择上级菜单，顶级菜单选择'顶级菜单'</div>
    </ElFormItem>
    <ElFormItem label="路由" prop="path">
      <ElInput v-model="formData.path" placeholder="请输入路由" />
      <div class="form-tip">
        路由地址，做为顶级菜单时候需要加/，如：/system，二级菜单为路径如：system/user/index，权限则留空</div
      >
    </ElFormItem>
    <ElFormItem label="组件" prop="component">
      <ElInput v-model="formData.component" placeholder="请输入组件路径" />
      <div class="form-tip"
        >组件路径，顶级菜单为Layout，二级菜单为路径如：system/user/index，权限则留空
      </div>
    </ElFormItem>
    <ElFormItem label="图标" prop="icon">
      <ElInput v-model="formData.icon" placeholder="请输入图标名称" />
      <div class="form-tip">图标名称，使用Element Plus图标，如：Setting</div>
    </ElFormItem>
  </ElForm>
</template>

<style scoped>
.demo-ruleForm {
  padding: 20px;
}
.form-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 4px;
  line-height: 1.2;
}
</style>
