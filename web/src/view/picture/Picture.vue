<template>
  <el-main>
    <el-form :model="photoList" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="photoList.menuName" placeholder="请输入图片名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBtn" :icon="Search" >搜索</el-button>
        <el-button @click="resetBtn" type="danger" plain :icon="Refresh" >重置</el-button>
        <el-button type="primary" @click="insertBtn" :icon="Plus">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableList.list" border stripe :height="tableHeight">
      <el-table-column prop="photoName" label="图片名称"></el-table-column>
      <el-table-column prop="remark" label="角色描述"></el-table-column>
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button type="primary" :icon="Edit" size="default" @click="editBtn(scope.row)" >编辑
          </el-button>
          <el-button type="danger" :icon="Delete" size="default" @click="deleteBtn(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="photoList.pageNum"
                   :page-sizes="[1,5, 10, 40, 80, 100]" :page-size="photoList.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="photoList.total" background
    >
    </el-pagination>
    <InsertPicture ref="insertRef" @refresh="refresh"></InsertPicture>
  </el-main>
</template>

<script setup>
import {Search,Refresh,Plus,Edit,Delete} from '@element-plus/icons-vue';
import {nextTick, onMounted, reactive, ref} from "vue";
import {deletePictureAPI, selectPictureAPI} from "../../api/coreFuctionAPI/picture.js";
import {ElMessage} from "element-plus";
import InsertPicture from "./InsertPicture.vue";
//sizeChange
const sizeChange = (size) => {
  photoList.pageSize=size;
  getPictureList()
}
const currentChange = (page) => {
  photoList.pageNum=page
  getPictureList()
}
const photoList = reactive({
  photoName:'',
  pageNum:1,
  pageSize:5,
  total:0
})
const tableList= reactive({
  list:[]
})
//删除包装类
const deletePictureForm = reactive({
  deleteUserId:0,
  photoId:0
})
//登录者id
const updateUserId=sessionStorage.getItem("loginUser")
const getPictureList = async (string) => {
  let res=await selectPictureAPI(photoList)
  if (res && res.code===200){
    if (typeof string !== "undefined"){
      ElMessage({
        message:string,
        type:'success'
      })
    }else {
      ElMessage({
        message:'查询成功',
        type:'success'
      })
    }
    tableList.list=res.data.records
    photoList.total=res.data.total
  }
}
//编辑按钮
const editBtn = async (row) => {
  //编辑的时候弹出新增框
  insertRef.value.show(row)
}
const deleteBtn = async (row) => {
  deletePictureForm.deleteUserId=updateUserId;
  deletePictureForm.photoId=row.id;
  //调用删除用户api
  let res=await deletePictureAPI(deletePictureForm);
  if (res &&res.code===200){
    ElMessage({
      message:res.msg,
      type:'success'
    })
    //删除成功后，进行刷新
    await getPictureList()
  }
}
const searchBtn = async () => {
  await getPictureList("搜索成功")
}
const resetBtn = async () => {
  photoList.photoName=''
  await getPictureList("重置成功")
}
const insertRef=ref();
const insertBtn = () => {
  insertRef.value.show()
}
//刷新列表
const refresh = () => {
  getPictureList()
}
const tableHeight = ref(0)
onMounted(()=>{
  getPictureList()
  nextTick(()=>{
    tableHeight.value=window.innerHeight-279
  })
})
</script>

<style scoped>

</style>