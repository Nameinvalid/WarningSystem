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
                path: '/user',
                name:'user',
                component:()=>import('@/view/system/User.vue'),
                meta: {
                    name:'用户管理',
                    icon:'User'
                }
            },
            {
                path: '/role',
                name:'role',
                component:()=>import('@/view/system/Role.vue'),
                meta: {
                    name:'角色管理',
                    icon:'User'
                }
            },
            {
                path: '/menu',
                name:'menu',
                component:()=>import('@/view/system/Menu.vue'),
                meta: {
                    name:'菜单管理',
                    icon:'User'
                }
            },
        ]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path: '/picture',
            name:'picture',
            component: ()=>import ('@/view/picture/Picture.vue'),
            meta:{
                name:'覆冰图片管理',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path:'/iceThickness',
            name:'iceThickness',
            component: ()=>import ('@/view/icethickness/IceThickness.vue'),
            meta:{
                name:'覆冰厚度预警',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path:'/warningLevel',
            name:'warningLevel',
            component: ()=>import ('@/view/warninglevel/WarningLevel.vue'),
            meta:{
                name:'预警等级管理',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path:'/weather',
            name:'weather',
            component: ()=>import ('@/view/weather/Weather.vue'),
            meta:{
                name:'气象数据管理',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path:'/mapIceCity',
            name:'MapIceCity',
            component: ()=>import ('@/view/mapicecity/Map.vue'),
            meta:{
                name:'城市覆冰分布图',
                icon:'house'
            }
        }]
    },
    {
        path: '/Index',
        component:Layout,
        children: [{
            path:'/model',
            name:'model',
            component: ()=>import ('@/view/modeltest/Model.vue'),
            meta:{
                name:'模型管理',
                icon:'house'
            }
        }]
    },
]

/**
 * 创建路由实例
 */

const router=createRouter({
    history:createWebHistory(),
    routes
})

export {router}