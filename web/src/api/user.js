import {post,Delete,put} from '@/http/index'
import {get} from "../http/index.js";

export const insertUserApi = (params) => {
  return post('/user/insertUser',params);
}

export const selectUserApi=(params)=>{
  return post('/user/findIPageUser',params)
}

export const updateUserApi=(params)=>{
  return put('/user/updateUser',params)
}

export const deleteUserApi = (params) => {
  return Delete('/user/deleteUser',{data:params})
}

export const selectRoleAllAPI = () => {
  return get('/user/selectAllRole');
}
