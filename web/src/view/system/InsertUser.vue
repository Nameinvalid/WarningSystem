<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
      <el-form :model="updateUserForm" ref="userForm" label-width="80px" size="default" >
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
        </el-row>
        <el-row>
          <el-col :span="12" v-if="updateUserForm.type ===1">
            <el-form-item prop="roleName" label="角色">
              <el-select v-model="updateUserForm.roleName" filterable clearable placeholder="角色名称">
                <el-option
                    v-for="item in roleList.role"
                    :key="item.id"
                    :label="item.roleName"
                    :value="item.roleName"
                    @click="getRoleId(item.id)"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="updateUserForm.type ===1">
            <el-form-item prop="username" label="账户">
              <el-input v-model="updateUserForm.username"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="updateUserForm.type===1">
            <el-form-item prop="password" label="密码">
              <el-input v-model="updateUserForm.password"></el-input>
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
import {ElMessage} from "element-plus";
import useInstance from "../../hooks/useInstance.js";
import {insertUserApi, selectRoleAllAPI, updateUserApi} from "../../api/user.js";

const { global } = useInstance()
//所有的参数
const userForm=ref()
const dialog = reactive({
  visible:false,
  title:'标题'
})
//所有的角色
const roleList = reactive({
  role:[]
})
const getRoleId = async (index) => {
  updateUserForm.roleId=index;
}
//修改包装类
const updateUserForm=reactive({
  username:'',
  password:'',
  phone:0,
  sex:3,
  type:3,
  name:'',
  userId:0,//判断是新增还是修改
  updateUserId:0,
  roleName:'',
  roleId:0
})
const updateUserId=sessionStorage.getItem("loginUser")
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
  userForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updateUserForm.updateUserId=updateUserId
      if (updateUserForm.userId===0){
        //新增
        res=await insertUserApi(updateUserForm)
      }else {
        //修改
        res= await updateUserApi(updateUserForm)
      }
      if (res &&res.code===200){
        ElMessage.success(res.msg)
        emits('refresh')
        onClose()
      }
    }
  })
}
//加载父组件传过来的参数，并把弹窗打开
const show = async (row) => {
  let res=await selectRoleAllAPI();
  if (res && res.code===200){
    roleList.role=res.data
  }
  if (row){
    ElMessage({
      message:"对 "+row.name+" 账号进行修改",
      type:'success'
    })
    //父组件传过来的参数
    //不能添加await，添加了await之后userForm.value?.resetFields()就不会生效，算是一个时机问题
    nextTick(() => {
      global.$objCopy(row,updateUserForm)
      //如果是修改的话，会有一个userId，新增的时候，userId会是0
      updateUserForm.userId=row.id
      dialog.title='修改'
    })
  }else {
    ElMessage({
      message:"新增用户",
      type:'success'
    })
    dialog.title='新增'
  }
  userForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  updateUserForm.password='';
  updateUserForm.username='';
  updateUserForm.userId=0;
  onShow()
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