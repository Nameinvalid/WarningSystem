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
          <el-menu-item index="2" @click="selectIndex">
            <el-icon><House /></el-icon>
            <template #title>首页</template>
          </el-menu-item>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><Menu /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="1-1" @click="selectUser">
              <el-icon><UserFilled /></el-icon>
              <span>用户管理</span>
            </el-menu-item>
            <el-menu-item index="1-2" @click="selectRole">
              <el-icon><Avatar /></el-icon>
              <span>角色管理</span>
            </el-menu-item>
            <el-menu-item index="1-3" @click="selectMenu">
              <el-icon><Tickets /></el-icon>
              <span>菜单管理</span>
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="4" @click="selectPicture">
            <el-icon><PictureFilled /></el-icon>
            <template #title>覆冰图片管理</template>
          </el-menu-item>
          <el-menu-item index="5" @click="selectWarningLevel">
            <el-icon><Odometer /></el-icon>
            <template #title>预警等级管理</template>
          </el-menu-item>
          <el-menu-item index="6" @click="selectWeather">
            <el-icon><Drizzling /></el-icon>
            <template #title>气象数据管理</template>
          </el-menu-item>
          <el-menu-item index="7" @click="selectMapIceCity">
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
        </el-header>
        <el-main>
          <Main></Main>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import {onMounted, ref} from 'vue';

import { Expand, Fold } from '@element-plus/icons-vue'
import Main from "./main/Main.vue";

const router = useRouter()

const isCollapse = ref(false)

const selectUser = async () => {
  router.push("/user")
}

const selectRole = async () => {
  router.push("/role")
}

const selectMenu = async () => {
  router.push("/menu")
}

const selectPicture = async () => {
  router.push("/picture")
}

const selectWarningLevel = async () => {
  router.push("/warningLevel")
}
const selectWeather= async () => {
  router.push("/weather")
}

const selectMapIceCity = async () => {
  router.push("/mapIceCity")
}

const selectIndex = async () => {
  console.log(sessionStorage.getItem("login"))
  router.push("/Index")
}
onMounted(() => {

})
</script>

<style scoped>
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
</style>