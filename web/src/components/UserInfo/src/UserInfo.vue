<script setup lang="ts">
import { ElDropdown, ElDropdownMenu, ElDropdownItem, ElMessageBox } from 'element-plus'
import { useI18n } from '@/hooks/web/useI18n'
import { useCache } from '@/hooks/web/useCache'
import { resetRouter } from '@/router'
import { useRouter } from 'vue-router'
import { loginOutApi } from '@/api/login'
import { useDesign } from '@/hooks/web/useDesign'
import { useTagsViewStore } from '@/store/modules/tagsView'

const tagsViewStore = useTagsViewStore()

const { getPrefixCls } = useDesign()

const prefixCls = getPrefixCls('sysUser-info')

const { t } = useI18n()

const { wsCache } = useCache('localStorage')

const { replace } = useRouter()

const loginOut = () => {
  ElMessageBox.confirm(t('sysCommon.loginOutMessage'), t('sysCommon.reminder'), {
    confirmButtonText: t('sysCommon.ok'),
    cancelButtonText: t('sysCommon.cancel'),
    type: 'warning'
  })
    .then(async () => {
      const res = await loginOutApi().catch(() => {})
      if (res) {
        wsCache.clear()
        tagsViewStore.delAllViews()
        resetRouter() // 重置静态路由表
        replace('/login')
      }
    })
    .catch(() => {})
}

const toDocument = () => {
  window.open('https://element-plus-admin-doc.cn/')
}
const userInfo = wsCache.get('userInfo') || ''
</script>

<template>
  <ElDropdown :class="prefixCls" trigger="click">
    <div class="flex items-center">
      <img
        src="@/assets/imgs/avatar.jpg"
        alt=""
        class="w-[calc(var(--logo-height)-25px)] rounded-[50%]"
      />
      <span class="<lg:hidden text-14px pl-[5px] text-[var(--top-header-text-color)]">{{
        userInfo.name
      }}</span>
    </div>
    <template #dropdown>
      <ElDropdownMenu>
        <ElDropdownItem>
          <div @click="toDocument">{{ t('sysCommon.document') }}</div>
        </ElDropdownItem>
        <ElDropdownItem divided>
          <div @click="loginOut">{{ t('sysCommon.loginOut') }}</div>
        </ElDropdownItem>
      </ElDropdownMenu>
    </template>
  </ElDropdown>
</template>
