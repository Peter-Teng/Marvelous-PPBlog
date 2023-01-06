<template>
    <div id="formbody">
        <div class="half">
            <div class="loginForm">
                <div class="login-text">登录</div>
                <div class="loginInfo">
                    <el-input type="text" class="inputs" v-model="username" placeholder="请输入用户名">
                        <template #prefix>
                            <el-icon class="input-icon" :size="20">
                                <user />
                            </el-icon>
                        </template>
                    </el-input>
                    <br />
                    <el-input type="password" class="inputs" v-model="password" placeholder="请输入密码" show-password>
                        <template #prefix>
                            <el-icon class="input-icon" :size="20">
                                <key />
                            </el-icon>
                        </template>
                    </el-input>
                    <br />
                    <el-button v-wave color="#227C70" class="Botton" @click="login">登录</el-button>
                </div>
            </div>
            <div class="registerPrompt">
                <div class="PromptText">本博客还没有主人吗？</div>
                <br />
                <div class="PromptText" style="padding-top: 15px; font-size: 0.25rem; padding-bottom: 30px;">立即注册!🥳</div>
                <br />
                <el-button v-wave color="#3A4F7A" class="Botton" @click="flip">去注册</el-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import api from '../../api/index'
import { User, Key } from '@element-plus/icons-vue'

// 变量区域
const username = ref('')
const password = ref('')

const emit = defineEmits(['flip'])

// 函数区
function login() {
    if(username.value.length < 3 || username.value.length > 8) {
        ElMessage.warning("用户名要在3到8个字符之间哦")
        return;
    }
    if(password.value.length < 4 || password.value.length > 16) {
        ElMessage.warning("密码要在4到16个字符之间哦!")
        return;
    }
    const user = {
        "username": username.value,
        "password": password.value,
    }
    api.login(user).then(res => {
        ElMessage.success("登录成功！")
    })
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
    width: 800px;
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
    margin: auto auto;
    height: 45px;
}

.inputs ::selection {
  background-color: #3a3a3a;
  color: #FAF8F1;
}

.Botton {
    margin-top: 15px;
    width: 200px;
    height: 40px;
    font-family: 'Courier New', Courier, monospace;
    font-weight: lighter;
    font-size: 0.1rem;
    border-radius: 10px;
}

.input-icon {
    color: black;
}

.login-text {
    color: #1A120B;
    font-size: 0.3rem;
    width: 400px;
    text-align: center;
    padding-bottom: 30px;
    padding-top: 95px;
}

.PromptText {
    font-size: 0.2rem;
    width: 400px;
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
    width: 400px;
    height: 100%;
}

.registerPrompt {
    background-color: #ffb30092;
    width: 400px;
    height: 450px;
    text-align: center;
}
</style>