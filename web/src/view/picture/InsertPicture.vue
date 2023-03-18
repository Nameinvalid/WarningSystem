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
        <el-col :span="12">
          <el-form-item prop="remark" label="图片描述">
            <el-input v-model="updatePhotoForm.remark"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item prop="position" label-width="14.5vh" label="拍摄图片的地址">
            <el-input v-model="updatePhotoForm.position"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item prop="picture" label="上传图片"></el-form-item>
          <el-upload
              ref="photoRef"
              class="upload-demo"
              drag
              :limit="1"
              action="http://localhost:8080/upload/picture"
              multiple
              :auto-upload="false"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :before-upload="beforeAvatarUpload"
              :on-success="handleAvatarSuccess"
              :on-error="handleAvatarError"
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              移动到这里或者 <em>点击上传</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                图片上传大小不能超过2M
              </div>
            </template>
          </el-upload>
          <el-dialog v-model="dialogVisible">
            <img w-full :src="dialogImageUrl" alt="Preview Image" />
          </el-dialog>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onSubmitPicture">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import useInstance from "../../hooks/useInstance.js";
import {nextTick, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {insertPictureAPI} from "../../api/coreFuctionAPI/picture";
import {updatePictureAPI} from "../../api/coreFuctionAPI/picture.js";
const updateUserId=sessionStorage.getItem("loginUser")
const { global } = useInstance()
const dialog = reactive({
  visible:false,
  title:'标题'
})

const dialogVisible = ref(false)
const dialogImageUrl = ref('')

let photoRef = ref()
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true
}
//检测修改时是否要上传文件
//0是不需要上传文件，1是需要
let uploadPhoto=ref(0)
const onSubmitPicture = () => {
  photoRef.value.submit()
  if (dialog.title==='修改'){
    if (uploadPhoto.value===0){
      onConfirm()
    }
  }
}
const handleAvatarSuccess = (uploadFile) => {
  if (uploadFile.code===200){
    updatePhotoForm.url=uploadFile.data
    onConfirm()
  }else {
    ElMessage.error(uploadFile.message)
  }
  photoRef.value.clearFiles();
}
const handleAvatarError = (uploadFile) => {
  ElMessage.error("上传照片"+uploadFile.name+"失败")
}

const beforeAvatarUpload = (rawFile) => {
  if (dialog.title==='修改'){
    //说明需要上传文件，并且是修改窗口
    uploadPhoto.value=1
  }
  if (rawFile.type !== 'image/jpeg' && rawFile.type!=='image/png') {
    ElMessage.error('照片的格式不是jpg或者png的格式！')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('文件大小不能找过2MB！')
    return false
  }
  return true
}
const photoForm=ref()
const updatePhotoForm =reactive({
  photoName:'',
  remark:'',
  position:'',
  url:'',
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
      message:"新增照片",
      type:'success'
    })
    dialog.title='新增'
  }
  photoForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  updatePhotoForm.photoId=0
  uploadPhoto.value=0
  onShow()
}

const onShow = () => {
  dialog.visible=true
}
const onConfirm = () => {
  //写需要提交给后台的属性，分清是修改还是新增
  photoForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updatePhotoForm.updateUserId=updateUserId
      if (updatePhotoForm.photoId===0){
        //新增
        res= await insertPictureAPI(updatePhotoForm)
      }else {
        //修改
        res= await updatePictureAPI(updatePhotoForm)
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