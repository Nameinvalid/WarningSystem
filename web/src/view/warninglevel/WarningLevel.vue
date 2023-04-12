<template>
  <el-main>
    <el-form :model="gradeList" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="gradeList.iceGrade" placeholder="请输入覆冰等级"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search" >搜索</el-button>
        <el-button @click="resetBtn" type="danger" plain :icon="Refresh" >重置</el-button>
        <el-button type="primary" @click="insertBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格内容 -->
    <el-table :data="tableList.list" border stripe :height="tableHeight">
      <el-table-column prop="srcThickness" label="起始厚度"></el-table-column>
      <el-table-column prop="distThickness" label="最终厚度"></el-table-column>
      <el-table-column prop="iceGrade" label="覆冰等级"></el-table-column>
      <el-table-column prop="gradeColor" label="等级颜色"></el-table-column>
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button type="primary" :icon="Edit" size="default" @click="editBtn(scope.row)" >编辑
          </el-button>
          <el-button type="danger" :icon="Delete" size="default" @click="deleteBtn(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <InsertWaringLevel ref="insertRef" @refresh="refresh"></InsertWaringLevel>
  </el-main>
</template>

<script setup>

import {nextTick, onMounted, reactive, ref} from "vue";
import {Search,Refresh,Plus,Edit,Delete} from '@element-plus/icons-vue';
import InsertWaringLevel from "./InsertWaringLevel.vue";
import {ElMessage} from "element-plus";
import {deleteWaringLevel, selectWaringLevel} from "../../api/coreFuctionAPI/grade.js";

const  gradeList= reactive({
  iceGrade:'',
})
//后端返回的包装类
const tableList= reactive({
  list:[]
})
//删除包装类
const deleteGradeForm = reactive({
  deleteUserId:0,
  iceGradeId:0
})

//登录者id
const updateUserId=sessionStorage.getItem("loginUser")
const getIceGradeList = async (string) => {
  let res= await selectWaringLevel(gradeList)
  if (res && res.data){
    if (typeof string != 'undefined'){
      ElMessage({
        message:string,
        type:'success'
      })
    }
    tableList.list=res.data
  }
}
const searchBtn = async () => {
  await getIceGradeList("搜索成功")
}
const resetBtn = async () => {
  gradeList.grade=''
  await getIceGradeList()
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
  deleteGradeForm.deleteUserId=updateUserId;
  deleteGradeForm.iceGradeId=row.id;
  //调用删除用户api
  let res=await deleteWaringLevel(deleteGradeForm);
  if (res &&res.code===200){
    ElMessage({
      message:res.msg,
      type:'success'
    })
    //删除成功后，进行刷新
  await getIceGradeList()
  }
}
//刷新列表
const refresh = () => {
  getIceGradeList()
}

//表格高度
const tableHeight=ref(0)

onMounted(()=>{
  getIceGradeList()
  nextTick(()=>{
    tableHeight.value=window.innerHeight-279
  })
})
</script>

<style scoped>

</style>