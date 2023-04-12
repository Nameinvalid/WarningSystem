/**
 * 导入官方的axios，对axios进行封装
 */
import axios from "axios"
import config from "@/http/config"
import { ElMessage } from "element-plus";
//axios配置
const instance=axios.create({
    baseURL: config.baseUrl,
    timeout: config.timeOut
})

instance.interceptors.request.use(
    config=>{
        return Promise.resolve(config)
    },
    err=>{
        return Promise.reject(err)
    }
)

instance.interceptors.response.use(
    res=>{
        console.log(res)
        if(res.data.code!==200){
            if (res.statusText==="OK"){
                return res.data
            }else if (res.data.code==="200"){
                return res;
            }
            ElMessage.error(res.data.msg||'服务器出错')
            return Promise.reject(res.data.msg||'服务器出错')
        }else{
            return res.data
        }

    },
    error=>{
        console.log('进入错误')
        error.data={};
        if(error&&error.response){
            switch(error.response.status){
                case 400:
                    error.data.msg='错误请求';
                    ElMessage.error(error.data.msg);
                    break;
                case 401:
                    error.data.msg = '未授权，请重新登录';
                    ElMessage.error(error.data.msg);
                    break;
                case 403:
                    error.data.msg='拒绝访问';
                    ElMessage.error(error.data.msg);
                    break;
                case 404:
                    error.data.msg='请求错误，未找到资源';
                    ElMessage.error(error.data.msg);
                    break;
                case 405:
                    error.data.msg="请求方法为允许";
                    ElMessage.error(error.data.msg);
                    break;
                case 408:
                    error.data.msg = '请求超时';
                    ElMessage.error(error.data.msg);
                    break;
                case 500:
                    error.data.msg = '服务器端出错';
                    ElMessage.error(error.data.msg);
                    break;
                case 501:
                    error.data.msg = '网络未实现';
                    ElMessage.error(error.data.msg);
                    break;
                case 502:
                    error.data.msg = '网络错误';
                    ElMessage.error(error.data.msg);
                    break;
                case 503:                       
                    error.data.msg = '服务不可用';
                    ElMessage.error(error.data.msg);
                    break;
                case 504:
                    error.data.msg = '网络超时';
                    ElMessage.error(error.data.msg);
                    break;
                case 505:
                    error.data.msg = 'http版本不支持该请求';
                    ElMessage.error(error.data.msg);
                    break;
                default:
                    error.data.msg = `连接错误${error.response.status}`;
                    ElMessage.error(error.data.msg);
            }
        }else{
            error.data.msg='连接到服务器失败';
            ElMessage.error(error.data.msg);
        }
        return Promise.reject(error)
    }  
)     
        

export function get(url, params) {
    return instance.get(url, {params});
}

export function post(url, params) {
    return instance.post(url, params);
}

export function put(url,params){
    return instance.put(url,params);
}

export function Delete(url,params){
    return instance.delete(url,params);
}
