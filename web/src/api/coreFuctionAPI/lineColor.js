import {post} from "../../http/index.js";

export const iceToLineColor = (params) => {
    return post('/lineColor/iceToColor',params);
}