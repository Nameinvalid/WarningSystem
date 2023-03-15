<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
    <el-form :model="updatePhotoForm" ref="photoForm" label-width="80px" size="default" >
      <el-row>
        <el-col :span="12">
          <el-form-item prop="photoName" label="图片名称">
            <el-input v-model="updatePhotoForm.photoName"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item prop="picture" label="上传图片"></el-form-item>
          <el-upload
              class="upload-demo"
              drag
              action=""
              multiple
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              移动到这里或者 <em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                图片上传大小不能超过500M
              </div>
            </template>
          </el-upload>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import useInstance from "../../hooks/useInstance.js";
import {nextTick, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
const updateUserId=sessionStorage.getItem("loginUser")
const { global } = useInstance()
const dialog = reactive({
  visible:false,
  title:'标题'
})

const photoForm=ref()
const updatePhotoForm =reactive({
  photoName:'',
  photoId:0,//判断是新增还是修改
  updateUserId:0,
})
const show = async (row) => {
  if (row){
    ElMessage({
      message:"对 "+row.photoName+" 菜单进行修改",
      type:'success'
    })
    //父组件传过来的参数
    //不能添加await，添加了await之后userForm.value?.resetFields()就不会生效，算是一个时机问题
    nextTick(() => {
      global.$objCopy(row,updatePhotoForm)
      //如果是修改的话，会有一个userId，新增的时候，userId会是0
      updatePhotoForm.photoId=row.id
      dialog.title='修改'
    })
  }else {
    ElMessage({
      message:"新增角色",
      type:'success'
    })
    dialog.title='新增'
  }
  photoForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  onShow()
}

const onShow = () => {
  dialog.visible=true
}
const onConfirm = () => {
  updatePhotoForm.updateUserId=updateUserId
  console.log("新增",updatePhotoForm.updateUserId)
  onClose()
  //写需要提交给后台的属性，分清是修改还是新增
  photoForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updatePhotoForm.updateUserId=updateUserId
      if (updatePhotoForm.photoId===0){
        //新增
        //res=await insertMenuAPI(updateMenuForm)
        console.log("新增",updatePhotoForm.updateUserId)
      }else {
        //修改
        //res= await updateMenuAPI(updateMenuForm)
        console.log("修改",updatePhotoForm.updateUserId)
      }
      if (res &&res.code===200){
        ElMessage.success(res.msg)
        emits('refresh')
        onClose()
      }
    }
  })
}

//弹框关闭按钮
const onClose = () => {
  dialog.visible=false
}
//暴露出show函数给父组件使用
defineExpose({
  show
})

//注册事件
const emits = defineEmits(['refresh'])
</script>

<style lang="scss" scope>
.el-dialog {
  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;

  .el-dialog__header {
    margin-right: 0;
    border-top-left-radius: 7px !important;
    border-top-right-radius: 7px !important;
    background-color: #009688 !important;

    .el-dialog__title {
      color: #fff;
      font-size: 16px;
      font-weight: 600;
    }

  }

  .el-dialog__headerbtn {

    .el-dialog__close {
      color: #fff;
    }

  }

  .el-dialog__body {
    padding: 10px;
  }

  .el-dialog__footer {
    border-top: 1px solid #e8eaec !important;
    padding: 10px;
  }

}
</style>