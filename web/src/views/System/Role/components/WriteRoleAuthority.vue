<script setup lang="ts">
import { ref, watch } from 'vue'
import { ElTree } from 'element-plus'
import type { Authority } from '@/api/authority/types'
import type { AuthorityTree } from '@/api/authority/types'
import type { PropType } from 'vue'

const props = defineProps({
  authorityList: {
    type: Array as PropType<Nullable<Authority[]>>,
    default: () => []
  },
  selectedKeys: {
    type: Array as PropType<Nullable<number[]>>,
    default: () => []
  }
})

const treeData = ref<AuthorityTree[]>([])
const selectedKeys = ref<number[]>([])
const treeRef = ref()

// 将扁平数据转换为树形结构
const processData = (data: any[]): AuthorityTree[] => {
  if (!Array.isArray(data)) {
    console.error('Invalid data format: data is not an array')
    return []
  }

  const map = new Map<number, AuthorityTree>()
  const result: AuthorityTree[] = []

  // 首先将所有节点放入 map
  data.forEach((item) => {
    if (item && typeof item === 'object') {
      map.set(item.id, {
        id: item.id,
        title: item.title || item.name || '',
        value: item.value || '',
        children: []
      })
    }
  })

  // 构建树形结构
  data.forEach((item) => {
    if (item && typeof item === 'object') {
      const node = map.get(item.id)
      if (node) {
        // 这里假设顶级节点的 parentId 为 0 或 null
        if (!item.parentId || item.parentId === 0) {
          result.push(node)
        } else {
          const parent = map.get(item.parentId)
          if (parent) {
            parent.children = parent.children || []
            parent.children.push(node)
          }
        }
      }
    }
  })

  return result
}

// 监听权限列表变化
watch(
  () => props.authorityList,
  (newVal) => {
    if (newVal) {
      // 确保数据是数组
      const data = Array.isArray(newVal) ? newVal : [newVal]
      treeData.value = processData(data)
    } else {
      console.error('Invalid authorityList format')
      treeData.value = []
    }
  }
)

watch(
  () => props.selectedKeys,
  (newVal) => {
    if (newVal) {
      selectedKeys.value = newVal
    }
  }
)

defineExpose({
  elFormRef: treeRef,
  getSelectedKeys: () => {
    console.log(treeRef.value?.getCheckedKeys())
    return treeRef.value?.getCheckedKeys() || []
  }
})
</script>

<template>
  <div class="authority-tree-container">
    <el-tree
      ref="treeRef"
      :data="treeData"
      :props="{
        label: 'title',
        children: 'children'
      }"
      show-checkbox
      node-key="id"
      :default-checked-keys="selectedKeys"
      :default-expanded-keys="selectedKeys"
    />
  </div>
</template>

<style scoped>
.authority-tree-container {
  padding: 20px;
  max-height: 400px;
  overflow-y: auto;
}
</style>
