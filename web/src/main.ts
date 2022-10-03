import { createApp } from 'vue'
import './style.css'
//引入
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


import App from './App.vue'



const app = createApp(App)

//注册
app.use(ElementPlus)





app.mount('#app')