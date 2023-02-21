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
    <!-- 表格内容 -->
    <el-table :data="tableList.list" border stripe >
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.type === 1" type="success" size="default">启用</el-tag>
          <el-tag v-if="scope.row.type === 0" type="danger" size="default">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sex" label="性别">
        <template #default="scope">
          <el-tag v-if="scope.row.sex === 1" type="success" size="default">男</el-tag>
          <el-tag v-if="scope.row.sex === 0" type="danger" size="default">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button type="primary" :icon="Edit" size="default" @click="editBtn(scope.row)" >编辑
          </el-button>
          <el-button type="danger" :icon="Delete" size="default" @click="deleteBtn(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <InsertUser ref="insertRef"></InsertUser>
  </el-main>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {selectUserApi,deleteUserApi} from "../../api/user.js";
import {Search,Refresh,Plus,Edit,Delete} from '@element-plus/icons-vue';
import InsertUser from "./InsertUser.vue";
import {ElMessage} from "element-plus";
//查询包装类
const  userList= reactive({
  phone:'',
  name:'',
  pageNum:1,
  pageSize:20
})
//删除包装类
const deleteUserForm = reactive({
  deleteUserId:0,
  userId:0
})
//后端返回的包装类
const tableList= reactive({
  list:[]
})
//登录者id
const updateUserId=sessionStorage.getItem("loginUser")
//模糊查询按钮
const searchBtn = async () => {
  await getUserList("搜索成功")
}
//新增按钮，弹出弹框，调用子组件的show方法
const insertRef=ref();
const insertBtn = () => {
  insertRef.value.show()
}
//编辑按钮
const editBtn = async (row) => {
  //编辑的时候弹出新增框
  insertRef.value.show(row)
}
//删除按钮
const deleteBtn = async (row) => {
  deleteUserForm.deleteUserId=updateUserId;
  deleteUserForm.userId=row.id;
  //调用删除用户api
  let res=await deleteUserApi(deleteUserForm);
  if (res &&res.code===200){
    ElMessage({
      message:res.msg,
      type:'success'
    })
    //删除成功后，进行刷新
    await getUserList()
  }
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
    tableList.list=res.data.records
  }
}

onMounted(()=>{
  getUserList()
})
</script>

<style scoped>

</style>