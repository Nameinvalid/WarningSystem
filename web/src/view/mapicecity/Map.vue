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
              :value="item.name"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button :icon="Search" round id="geo">搜索</el-button>
        <el-button @click="resetBtn" type="danger" :icon="Refresh" round>重置</el-button>
      </el-form-item>
    </el-form>
    <div id="container"></div>
  </el-main>
</template>

<script setup>
import {cityID} from "../../api/coreFuctionAPI/weather.js";
import {onMounted, reactive} from "vue";
import {Search,Refresh} from '@element-plus/icons-vue';
import {findPositionAllAPI} from "../../api/coreFuctionAPI/position.js";
//地图组件
import AMapLoader from '@amap/amap-jsapi-loader';
import {iceToLineColor} from "../../api/coreFuctionAPI/lineColor.js";
window._AMapSecurityConfig = {
  // 设置安全密钥
  securityJsCode: '6213b4eb12ca8a77c1cdcc68f0b29cab',
}

const cityName =reactive({
  mainCity:'',//地级市
  cityName:'',//城区名称
})

const cityNames= reactive({
  cityNames:[],//城区所有信息
})
//存储数据库中的电线杆数据点
const position=reactive({
  positions:[],
  geos:[]//坐标
})
const ice=reactive({
  ice:[]
})

const lineColor =reactive({
  lineColor:[]
})

//查询城市ID
const selectCityNames = async () => {
  let res=await cityID(cityName.mainCity);
  cityNames.cityNames=res.data.location;
  console.log("citynames",cityNames.cityNames)
}


const searchBtn = async () => {
  console.log("城区名称",cityName.cityName)
  //查询当前的城区的线路数据点
  let res=await findPositionAllAPI(cityName.cityName);
  if (res && res.code===200){
    for (let i = 0; i < res.data.length; i++) {
      position.positions[2*i]=res.data[i].position
      position.positions[2*i+1]=res.data[i].distPosition;
      ice.ice[i]=res.data[i].ice
    }
    //循环完了之后，判断冰厚标准，是红线还是蓝线还是绿线
    let resLine=await iceToLineColor(ice.ice);
    if (resLine && resLine.code===200){
      lineColor.lineColor=resLine.data;
      console.log(lineColor.lineColor)
    }
  }

}

const resetBtn = async () => {
  cityName.cityName='';
  cityName.mainCity='';
}

