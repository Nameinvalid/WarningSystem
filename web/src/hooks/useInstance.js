import { getCurrentInstance} from "vue";

export default function useInstance() {
    const { appContext, proxy } = getCurrentInstance()
    const global = appContext.config.globalProperties;
    return {
        proxy,
        global
    }
}