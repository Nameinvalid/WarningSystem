<template>
  <div class="insert-user">
    <el-card class="box-card">
      <div class="title">
        <p>新用户注册</p>
      </div>
      <div class="rg-center">
        <div class="rg-form">
          <el-form :model="userForm" label-width="90px">
            <el-form-item label="用户名">
              <el-input v-model="userForm.username" placeholder="请输入用户名" prefix-icon="UserFilled"/>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="userForm.password" placeholder="请输入密码" prefix-icon="key" show-password/>
            </el-form-item>
            <el-form-item label="手机号">
              <el-input v-model="userForm.phone" placeholder="请输入手机号" prefix-icon="Iphone"/>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="userForm.sex">
                <el-radio label="男"/>
                <el-radio label="女"/>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="真实名字">
              <el-input v-model="userForm.name" placeholder="请输入身份证名字" prefix-icon="User"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="insertUser">提交</el-button>
              <el-button type="info" class="return-login" @click="returnLogin">返回登录页面</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { insertUserApi } from "../api/user.js";
import { reactive,onMounted } from 'vue';
import { ElMessage } from "element-plus";

const router = useRouter()
const userForm = reactive({
  username:'',
  password:'',
  phone:'',
  name:'',
  sex:''
})
const insertUser = async () => {
  if (userForm.sex==='男'){
    userForm.sex=1;
  }else if(userForm.sex==='女'){
    userForm.sex=0
  }
  let res=await insertUserApi(userForm)
  if(res||res===200){
    ElMessage.success("注册成功，即将返回登录页面进行登录")
    router.push('/')
  }else {
    ElMessage.error("注册失败，请重新注册")
    router.push('/insertUser')
  }
}

const returnLogin = async () => {
  router.push("/")
}

onMounted(()=>{

})
</script>

<style scoped>

.insert-user{
  background: url("https://img-qn.51miz.com/preview/element/00/01/27/03/E-1270347-B7853F8A.jpg") no-repeat;
  background-size: cover;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.return-login{
  margin-left: 10vh;
}
.box-card {
  width: 500px;
  z-index: 100;
}
.title{
  text-align: center;
}
</style>