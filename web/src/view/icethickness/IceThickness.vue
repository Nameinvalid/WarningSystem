<template>
  <el-main>
    <el-form :model="cityName" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="cityName.mainCity" @blur="selectCityNames" placeholder="请输入城市名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="cityName.cityName" filterable placeholder="请选择主要城区/乡镇">
          <el-option
              v-for="item in cityNames.cityNames"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchAfterDay" :icon="Clock" type="primary" round>二十四小时</el-button>
        <el-button @click="resetBtn" type="danger" :icon="Refresh" round>重置</el-button>
      </el-form-item>
    </el-form>
  </el-main>
  <div class="echarts-box">
    <div id="myEcharts" :style="{ width: '900px', height: '300px' }"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts';
import {onMounted, onUnmounted, reactive} from "vue";
import {ElMessage} from "element-plus";
import {cityAfterDay, cityID} from "../../api/coreFuctionAPI/weather.js";
import {Refresh,Clock} from '@element-plus/icons-vue';
import {iceModel} from "../../api/coreFuctionAPI/model.js";


const cityName =reactive({
  mainCity:'',//地级市
  cityName:'',//城区id
})
const cityNames= reactive({
  cityNames:[],//城区所有信息
})
//存放24小时的天气信息
const cityWeatherAfterDay=reactive({
  cityWeatherAfterDay:[],
})

//挑选信息组成二维数组送给后台的模型
const iceData=reactive({
  iceData:[[]]
})

//存放时间的信息
const cityAfterDayTimeMessage=reactive({
  day:[]
})

//存放覆冰厚度
const cityLineHeights =reactive({
  icingHeights:[]
})

//24小时天气预报
const searchAfterDay = async () => {
  if (cityName.mainCity===''){
    ElMessage.error({
      message:"请输入城市名称"
    })
  }else if (cityName.cityName===''){
    ElMessage.error({
      message:"请选择主要城区/乡镇"
    })
  }else {
    let res=await cityAfterDay(cityName.cityName);
    cityWeatherAfterDay.cityWeatherAfterDay=res.data.hourly;
    for (let i = 0; i < cityWeatherAfterDay.cityWeatherAfterDay.length; i++) {
      let dayTime= new Date(cityWeatherAfterDay.cityWeatherAfterDay[i].fxTime);
      dayTime=dayTime.getFullYear() + '-' + (dayTime.getMonth() + 1) + '-' + dayTime.getDate() + ' ' + dayTime.getHours() + ':' + dayTime.getMinutes() + ':' + dayTime.getSeconds();  // 2019-5-12 8:0:0
      cityAfterDayTimeMessage.day[i]=dayTime
      //进行覆冰厚度预测
      //将挑选好的信息传给二维数组 按照 温度，风速，湿度，厚度进行传输
      let data=cityWeatherAfterDay.cityWeatherAfterDay[i];
      //温度要在5℃一下，其实风速在
      let windSpeed=data.windSpeed/3.6;//将公里/h转换成m/s
      iceData.iceData[i]=[data.temp/1.0,windSpeed,data.humidity/1.0,0.0];
    }
    await model();
    console.log("覆冰初始数据",iceData.iceData)
    console.log(cityWeatherAfterDay.cityWeatherAfterDay)
    console.log(cityAfterDayTimeMessage.day)
    await initChart();
  }
}

const model = async () => {
  let res=await iceModel(iceData.iceData);
  cityLineHeights.icingHeights=res.data;
  console.log(cityLineHeights.icingHeights);
}


//查询城市ID
const selectCityNames = async () => {
  let res=await cityID(cityName.mainCity);
  cityNames.cityNames=res.data.location;
}

const resetBtn = async () => {
  cityName.cityName='';
  cityName.mainCity='';
}

let echart=echarts;

onUnmounted(()=>{

})

onMounted(()=>{

})
let chart;
const initChart = () => {
  if (chart != null && chart !== "" && chart !== undefined){
    chart.dispose();
  }
  chart= echart.init(document.getElementById("myEcharts"), "dark");
  // 把配置和数据放这里
  chart.setOption({
    xAxis: {
      type: "category",
      data: cityAfterDayTimeMessage.day,
    },
    tooltip: {
      trigger: "axis"
    },
    yAxis: {
      type: "value"
    },
    series: [
      {//写大概的覆冰厚度
        data: cityLineHeights.icingHeights,
        type: "line",
        smooth: true
      }
    ]
  });
  window.onresize = ()=> {
    //自适应大小
    chart.resize();
  };
}
</script>

<style scoped>

</style>