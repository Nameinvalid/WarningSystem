import {post,Delete,put} from '@/http/index'

export const insertRoleApi = (params) => {
    return post('/role/insertRole',params);
}

export const selectRoleApi=(params)=>{
    return post('/role/findRolePage',params)
}

export const updateRoleApi=(params)=>{
    return put('/role/updateRole',params)
}

export const deleteRoleApi = (params) => {
    return Delete('/role/deleteRole',{data:params})
}