<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
    <el-form :model="updateRoleForm" ref="roleForm" label-width="80px" size="default" >
      <el-row>
        <el-col :span="12">
          <el-form-item prop="roleName" label="角色名字">
            <el-input v-model="updateRoleForm.roleName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="remark" label="角色描述">
            <el-input v-model="updateRoleForm.remark"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item prop="type" label="状态">
            <el-radio-group v-model="updateRoleForm.type">
              <el-radio :label="0">停用</el-radio>
              <el-radio :label="1">启用</el-radio>
            </el-radio-group>
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

import {nextTick, reactive, ref} from "vue";
import useInstance from "../../hooks/useInstance.js";
import {insertRoleApi, updateRoleApi} from "../../api/role.js";
import {ElMessage} from "element-plus";

const { global } = useInstance()
const dialog = reactive({
  visible:false,
  title:'标题'
})
const updateRoleForm =reactive({
  roleName:'',
  remark:'',
  type:3,
  roleId:0,//判断是新增还是修改
  updateUserId:0
})
const updateUserId=sessionStorage.getItem("loginUser")
const roleForm=ref()
//加载父组件传过来的参数，并把弹窗打开
const show = async (row) => {
  if (row){
    ElMessage({
      message:"对 "+row.roleName+" 角色进行修改",
      type:'success'
    })
    //父组件传过来的参数
    //不能添加await，添加了await之后userForm.value?.resetFields()就不会生效，算是一个时机问题
    nextTick(() => {
      global.$objCopy(row,updateRoleForm)
      //如果是修改的话，会有一个userId，新增的时候，userId会是0
      updateRoleForm.roleId=row.id
      dialog.title='修改'
    })
  }else {
    ElMessage({
      message:"新增角色",
      type:'success'
    })
    dialog.title='新增'
  }
  roleForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  updateRoleForm.roleId=0;
  onShow()
}
const onConfirm = () => {
  //写需要提交给后台的属性，分清是修改还是新增
  roleForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updateRoleForm.updateUserId=updateUserId
      if (updateRoleForm.roleId===0){
        //新增
        res=await insertRoleApi(updateRoleForm)
      }else {
        //修改
        res= await updateRoleApi(updateRoleForm)
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