<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
      <el-form :model="updateUserForm" label-width="80px" size="default">
        <el-row>
          <el-col :span="12">
            <el-form-item prop="name" label="姓名">
              <el-input v-model="updateUserForm.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="phone" label="电话">
              <el-input v-model="updateUserForm.phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item prop="type" label="状态">
              <el-radio-group v-model="updateUserForm.type">
                <el-radio :label="0">停用</el-radio>
                <el-radio :label="1">启用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="sex" label="性别">
              <el-radio-group v-model="updateUserForm.sex">
                <el-radio :label="0">女</el-radio>
                <el-radio :label="1">男</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="updateUserForm.type ===1">
            <el-form-item prop="username" label="账户">
              <el-input v-model="updateUserForm.username"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-col :span="12" v-if="updateUserForm.type===1">
          <el-form-item prop="password" label="密码">
            <el-input v-model="updateUserForm.password"></el-input>
          </el-form-item>
        </el-col>
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
import {nextTick, reactive} from "vue";
import {ElMessage} from "element-plus";
import useInstance from "../../hooks/useInstance.js";

const { global } = useInstance()
//所有的参数
const dialog = reactive({
  visible:false,
  title:'标题'
})
//修改包装类
let updateUserForm=reactive({
  username:'',
  password:'',
  phone:0,
  sex:0,
  type:0,
  name:''
})
//展示函数，将弹框展示出来
const onShow = () => {
  dialog.visible=true
}
//弹框关闭按钮
const onClose = () => {
  dialog.visible=false
}
//信息修改完成或者新增信息完成，对所有的属性进行提交
const onConfirm = () => {
  //写需要提交给后台的属性，分清是修改还是新增
  dialog.visible=false
}
//加载父组件传过来的参数，并把弹窗打开
const show = async (row) => {
  if (row){
    ElMessage({
      message:"进行修改",
      type:'success'
    })
  }
  //父组件传过来的参数
  await nextTick(() => {
    global.$objCopy(row,updateUserForm)
  })
  onShow()
}
//暴露出show函数给父组件使用
defineExpose({
  show
})


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