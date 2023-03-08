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
    <el-table :data="tableList.list" border stripe :height="tableHeight">
      <el-table-column prop="roleName" label="角色名"></el-table-column>
      <el-table-column prop="remark" label="角色描述"></el-table-column>
      <el-table-column prop="type" label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.type === 1" type="success" size="default">启用</el-tag>
          <el-tag v-if="scope.row.type === 0" type="danger" size="default">禁用</el-tag>
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
    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="roleList.pageNum"
                   :page-sizes="[1,5, 10, 40, 80, 100]" :page-size="roleList.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="roleList.total" background
    >
    </el-pagination>
    <InsertRole ref="insertRef" @refresh="refresh"></InsertRole>
  </el-main>
</template>

<script setup>
import {Search,Refresh,Plus,Edit,Delete} from '@element-plus/icons-vue';
import {nextTick, onMounted, reactive, ref} from "vue";
import {deleteRoleApi, selectRoleApi} from "../../api/role.js";
import {ElMessage} from "element-plus";
import InsertRole from "./InsertRole.vue";

//sizeChange
const sizeChange = (size) => {
  roleList.pageSize=size;
  getRoleList()
}
const currentChange = (page) => {
  roleList.pageNum=page
  getRoleList()
}
const roleList = reactive({
  roleName:'',
  pageNum:1,
  pageSize:5,
  total:0
})

//登录者id
const updateUserId=sessionStorage.getItem("loginUser")
//删除包装类
const deleteRoleForm = reactive({
  deleteUserId:0,
  roleId:0
})
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
const deleteBtn = async (row) => {
  deleteRoleForm.deleteUserId=updateUserId;
  deleteRoleForm.roleId=row.id;
  //调用删除用户api
  let res=await deleteRoleApi(deleteRoleForm);
  if (res &&res.code===200){
    ElMessage({
      message:res.msg,
      type:'success'
    })
    //删除成功后，进行刷新
    await getRoleList()
  }
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
    roleList.total=res.data.total
  }
}
//表格高度
const tableHeight=ref(0)
onMounted(()=>{
  getRoleList()
  nextTick(()=>{
    tableHeight.value=window.innerHeight-279
  })
})
</script>

<style scoped>

</style>