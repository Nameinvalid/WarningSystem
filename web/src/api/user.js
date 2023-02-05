import {post} from '@/http/index'

export const insertUserApi = (params) => {
  return post('/user/insertUser',params);
}
