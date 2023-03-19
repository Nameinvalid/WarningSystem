<template>
  <div class="layout">
    <div class="top">
      <div class="text-image">
        覆冰预警监视后台管理系统
      </div>
    </div>
    <div class="selectLogin-page">
      <el-card class="box-card">
        <div class="clearfix">
          <span class="selectLogin-title">🔐覆冰预警监视后台管理系统</span>
        </div>
        <div class="selectLogin-main">
          <el-form :model="loginForm" label-width="120px">
            <el-form-item>
              <template #label>
                <div style="color: white">用户名</div>
              </template>
              <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="UserFilled"/>
            </el-form-item>
            <el-form-item>
              <template #label>
                <div style="color: white">密码</div>
              </template>
              <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="key" show-password/>
            </el-form-item>
            <el-button type="primary" class="selectLogin-button" @click="selectLogin">登录</el-button>
            <el-button class="insert-button" type="info" @click="insertUser">注册</el-button>
          </el-form>
        </div>
      </el-card>
    </div>
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
      ElMessage({
        message:"欢迎"+res.data.username+"使用本系统",
        type:'success'
      })
      sessionStorage.setItem("username",res.data.username)
      sessionStorage.setItem("loginUser",res.data.id)
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
.top{
  width: 100%;
  height: 10vh;
  background: url(../images/头部.png) no-repeat;
}
.text-image{
  padding-top: 3vh;
  color: white;
  width: 100%;
  height: 100vh;
  text-align: center;
  z-index: 1;
}
.selectLogin-page{
  background-size: cover;
  height: 90vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.layout{
  background: url(../images/背景图.png) no-repeat;
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
  color: white;
  background-color: #015d86;
  border: 3px solid #3590e7;
  width: 350px;
  z-index: 100;
}
</style>