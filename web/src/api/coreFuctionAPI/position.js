import {get, post} from "../../http/index.js";

export const findPositionAllAPI = (params) => {
  return get(`/position/findPositionAll/${params}`);
}