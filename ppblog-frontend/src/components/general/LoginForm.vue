<template>
    <div id="formbody">
        <div class="half">
            <div class="loginForm">
                <div class="login-text">登录</div>
                <div class="loginInfo">
                    <el-form :model="user">
                        <el-input type="text" class="inputs" v-model="user.username" placeholder="请输入用户名">
                            <template #prefix>
                                <el-icon class="input-icon" :size="20">
                                    <User />
                                </el-icon>
                            </template>
                        </el-input>
                        <br />
                        <el-input type="password" class="inputs" v-model="user.password" placeholder="请输入密码"
                            show-password>
                            <template #prefix>
                                <el-icon class="input-icon" :size="20">
                                    <Key />
                                </el-icon>
                            </template>
                        </el-input>
                    </el-form>
                    <br />
                    <el-button v-wave color="#227C70" class="Botton" @click="login">登录</el-button>
                    <br />
                    <el-button v-wave color="#E14D2A" class="Botton" @click="visitorLogin" style="margin-top: 0;">游客访问</el-button>
                </div>
            </div>
            <div class="registerPrompt">
                <div class="PromptText">本博客还没有主人吗？</div>
                <br />
                <div class="PromptText" style="padding-top: 15px; font-size: 2rem; padding-bottom: 30px;">立即注册!🥳</div>
                <br />
                <el-button v-wave color="#3A4F7A" class="Botton" @click="flip">去注册</el-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive, inject } from 'vue'
import api from '../../api/index'
import { User, Key } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

// 变量区域
const router = useRouter()
const userStore = inject("userStore")

const user = reactive({
    username: "",
    password: ""
})

const emit = defineEmits(['flip'])

// 函数区
function login() {
    if (user.username.length < 3 || user.username.length > 8) {
        ElMessage.warning("用户名要在3到8个字符之间哦")
        return;
    }
    if (user.password.length < 4 || user.password.length > 16) {
        ElMessage.warning("密码要在4到16个字符之间哦!")
        return;
    }

    api.login(user).then(res => {
        ElMessage.success("登录成功！")
        userStore.methods.login(userStore, res.data.data.token)
        router.push('/admin')
    })
}

const visitorLogin = () => {
    ElMessage.success("游客登录成功！")
    userStore.methods.login(userStore, "fakeToken")
    router.push('/admin')
}

const flip = () => {
    emit("flip")
}
</script>

<style scoped>
div {
    font-family: 'ZCOOL';
}

#formbody {
    user-select: none;
    width: 750px;
    height: 450px;
    background-color: #ffffff88;
    border-radius: 5px;
    box-shadow: 1px 1px 2px 2px #0a0a0a4a;
}

.half {
    display: flex;
}

.inputs {
    width: 300px;
    margin: 5px auto;
    height: 45px;
}

.inputs ::selection {
    background-color: #3a3a3a;
    color: #FAF8F1;
}

.Botton {
    margin-top: 1rem;
    width: 200px;
    height: 40px;
    font-family: 'Courier New', Courier, monospace;
    font-weight: lighter;
    font-size: 0.9rem;
    border-radius: 10px;
}

.input-icon {
    color: black;
}

.login-text {
    color: #1A120B;
    font-size: 3rem;
    width: 375px;
    text-align: center;
    padding-bottom: 30px;
    padding-top: 95px;
}

.tips {
    cursor: pointer;
    vertical-align: super;
    height: 100%;
    margin-left: 10px;
}

.PromptText {
    font-size: 2.5rem;
    width: 375px;
    text-align: center;
    padding-bottom: 30px;
    padding-top: 115px;
}

.loginInfo {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.loginForm {
    width: 375px;
    height: 100%;
}

.registerPrompt {
    background-color: #ffb30092;
    width: 375px;
    height: 450px;
    text-align: center;
}
</style>