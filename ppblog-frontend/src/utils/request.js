import { ElMessage } from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import axios from "axios"

const instance = axios.create({})

const showError = (code, message) => {
    ElNotification({
        title: `Error ${code}`,
        message: message,
        type: 'error',
      })
}

instance.interceptors.response.use(
    response => {
        if (response.status > 400) {
            ElMessage.error(`An error has happened!`)
            console.log(response)
            return Promise.reject(response)
        } else {
            if ("code" in response.data) {
                if (response.data.code != 200) {
                    ElMessage.error(`Status : [${response.data.code}] ${response.data.message}`)
                    return Promise.reject(response)
                }
            }
            return Promise.resolve(response)
        }
    },
    error => {
        showError(error.code, error.message)
    }
)
export default instance;