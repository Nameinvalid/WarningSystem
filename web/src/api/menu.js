import {post} from '@/http/index'
import {Delete} from "@/http/index.js";
import {put} from "@/http/index.js";
import {get} from "../http/index.js";

export const insertMenuAPI = (params) => {
  return post("/menu/insertMenu",params)
}
export const deleteMenuAPI = (params) => {
  return Delete("/menu/deleteMenu",{data:params})
}

export const updateMenuAPI=(params)=>{
    return put("/menu/updateMenu",params)
}

export const selectMenuAPI = (params) => {
  return post("/menu/findMenuPage",params)
}

export const selectParentMenuAPI = (params) => {
  return get(`/menu/findParentMenu/${params}`)
}