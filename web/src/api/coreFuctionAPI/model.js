import {get, post} from "../../http/index.js";

export const iceModel = (params) => {
    return post('/model/iceModel',params);
}