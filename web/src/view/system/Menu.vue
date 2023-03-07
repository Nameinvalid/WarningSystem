<template>
  <el-main>
    <el-form :model="menuList" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="menuList.menuName" placeholder="请输入菜单名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search" >搜索</el-button>
        <el-button @click="resetBtn" type="danger" plain :icon="Refresh" >重置</el-button>
        <el-button type="primary" @click="insertBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableList.list" border stripe :height="tableHeight">
      <el-table-column prop="parentMenuName" label="父级菜单名">
        <template #default="scope">
          <el-tag v-if="null===scope.row.parentMenuName || ''===scope.row.parentMenuName" type="success" size="default">无</el-tag>
          <el-tag v-else>{{scope.row.parentMenuName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="menuName" label="菜单名"></el-table-column>
      <el-table-column prop="menuIndex" label="菜单序号"></el-table-column>
      <el-table-column prop="menuGrade" label="菜单等级">
        <template #default="scope">
          <el-tag type="success" size="default">{{scope.row.menuGrade}}级菜单</el-tag>
        </template>
      </el-table-column>
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
    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="menuList.pageNum"
                   :page-sizes="[1,5, 10, 40, 80, 100]" :page-size="menuList.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="menuList.total" background
    >
    </el-pagination>
    <InsertMenu ref="insertRef" @refresh="refresh"></InsertMenu>
  </el-main>
</template>

<script setup>

import {nextTick, onMounted, reactive, ref} from "vue";
import {Search,Refresh,Plus,Edit,Delete} from '@element-plus/icons-vue';
import InsertMenu from "./InsertMenu.vue";
import {selectMenuAPI} from "../../api/menu.js";
import {ElMessage} from "element-plus";
import {deleteMenuAPI} from "../../api/menu";

//sizeChange
const sizeChange = (size) => {
  menuList.pageSize=size;
  getMenuList()
}
const currentChange = (page) => {
  menuList.pageNum=page
  getMenuList()
}

const menuList = reactive({
  menuName:'',
  menuGrade:'',
  pageNum:1,
  pageSize:5,
  total:0
})
//删除包装类
const deleteMenuForm = reactive({
  deleteUserId:0,
  menuId:0
})
const indexToString = (index) => {
  switch (index) {
    case 1:
      return '一'
    case 2:
      return '二';
    case 3:
      return '三';
    case 4:
      return '四';
  }
}

//登录者id
const updateUserId=sessionStorage.getItem("loginUser")
//后端返回的包装类
const tableList= reactive({
  list:[]
})

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
  deleteMenuForm.deleteUserId=updateUserId;
  deleteMenuForm.menuId=row.id;
  //调用删除用户api
  let res=await deleteMenuAPI(deleteMenuForm);
  if (res &&res.code===200){
    ElMessage({
      message:res.msg,
      type:'success'
    })
    //删除成功后，进行刷新
    await getMenuList()
  }
}
const getMenuList= async (string)=> {
  let res= await selectMenuAPI(menuList)
  if (res && res.code===200){
    if (typeof string != 'undefined'){
      ElMessage({
        message:string,
        type:'success'
      })
    }
    tableList.list=res.data.records
    menuList.total=res.data.total
    for (let i = 0; i < tableList.list.length; i++) {
      tableList.list[i].menuGrade=indexToString(tableList.list[i].menuGrade);
    }
  }
  console.log(tableList.list)
}

const searchBtn = async () => {
  await getMenuList("搜索成功")
}

//刷新列表
const refresh = () => {
  getMenuList()
}
const resetBtn = async () => {
  menuList.menuName=''
  await getMenuList()
}

const tableHeight = ref(0)
onMounted(()=>{
  getMenuList()
  nextTick(()=>{
    tableHeight.value=window.innerHeight-230
  })
})
</script>

<style scoped>

</style>