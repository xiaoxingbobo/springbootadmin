// 注册一个全局自定义指令 `v-hasPermission`
import { store } from '@/store'

export default function (app: any) {
  app.directive('hasPermission', {
    mounted(el: any, binding: any) {
      const { value } = binding
      const usersPermissions = store.state.value.permission._roleAuthority
      const f = usersPermissions.some((item: any) => {
        //some会循环所有元素，如果有一个元素满足则返回true并跳出循环，如果都没有则返回false
        return item.indexOf(value) !== -1
      })
      if (!f) {
        ///如果不满足则通过dom元素销毁这个按钮
        el.parentNode && el.parentNode.removeChild(el)
      }
    }
  })
}
