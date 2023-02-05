import { createRouter, createWebHistory} from "vue-router"


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
        redirect:'/Index',
        children:[{
            path:'/Index',
            component:()=>import('@/view/Index.vue'),
            name:'index',
            meta:{
                name:'首页',
                icon:'house'
            }
        }]
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