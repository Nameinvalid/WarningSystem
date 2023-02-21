<template>
  <el-main>
    <el-form :model="userList" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="userList.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="userList.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search" >搜索</el-button>
        <el-button @click="resetBtn" type="danger" plain :icon="Refresh" >重置</el-button>
        <el-button type="primary" @click="insertBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <InsertUser ref="insertRef"></InsertUser>
  </el-main>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {selectUserApi} from "../../api/user.js";
import {Search,Refresh,Plus} from '@element-plus/icons-vue';
import InsertUser from "./InsertUser.vue";
import {ElMessage} from "element-plus";
const  userList= reactive({
  phone:'',
  name:'',
  pageNum:1,
  pageSize:20
})
const searchBtn = async () => {
  await getUserList("搜索成功")
}
//新增按钮，弹出弹框，调用子组件的show方法
const insertRef=ref();
const insertBtn = () => {
  insertRef.value.show(userList)
}


const resetBtn = async () => {
  userList.name='';
  userList.phone='';
  await getUserList()
}
const getUserList = async (string) => {
  let res= await selectUserApi(userList)
  if (res && res.code===200){
    if (typeof string != 'undefined'){
      ElMessage({
        message:string,
        type:'success'
      })
    }
    console.log(res.data)
  }
}

onMounted(()=>{
  getUserList()
})
</script>

<style scoped>

</style>