<template>
    <div class="selectLogin-page">
      <el-card class="box-card">
        <div class="clearfix">
          <span class="selectLogin-title">🔐覆冰预警监视后台管理系统</span>
        </div>
        <div class="selectLogin-main">
          <el-form :model="loginForm" label-width="120px">
            <el-form-item label="用户名">
              <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="UserFilled"/>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="key" show-password/>
            </el-form-item>
            <el-button type="primary" class="selectLogin-button" @click="selectLogin">登录</el-button>
            <el-button class="insert-button" type="info" @click="insertUser">注册</el-button>
          </el-form>
        </div>
      </el-card>
    </div>
  </template>

<script setup>
import { reactive,onMounted } from 'vue';
import { loginApi } from "../api/login.js";
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter()

const loginForm =reactive({
    username: '',
    password: '',
})

const selectLogin=async()=>{
    let res=await loginApi(loginForm)
    if(res.data!==null && res.code===200){
      ElMessage({
        message:res.msg,
        type:'success'
      })
      sessionStorage.setItem("login",res.data)
      router.push('/Index')
      /*let resMenu= await selectMenuAPI()
      if(resMenu.data!==null&&resMenu.code===200){
        sessionStorage.setItem("menu",res.data)
      }
      router.push('/Index')
    }else {
      ElMessage(res.code)
      router.push('/')
    }*/
}}
const insertUser=async ()=>{
  router.push('/insertUser')
}

onMounted(() => {

})
</script>

<style lang="scss" scoped>
.selectLogin-page{
  background: url("https://img-qn.51miz.com/preview/element/00/01/27/03/E-1270347-B7853F8A.jpg") no-repeat;
  background-size: cover;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.selectLogin-title{
  padding-left: 5vh;
  text-align:center;
}
.selectLogin-main{
  margin-top: 30px;
  margin-left: -60px;
}

.selectLogin-button{
  margin-left: 13vh;
}
.insert-button{
  margin-left: 8vh;
}
.box-card {
  width: 350px;
  z-index: 100;
}
</style>