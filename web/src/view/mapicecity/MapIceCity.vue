<template>
  <div id="container">
    <div id="myPageTop">
      <el-input v-model="cityName.city" id="tip_input" placeholder="请输入关键字"></el-input>
      <el-button  @click="searchBtn">搜索</el-button>
      <input id="geo" type="button" class="btn" value="地址 -> 经纬度" />
      <el-popover
          placement="top-start"
          title="Title"
          :width="200"
          trigger="hover"
          content="this is content, this is content, this is content"
      >
        <template #reference>
          <el-button class="m-2">Hover to activate</el-button>
        </template>
      </el-popover>
    </div>
  </div>
</template>

<script setup>

//初始化高德地图key和插件
import AMapLoader from '@amap/amap-jsapi-loader';
import {onMounted} from "vue";
import { reactive } from "vue-demi";
import {findPositionAllAPI} from "../../api/coreFuctionAPI/position.js";


window._AMapSecurityConfig = {
  // 设置安全密钥
  securityJsCode: '6213b4eb12ca8a77c1cdcc68f0b29cab',
}
//转换之后的坐标
const state=reactive({
  current_position:[],
  path:[]
})
//转换前的地点
const positions=reactive({
  position:[]
})
const positionsName=reactive({
  positionName:[]
})
const cityName=reactive({
  city:'',
  cityInformation:{}
})

const searchBtn = () => {
  console.log(cityName.city);
  selectPositions()
}

let auto;
let placeSearch;
let map;
let geocoder;
let markers = [];
const selectPositions = async () => {
  let res= await findPositionAllAPI(cityName.city);
  if (res && res.code===200){
    positions.position=res.data;
    for (let i = 0; i < positions.position.length; i++) {
      positionsName.positionName[i]=positions.position[i].position;
    }
    /*placeSearch.setCity(cityName.cityInformation.adcode);
    placeSearch.search(cityName.cityInformation.name);//关键字查询查询
     */
  }
}

const initMap=()=> {
  let res=AMapLoader.load({
    key:'8e167a30f72f24852f31274698a42430',
    version:"2.0",
    plugins:["AMap.ControlBar","AMap.ToolBar","AMap.Scale","AMap.HawkEye",'AMap.PlaceSearch','AMap.AutoComplete'
    ,'AMap.Geocoder','AMap.Marker','AMap.TileLayer.Satellite','AMap.InfoWindow'],
  })
  res.then((AMap)=>{
    map = new AMap.Map("container",{
      viewMode: "3D", //是否为3D地图模式
      layers: [new AMap.TileLayer.Satellite()],//卫星图层
      zoom: 15, //初始化地图级别
      center: [110.412286,25.310778], //初始化地图中心点位置
      rotateEnable: true,
      pitchEnable: true,
      resizeEnable: true,
      showLabel: false,
      showIndoorMap: false,
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
    //地点查询
    const autoOptions = {
      //input:"tip_input"
    };
    auto = new AMap.AutoComplete(autoOptions);
    placeSearch = new AMap.PlaceSearch({
      map: map
    });  //构造地点查询类
    auto.on("select", async (e) => {
      cityName.city=e.poi.name;
      cityName.cityInformation=e.poi;
      await selectPositions();
      //查询完了之后进行转录地址
      await geoCode();
    });//注册监听，当选中某条记录时会触发
    //转录地址并进行地址标记
    geocoder = new AMap.Geocoder({
      city: "全国", //城市设为北京，默认：“全国”
    });
    const geoCode = () => {
      map.remove(markers);
      markers = [];
      geocoder.getLocation(positionsName.positionName, (status, result)=> {
        if (status === 'complete' && result.geocodes.length) {
          for (let i = 0; i < result.geocodes.length; i += 1) {
            let marker = new AMap.Marker({
              zooms: [13, 18],
              icon:'https://webapi.amap.com/images/mass/mass2.png',
              position: result.geocodes[i].location
            });
            markers.push(marker);
            state.path.push([marker._position.KL, marker._position.kT]);
          }
          map.add(markers);
          //标记完成进行连线
          addPolyLine();
          map.setFitView(markers);
        }
      });
    }
    document.getElementById("geo").onclick = geoCode;
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
    //通过数据库中的数据获取地址信息，然后标记到地图上，然后连线


    //通过点标记，进行折线连接
/*
    map.on("click", (e) => {
      // console.log(e);
      state.current_position = [e.lnglat.KL, e.lnglat.kT];
      state.path.push([e.lnglat.KL, e.lnglat.kT]);
      addMarker();

      addPolyLine();
      // 地图按照适合展示图层内数据的缩放等级展示
      // map.setFitView();
    });
    // 实例化点标记
    function addMarker() {
      const marker = new AMap.Marker({
        icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
        position: state.current_position,
        offset: new AMap.Pixel(-26, -54),
      });
      marker.setMap(map);
    }
*/

    // 折线
    const addPolyLine=()=> {
      const polyline = new AMap.Polyline({
        path: state.path,
        isOutline: true,
        outlineColor: "#ffeeff",
        borderWeight: 1,
        strokeColor: "#3366FF",
        strokeOpacity: 0.6,
        strokeWeight: 5,
        // 折线样式还支持 'dashed'
        strokeStyle: "solid",
        // strokeStyle是dashed时有效
        // strokeDasharray: [10, 5],
        lineJoin: "round",
        lineCap: "round",
        zIndex: 50,
      });
      //信息窗体
      let infoWindow;
      const openInfo = (e) => {
        //构建信息窗体中显示的内容
        let info = [];
        info.push("<div class='input-card content-window-card'><div><img style=\"float:left;width:67px;height:16px;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
        info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>高德软件</h4>");
        info.push("<p class='input-item'>电话 : 010-84107000   邮编 : 100102</p>");
        info.push("<p class='input-item'>地址 :北京市朝阳区望京阜荣街10号首开广场4层</p></div></div>");

        infoWindow = new AMap.InfoWindow({
          content: info.join("")  //使用默认信息窗体框样式，显示信息内容
        });

        infoWindow.open(map, [e.lnglat.KL, e.lnglat.kT]);
      }
      //对折线添加点击事件
      polyline.on('click',(e)=>{
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
      map.add([polyline]);
    }

  })
}
onMounted(()=>{
  initMap();
})
</script>

<style scoped>
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: calc(100vh - 156px);
}

#myPageTop {
  position: absolute;
  z-index: 2;
}
</style>