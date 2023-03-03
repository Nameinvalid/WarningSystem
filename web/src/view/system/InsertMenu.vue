<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
    <el-form :model="updateMenuForm" ref="menuForm" label-width="80px" size="default" >
      <el-row>
        <el-col :span="12">
          <el-form-item prop="menuName" label="菜单名字">
            <el-input v-model="updateMenuForm.menuName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="parentMenuName" label="父级菜单名字">
            <el-select v-model="updateMenuForm.parentMenuName" filterable placeholder="父级菜单名字">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item prop="menuGrade" label="性别">
            <el-radio-group v-model="updateMenuForm.menuGrade">
              <el-radio :label="1">一级菜单</el-radio>
              <el-radio :label="2">二级菜单</el-radio>
              <el-radio :label="3">三级菜单</el-radio>
              <el-radio :label="4">四级菜单</el-radio>
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

import useInstance from "../../hooks/useInstance.js";
import {nextTick, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {insertMenuAPI, updateMenuAPI} from "../../api/menu.js";

const { global } = useInstance()
const dialog = reactive({
  visible:false,
  title:'标题'
})
const updateMenuForm =reactive({
  menuName:'',
  parentMenuName:'',
  parentMenuId:0,
  menuGrade:10,
  menuId:0,//判断是新增还是修改
  updateUserId:0,
})
const updateUserId=sessionStorage.getItem("loginUser")
const menuForm=ref()
//加载父组件传过来的参数，并把弹窗打开
const show = async (row) => {
  if (row){
    ElMessage({
      message:"对 "+row.menuName+" 菜单进行修改",
      type:'success'
    })
    //父组件传过来的参数
    //不能添加await，添加了await之后userForm.value?.resetFields()就不会生效，算是一个时机问题
    nextTick(() => {
      global.$objCopy(row,updateMenuForm)
      //如果是修改的话，会有一个userId，新增的时候，userId会是0
      updateMenuForm.menuId=row.id
      updateMenuForm.parentMenuId=row.bId
      dialog.title='修改'
    })
  }else {
    ElMessage({
      message:"新增角色",
      type:'success'
    })
    dialog.title='新增'
  }
  menuForm.value?.resetFields()
  //由于表单中有几个数据消不掉，所以另外加上
  updateMenuForm.menuId=0;
  updateMenuForm.menuGrade=10;
  updateMenuForm.parentMenuId=0;
  onShow()
}
const options = [{
  value: 'Option1',
  label: 'Option1',
},
  {
    value: 'Option2',
    label: 'Option2',
  },
  {
    value: 'Option3',
    label: 'Option3',
  },
  {
    value: 'Option4',
    label: 'Option4',
  },
  {
    value: 'Option5',
    label: 'Option5',
  },]
const onConfirm = () => {
  //写需要提交给后台的属性，分清是修改还是新增
  menuForm.value?.validate(async (avid)=>{
    if (avid){
      let res=null
      updateMenuForm.updateUserId=updateUserId
      if (updateMenuForm.menuId===0){
        //新增
        res=await insertMenuAPI(updateMenuForm)
      }else {
        //修改
        res= await updateMenuAPI(updateMenuForm)
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