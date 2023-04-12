<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
    <el-form :model="updateGradeForm" ref="gradeForm" label-width="80px" size="default" >
      <el-row>
        <el-col :span="12">
          <el-form-item prop="srcThickness" label="起始厚度">
            <el-input v-model="updateGradeForm.srcThickness"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="distThickness" label="最终厚度">
            <el-input v-model="updateGradeForm.distThickness"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item prop="iceGrade" label="覆冰等级">
            <el-input v-model="updateGradeForm.iceGrade"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="gradeColor" label="颜色">
            <el-input v-model="updateGradeForm.gradeColor"></el-input>
          </el-form-item>
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
import {insertWaringLevel, updateWaringLevel} from "../../api/coreFuctionAPI/grade.js";

const { global } = useInstance()
const dialog = reactive({
  visible:false,
  title:'标题'
})
const updateGradeForm =reactive({
  srcThickness:'',
  distThickness:'',
  iceGrade:'',
  gradeColor:'',
  type:3,
  iceGradeId:0,//判断是新增还是修改
  updateUserId:0
})
const updateUserId=sessionStorage.getItem("loginUser")
const gradeForm=ref()
//加载父组件传过来的参数，并把弹窗打开
const show = async (row) => {
  if (row){
    ElMessage({
      message:"对 "+row.iceGrade+" 等级进行修改",
      type:'success'
    })
    //父组件传过来的参数
    //不能添加await，添加了await之后userForm.value?.resetFields()就不会生效，算是一个时机问题
    nextTick(() => {
      global.$objCopy(row,updateGradeForm)
      //如果是修改的话，会有一个userId，新增的时候，userId会是0
      updateGradeForm.iceGradeId=row.id
      dialog.title='修改'
    })
  }else {
    ElMessage({
      message:"新增角色",
      type:'success'
    })
    dialog.title='新增'
  }
  gradeForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  updateGradeForm.iceGradeId=0;
  onShow()
}
const onConfirm = () => {
  //写需要提交给后台的属性，分清是修改还是新增
  gradeForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updateGradeForm.updateUserId=updateUserId
      if (updateGradeForm.iceGradeId===0){
        //新增
        res=await insertWaringLevel(updateGradeForm)
      }else {
        //修改
        res= await updateWaringLevel(updateGradeForm)
      }
      if (res &&res.code===200){
        ElMessage.success(res.msg)
        emits('refresh')
        onClose()
      }
    }
  })
}
const onShow = () => {
  dialog.visible=true
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