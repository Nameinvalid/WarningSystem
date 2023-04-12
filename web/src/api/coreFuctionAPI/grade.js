import {Delete, post, put} from "../../http/index.js";

export const insertWaringLevel = (params) => {
    return post('/warning/insertWarningLevel',params);
}
export const selectWaringLevel = (params) => {
    return post('/warning/selectWaringLevel',params);
}
export const updateWaringLevel = (params) => {
    return put('/warning/updateWaringLevel',params);
}
export const deleteWaringLevel = (params) => {
    return Delete('/warning/deleteWaringLevel',{data:params});
}