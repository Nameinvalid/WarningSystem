<template>
  <el-main>
    <el-form :model="roleList" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="roleList.roleName" placeholder="请输入角色名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search" >搜索</el-button>
        <el-button @click="resetBtn" type="danger" plain :icon="Refresh" >重置</el-button>
        <el-button type="primary" @click="insertBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <InsertRole ref="insertRef" @refresh="refresh"></InsertRole>
  </el-main>
</template>

<script setup>
import {Search,Refresh,Plus} from '@element-plus/icons-vue';
import {onMounted, reactive, ref} from "vue";
import {selectRoleApi} from "../../api/role.js";
import {ElMessage} from "element-plus";
import InsertRole from "./InsertRole.vue";


const roleList = reactive({
  roleName:'',
  pageNum:1,
  pageSize:20
})

//登录者id
const updateUserId=sessionStorage.getItem("loginUser")

//后端返回的包装类
const tableList= reactive({
  list:[]
})
const searchBtn = async () => {
  await getRoleList("搜索成功")
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
//刷新列表
const refresh = () => {
  getRoleList()
}
const resetBtn =  async () => {
  roleList.roleName=''
  await getRoleList()
}
const getRoleList = async (string) => {
  let res=await selectRoleApi(roleList)
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
  getRoleList()
})
</script>

<style scoped>

</style>