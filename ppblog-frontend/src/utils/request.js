import { ElMessage } from 'element-plus'
import 'element-plus/theme-chalk/index.css' 
import axios from "axios"

const instance = axios.create({})

const showError = (code, message) => {
    ElMessage.error(`[Error] Status : ${code} as ${message}`)
}

instance.interceptors.response.use(
    response => response.status === 200 ? Promise.resolve(response) : Promise.reject(response),
    error => {
        showError(error.code, error.message)
    }
)
export default instance;