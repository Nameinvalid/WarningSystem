<template>
  <el-dialog :model-value="dialog.visible" :title="dialog.title" width="30%"
             :before-close="onClose" append-to-body :close-on-click-modal="false">
    <span>It's a draggable Dialog</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>


import {reactive} from "vue";

//所有的参数
const dialog = reactive({
  visible:false,
  title:'标题'
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
  console.log(row)
  //父组件传过来的参数
  onShow()
}
//暴露出show函数给父组件使用
defineExpose({
  show
})


</script>

<style lang="scss" scope>
.el-dialog {
  border-top-left-radius: 7px ;
  border-top-right-radius: 7px ;

  .el-dialog__header {
    margin-right: 0;
    border-top-left-radius: 7px ;
    border-top-right-radius: 7px ;
    background-color: #009688 ;

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