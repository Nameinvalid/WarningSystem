import {post,Delete} from '@/http/index'

export const insertUserApi = (params) => {
  return post('/user/insertUser',params);
}

export const selectUserApi=(params)=>{
  return post('/user/findIPageUser',params)
}

export const deleteUserApi = (params) => {
  return Delete('/user/deleteUser',{data:params})
}
