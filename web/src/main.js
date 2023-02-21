import { createApp } from 'vue'
import {router} from './router'
import objCopy from './util/objCopy'
import myconFirm from './util/myconfirm'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'

import * as ELIcons from '@element-plus/icons-vue'
import App from './App.vue'



const app = createApp(App)

for (let iconName in ELIcons) {
    app.component(iconName, ELIcons[iconName])
}
app.use(ElementPlus)
app.use(router)
app.mount('#app')

//全局挂载使用
app.config.globalProperties.$myconfirm = myconFirm
app.config.globalProperties.$objCopy = objCopy



