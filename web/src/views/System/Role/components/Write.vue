<script setup lang="ts">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { PropType, reactive, watch } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/types/form'
import { Role } from '@/api/role/types'

const { required } = useValidator()

const props = defineProps({
  currentRow: {
    type: Object as PropType<Nullable<Role>>,
    default: () => null
  },
  formSchema: {
    type: Array as PropType<FormSchema[]>,
    default: () => []
  }
})

const rules = reactive({
  username: [required()],
  roleId: [required()]
})

const { register, methods, elFormRef } = useForm({
  schema: props.formSchema
})

watch(
  () => props.currentRow,
  (currentRow) => {
    if (!currentRow) return
    const { setValues } = methods
    setValues(currentRow)
  },
  {
    deep: true,
    immediate: true
  }
)

defineExpose({
  elFormRef,
  getFormData: methods.getFormData
})
</script>

<template>
  <Form :rules="rules" @register="register" />
</template>
