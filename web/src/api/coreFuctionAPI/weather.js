import {get} from "../../http/index.js";

export const weatherAPI = (params) => {
  return get(`https://devapi.qweather.com/v7/weather/now?location=${params}&key=1b3e83cc960a4feba04117d79c921b72`);
}

export const cityID = (cityName,province) => {
  if (province===undefined){
    return get(`https://geoapi.qweather.com/v2/city/lookup?location=${cityName}&key=1b3e83cc960a4feba04117d79c921b72`);
  }else {
    return get(`https://geoapi.qweather.com/v2/city/lookup?location=${cityName}&adm=${province}&key=1b3e83cc960a4feba04117d79c921b72`);
  }

}

export const citySevenDay = (params) => {
  return get(`https://devapi.qweather.com/v7/weather/7d?location=${params}&key=1b3e83cc960a4feba04117d79c921b72`);
}

export const cityAfterDay = (params) => {
  return get(`https://devapi.qweather.com/v7/weather/24h?location=${params}&key=1b3e83cc960a4feba04117d79c921b72`);
}