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
        <el-button @click="searchSevenDay" :icon="Clock" type="primary" round>未来七天</el-button>
        <el-button @click="searchBtn" :icon="Search" round>搜索</el-button>
        <el-button @click="resetBtn" type="danger" :icon="Refresh" round>重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-col
          v-for="(o,index) in cityWeatherSevenDay.cityWeatherSevenDay"
          :key="o"
          :span="8"
      >
        <el-card shadow="hover"
                 class="card"
        >
          <el-image style="width: 100px; height: 100px; margin-left: 18vh" :src="cityIcons.icons[index]" :fit="'contain'"/>
          <el-container>
            <el-header class="week-header">
              <div class="week">
                {{o.week}}
              </div>
            </el-header>
            <el-main class="week-main">
              <el-descriptions
                  class="margin-top"
                  :column="1"
                  border
              >
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <el-image src="/src/images/QWeather-Icon/icons/900.svg"/>
                      </el-icon>
                      最高温度
                    </div>
                  </template>
                  {{o.tempMax}}℃
                </el-descriptions-item>
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <el-image src="/src/images/QWeather-Icon/icons/901.svg"/>
                      </el-icon>
                      最低温度
                    </div>
                  </template>
                  {{o.tempMin}}℃
                </el-descriptions-item>
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <el-image src="/src/images/QWeather-Icon/icons/1018.svg"/>
                      </el-icon>
                      风速
                    </div>
                  </template>
                  {{o.windSpeedDay}}km/h
                </el-descriptions-item>
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <el-image src="/src/images/QWeather-Icon/icons/2019.svg"/>
                      </el-icon>
                      相对湿度
                    </div>
                  </template>
                  {{o.humidity}}%
                </el-descriptions-item>
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <el-image src="/src/images/QWeather-Icon/icons/1703.svg"/>
                      </el-icon>
                      总降水量
                    </div>
                  </template>
                  {{o.precip}}mm
                </el-descriptions-item>
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon>
                        <Calendar />
                      </el-icon>
                      日期
                    </div>
                  </template>
                  {{o.fxDate}}
                </el-descriptions-item>
              </el-descriptions>
            </el-main>
          </el-container>
        </el-card>
      </el-col>
    </el-row>
  </el-main>
</template>

<script setup>
import {cityID, citySevenDay, weatherAPI} from "../../api/coreFuctionAPI/weather.js";
import {onMounted, reactive} from "vue";
import {Search,Refresh,Clock} from '@element-plus/icons-vue';
import {ElMessage} from "element-plus";

const cityName =reactive({
  mainCity:'',//地级市
  cityName:'',//城区id
})

const cityNames= reactive({
  cityNames:[],//城区所有信息
  cityWeathers:{}//选中城区的天气信息
})
const cityIcon=reactive({
  icon:''//天气图标
})
const cityIcons =reactive({
  icons:[],
})
//存放七天的信息
const cityWeatherSevenDay=reactive({
  cityWeatherSevenDay:[],
})
//日期转换成周几
const DayToWeek = (cityInfo) => {
  console.log(cityInfo);
  const weekNum = new Date(cityInfo.fxDate).getDay();
  let week;
  switch (weekNum) {
    case 0:
      week = "星期天";
      break;
    case 1:
      week = "星期一";
      break;
    case 2:
      week = "星期二";
      break;
    case 3:
      week = "星期三";
      break;
    case 4:
      week = "星期四";
      break;
    case 5:
      week = "星期五";
      break;
    case 6:
      week = "星期六";
      break;
  }
  return week;
}

//七天天气预报
const searchSevenDay = async () => {
  if (cityName.mainCity===''){
    ElMessage.error({
      message:"请输入城市名称"
    })
  }else if (cityName.cityName===''){
    console.log(cityName.cityName);
    ElMessage.error({
      message:"请选择主要城区/乡镇"
    })
  }else {
    console.log(cityName.cityName)
    let res=await citySevenDay(cityName.cityName);
    cityWeatherSevenDay.cityWeatherSevenDay=res.data.daily;
    for (let i = 0; i < cityWeatherSevenDay.cityWeatherSevenDay.length; i++) {
      cityIcons.icons[i]="/src/images/QWeather-Icon/icons/"+cityWeatherSevenDay.cityWeatherSevenDay[i].iconDay+".svg";
      cityWeatherSevenDay.cityWeatherSevenDay[i].week=DayToWeek(cityWeatherSevenDay.cityWeatherSevenDay[i]);
    }
    console.log(cityWeatherSevenDay.cityWeatherSevenDay);
    console.log(cityIcons.icons);
  }
}
//查询城市ID
const selectCityNames = async () => {
  let res=await cityID(cityName.mainCity);
  cityNames.cityNames=res.data.location;
}
//查询该城市当天的天气
const searchBtn = async () => {
  let res=await weatherAPI(cityName.cityName);
  cityNames.cityWeathers=res.data.now;
  cityIcon.icon="/src/images/QWeather-Icon/icons/"+cityNames.cityWeathers.icon+".svg";
}

const resetBtn = async () => {
  cityName.cityName='';
  cityName.mainCity='';
}

onMounted(()=>{
})
</script>

<style lang="scss" scope>
.el-col{
  margin-bottom: 20px;
}
.week-header{
  width: 45vh;
  height: 5vh;
  background-color: white;
}
.week{
  width: 45vh;
  height: 5vh;
  text-align: center;
  font-size: larger;
}
</style>