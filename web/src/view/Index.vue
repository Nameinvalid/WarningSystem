<template>
  <div class="common-layout">
    <el-container>
      <el-aside class="aside" width="200px">
        <el-row>
          <el-col>
            <el-menu
                active-text-color="#ffd04b"
                background-color="#545c64"
                class="menu"
                default-active="2"
                text-color="#fff"
            >
              <el-menu-item index="1">
                <template #title>
                  <el-icon><House /></el-icon>
                  <span>首页</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-container>
        <el-header class="header">Header</el-header>
        <el-main class="main">Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { reactive,onMounted } from 'vue';
import {ElMessage} from 'element-plus';

const router = useRouter()
console.log(sessionStorage.getItem("menu"))

onMounted(() => {

})
</script>

<style scoped>
.aside{
  background: aquamarine;
}
.header{
  background: bisque;
}
.main{
  background: chocolate;
}
</style>