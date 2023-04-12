<template>
  <el-button @click="model">测试输入二维数组预测覆冰厚度</el-button>
  {{iceCover.iceCover}}
</template>

<script setup>

import {iceModel} from "../../api/coreFuctionAPI/model.js";
import {reactive} from "vue";

//输入的数据
const iceDataTest=reactive({
  iceData:[[]]
})
//冰厚
const iceCover=reactive({
  iceCover:[]
})
const model = async () => {
  iceDataTest.iceData=[[-2,0.3,96,0.0],[-3,0.2,99,0.0]]
  console.log(iceDataTest.iceData);
  let res=await iceModel(iceDataTest.iceData);
  iceCover.iceCover=res.data
}
</script>

<style scoped>

</style>