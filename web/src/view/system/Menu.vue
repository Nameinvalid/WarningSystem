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
import {Search,Refresh,Plus} from '@element-plus/icons-vue';
import InsertMenu from "./InsertMenu.vue";
import {selectMenuAPI} from "../../api/menu.js";
import {ElMessage} from "element-plus";

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
  pageNum:1,
  pageSize:5,
  total:0
})
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
const getMenuList= async (string)=> {
  let res= await selectMenuAPI(menuList)
  if (res && res.code===200){
    if (typeof string != 'undefined'){
      ElMessage({
        message:string,
        type:'success'
      })
    }
    tableList.list=res.data.appRecords
    menuList.total=res.data.total
  }
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