const initMap=()=> {
  let res=AMapLoader.load({
    key:'8e167a30f72f24852f31274698a42430',
    version:"2.0",
    plugins:["AMap.ControlBar","AMap.ToolBar","AMap.Scale","AMap.HawkEye",'AMap.Geocoder','AMap.LabelsLayer','AMap.LabelMarker',
      'AMap.Marker','AMap.TileLayer.Satellite','AMap.InfoWindow','AMap.Polyline'],
  })
  res.then((AMap)=>{
    let map = new AMap.Map("container",{
      viewMode: "3D", //是否为3D地图模式
      //layers: [new AMap.TileLayer.Satellite()],//卫星图层
      zoom: 15, //初始化地图级别
      center: [110.412286,25.310778], //初始化地图中心点位置
      rotateEnable: true,
      pitchEnable: true,
      resizeEnable: true,
      pitch:0,
      rotation: 0,
      zooms:[2,20],
    })
    const controlBar = new AMap.ControlBar({
      position: {
        right: '10px',
        top: '10px'
      }
    });
    //表盘控制器
    controlBar.addTo(map);
    map.addControl(new AMap.Scale());
    map.addControl(new AMap.HawkEye());
    const toolBar = new AMap.ToolBar({
      position:{
        right:'40px',
        top:'110px'
      }
    });
    toolBar.addTo(map);
    //将地点转换成坐标
    let geocoder = new AMap.Geocoder({
      city: "灵川县", //城市设为北京，默认：“全国”
    });
    //点坐标
    let markers = [];
    const geoCode = async () => {
      await searchBtn();
      map.remove(markers);
      markers=[];
      geocoder.getLocation(position.positions,(status, result)=>{
        if (status === 'complete' && result.geocodes.length){
          for (let i = 0; i < result.geocodes.length; i++) {
            position.geos[i]=[result.geocodes[i].location.lng,result.geocodes[i].location.lat]
          }
          console.log(result.geocodes);
          //转录完了之后，进行点标记
          point();
        }
      })
    }
    document.getElementById("geo").onclick = geoCode;

    //点标记函数
    const point = async () => {
      //先添加图层
      let layer=new AMap.LabelsLayer({
        zooms: [1, 20],
        zIndex: 10,
      })
      layer.add(markers);
      // 图层添加到地图
      map.add(layer);
      let icon={
        type: 'image',
        image: 'https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png',
        size: [6, 15],
        anchor: 'bottom-center',
      }
      for (let i = 0; i < position.geos.length; i++) {
        let pointPosition=position.geos[i];
        let pointData={
          position:pointPosition,
          icon
        };
        let labelMarker=new AMap.LabelMarker(pointData);
        markers.push(labelMarker);
      }
      // 一次性将海量点添加到图层
      layer.add(markers);
      //点标记完成之后，进行线路绘制
      addPolyLine()
    }

    //添加折线
    const addPolyLine=()=> {
      //对折线做判断
      //两个画一个折线
      for (let i = 0; i < position.geos.length; i+=2) {
        let polyline = new AMap.Polyline({
          path: [position.geos[i],position.geos[i+1]],
          isOutline: true,
          outlineColor: "#ffeeff",//边框
          borderWeight: 1,//边框厚度
          strokeColor: lineColor.lineColor[i/2],//线的颜色
          strokeOpacity: 0.6,
          strokeWeight: 5,
          // 折线样式还支持 'dashed'
          strokeStyle: "solid",
          // strokeStyle是dashed时有效
          // strokeDasharray: [10, 5],
          lineJoin: "round",
          lineCap: "round",
          ice:ice.ice[i/2],//这里直接查询覆冰厚度
          zIndex: 50,
        })
        //信息窗体
        let infoWindow;
        const openInfo = (e) => {
          //构建信息窗体中显示的内容
          infoWindow = new AMap.InfoWindow({
            content: ""  //使用默认信息窗体框样式，显示信息内容
          });
          infoWindow.setContent("<div>覆冰厚度："+e.target._opts.ice+"</div>")
          infoWindow.open(map, [e.lnglat.KL, e.lnglat.kT]);
        }
        polyline.on('click',(e)=>{
          //调取冰厚
          console.log(e)
          openInfo(e,i);
        })
        polyline.on('mouseout',()=>{
          //当鼠标移出线时
          //直接移除信息框体
          map.clearInfoWindow();
        });
        map.add([polyline]);
      }
      /*const polyline = new AMap.Polyline({
        path: position.geos,
        isOutline: true,
        outlineColor: "#ffeeff",//边框
        borderWeight: 1,//边框厚度
        strokeColor: ["red",'blue'],//线的颜色
        strokeOpacity: 0.6,
        strokeWeight: 5,
        // 折线样式还支持 'dashed'
        strokeStyle: "solid",
        // strokeStyle是dashed时有效
        // strokeDasharray: [10, 5],
        lineJoin: "round",
        lineCap: "round",
        extData:[1,2,3],
        zIndex: 50,
      });
      //信息窗体
      let infoWindow;
      const openInfo = (e) => {
        //构建信息窗体中显示的内容
        let info = [];
        info.push("<div>{{}}</div>");

        infoWindow = new AMap.InfoWindow({
          content: info.join("")  //使用默认信息窗体框样式，显示信息内容
        });

        infoWindow.open(map, [e.lnglat.KL, e.lnglat.kT]);
      }
      //对折线添加点击事件
      polyline.on('click',(e)=>{
        //调取冰厚

        openInfo(e);
      })
      polyline.on('mouseover', ()=>{
        //当鼠标移动到线上时
        console.log(1)
      });
      polyline.on('mouseout',()=>{
        //当鼠标移出线时
        //直接移除信息框体
        map.clearInfoWindow();
        console.log(2)
      });
      map.add([polyline]);*/
    }



  })
}

onMounted(()=>{
  initMap();
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
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: calc(100vh - 156px);
}
</style>