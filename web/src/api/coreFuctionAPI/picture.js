import {post,Delete,put} from '@/http/index'

export const selectPictureAPI = (params) => {
  return post('/picture/selectPicturePage',params)
}

export const deletePictureAPI = (params) => {
  return Delete('/picture/deletePicture',{data: params})
}

export const updatePictureAPI = (params) => {
  return put('/picture/updatePicture',params)
}

export const insertPictureAPI = (params) => {
  return post('/picture/insertPicture',params)
}