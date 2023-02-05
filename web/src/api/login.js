import {post} from '@/http/index'

export const loginApi=(params)=>{
    return post("/login",params);
}

