import { createRouter, createWebHistory} from "vue-router"
import Layout from '@/view/Index.vue'

/**
 * 创建路由表
 */
const routes=[
    {
        path:'/',
        name:'Login',
        component: ()=>import ('@/login/Login.vue')
    },
    {
        path:'/insertUser',
        name:'insertUser',
        component: ()=>import ('@/login/InsertUser.vue')
    },
    {
        path: '/Index',
        component: Layout,
        redirect:'/Index',
        children:[{
            path:'/Index',
            component:()=>import('@/view/index/Index.vue'),
            name:'index',
            meta:{
                name:'首页',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        redirect:'/System',
        children: [
            {
                path: '/User',
                name:'user',
                component:()=>import('@/view/system/User.vue'),
                meta: {
                    name:'用户管理',
                    icon:'User'
                }
            }
        ]
    }
]

/**
 * 创建路由实例
 */

const router=createRouter({
    history:createWebHistory(),
    routes
})

export {router}