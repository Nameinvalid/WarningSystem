<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="250px">
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            text-color="#fff"
            :collapse="isCollapse"
        >
          <el-menu-item index="1">
            <img src="@/assets/vue.svg"/>
            <template #title>
              配电线路覆冰监测系统
            </template>
          </el-menu-item>
          <el-menu-item index="2" @click="selectIndex('首页')">
            <el-icon><House /></el-icon>
            <template #title>首页</template>
          </el-menu-item>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><Menu /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="1-1" @click="selectUser('用户管理')">
              <el-icon><UserFilled /></el-icon>
              <span>用户管理</span>
            </el-menu-item>
            <el-menu-item index="1-2" @click="selectRole('角色管理')">
              <el-icon><Avatar /></el-icon>
              <span>角色管理</span>
            </el-menu-item>
            <el-menu-item index="1-3" @click="selectMenu('菜单管理')">
              <el-icon><Tickets /></el-icon>
              <span>菜单管理</span>
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="4" @click="selectPicture('覆冰图片管理')">
            <el-icon><PictureFilled /></el-icon>
            <template #title>覆冰图片管理</template>
          </el-menu-item>
          <el-menu-item index="5" @click="selectWarningLevel('预警等级管理')">
            <el-icon><Odometer /></el-icon>
            <template #title>预警等级管理</template>
          </el-menu-item>
          <el-menu-item index="6" @click="selectWeather('气象数据管理')">
            <el-icon><Drizzling /></el-icon>
            <template #title>气象数据管理</template>
          </el-menu-item>
          <el-menu-item index="7" @click="selectMapIceCity('城市覆冰分布图')">
            <el-icon><IceCream/></el-icon>
            <template #title>城市覆冰分布图</template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <el-row class="tac">
            <el-icon style="font-size: 30px;" @click="() => { isCollapse = !isCollapse }">
              <component :is="isCollapse ?  Expand:Fold"></component>
            </el-icon>
          </el-row>
          <h1>{{ user.username }}</h1>
          <el-tooltip class="box-item" effect="dark" placement="bottom">
            <template #content>
              <a href="#" class="exit" @click="exitLogin">退出登录</a>
            </template>
            <!--：src=显示本地图片的-->
            <el-avatar size="large" :src="image" shape="circle" fit="fill">
            </el-avatar>
          </el-tooltip>
        </el-header>
        <el-tabs
            v-model="editableTabsValue"
            type="card"
            class="demo-tabs"
            closable
            @tab-remove="removeTab"
        >
          <el-tab-pane
              v-for="item in editableTabs"
              :key="item.name"
              :name="item.name"
          >
            <template #label>
              <el-breadcrumb-item :to="{ path: '/' }">{{item.title}}</el-breadcrumb-item>
            </template>

          </el-tab-pane>
        </el-tabs>
        <el-main>
          <Main></Main>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import {onMounted, reactive, ref} from 'vue';
import image from '@/images/首页背景图片.jpg';

import { Expand, Fold } from '@element-plus/icons-vue'
import Main from "./main/Main.vue";

const router = useRouter()

const isCollapse = ref(false)

const user=reactive({
  username:''
})

const exitLogin = async () => {
  sessionStorage.clear();
  router.push("/")
}
const selectUser = async (targetName) => {
  router.push("/user")
  addTab(targetName)
}

const selectRole = async (targetName) => {
  router.push("/role")
  addTab(targetName)
}

const selectMenu = async (targetName) => {
  router.push("/menu")
  addTab(targetName)
}

const selectPicture = async (targetName) => {
  router.push("/picture")
  addTab(targetName)
}

const selectWarningLevel = async (targetName) => {
  router.push("/warningLevel")
  addTab(targetName)
}
const selectWeather= async (targetName) => {
  router.push("/weather")
  addTab(targetName)
}

const selectMapIceCity = async (targetName) => {
  router.push("/mapIceCity")
  addTab(targetName)
}

const selectIndex = async (targetName) => {
  router.push("/Index")
  addTab(targetName)
}
onMounted(() => {
  user.username=sessionStorage.getItem("username")
})
const editableTabs = ref([{
  title: '首页',
  name: '1',
  path:'/Index'
},]);
const addTab = (targetName) => {
  const newTabName = `${++tabIndex}`
  editableTabs.value.push({
    title: targetName,
    name: newTabName,
  })
  editableTabsValue.value = newTabName
}
let tabIndex = 1
const editableTabsValue = ref('1')
const removeTab = (targetName) => {
  const tabs = editableTabs.value
  let activeName = editableTabsValue.value
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.name === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1]
        if (nextTab) {
          activeName = nextTab.name
        }
      }
    })
  }
  editableTabsValue.value = activeName
  editableTabs.value = tabs.filter((tab) => tab.name !== targetName)
}
</script>

<style lang="scss" scope>
.common-layout{
  height: 100vh;
  display: flex;
}
.el-header{
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
  background-color: #009688;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 40px;
}
.el-aside {
  background-color:#545c64;
  text-align: center;
  line-height: 200px;
  width: fit-content;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 250px;
  min-height: 400px;
  border-right: solid 1px #545c64;
}
.el-menu-vertical-demo{
  border-right: solid 1px #545c64;
}

.exit {
  text-decoration: none;
  color: white;
  line-height: 100%;
  height: 100%;
  width: 100%;
}
</style>