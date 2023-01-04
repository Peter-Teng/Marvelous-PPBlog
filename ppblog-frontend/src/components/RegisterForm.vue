<template>
    <div id="formbody">
        <div class="Half">
            <div class="loginPrompt">
                <div class="PromptText">本博客已经存在主人了？</div>
                <br />
                <div class="PromptText" style="padding-top: 15px; font-size: 1.5em; padding-bottom: 30px;">马上登录!💥</div>
                <br />
                <el-button v-wave color="#282A3A" class="Botton" @click="flip">去登录</el-button>
            </div>
            <div class="registerForm">
                <div class="register-text">注册</div>
                <div class="registerInfo">
                    <el-input type="text" class="inputs" v-model="username" placeholder="请输入注册的用户名">
                        <template #prefix>
                            <el-icon class="input-icon" :size="20">
                                <user />
                            </el-icon>
                        </template>
                    </el-input>
                    <br />
                    <el-input type="password" class="inputs" v-model="password" placeholder="请输入密码">
                        <template #prefix>
                            <el-icon class="input-icon" :size="20">
                                <key />
                            </el-icon>
                        </template>
                    </el-input>
                    <br />
                    <el-input type="password" class="inputs" v-model="passwordAgain" placeholder="请再次输入密码">
                        <template #prefix>
                            <el-icon class="input-icon" :size="20">
                                <key />
                            </el-icon>
                        </template>
                    </el-input>
                    <br/>
                    <el-button v-wave color="#EB455F" class="Botton">注册</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import api from '../api/index'
import { User, Key } from '@element-plus/icons-vue'

// 变量区域
const username = ref('')
const password = ref('')
const passwordAgain = ref('')

const emit = defineEmits(['flip'])

// 函数区

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
    color: #f5f5f68d;
    border-radius: 5px;
    box-shadow: 1px 1px 2px 2px #0a0a0a4a;
}

.inputs {
    width: 300px;
    margin: auto auto;
    height: 45px;
}

.Botton {
    margin-top: 15px;
    width: 200px;
    height: 40px;
    font-family: 'Courier New', Courier, monospace;
    font-weight: lighter;
    font-size: 1em;
    border-radius: 10px;
}

.input-icon {
    color: black;
}

.register-text {
    color: #1A120B;
    font-size: 3em;
    width: 400px;
    text-align: center;
    padding-bottom: 25px;
    padding-top: 80px;
}

.PromptText {
    color:#FAF8F1;
    font-size: 2.3em;
    width: 400px;
    text-align: center;
    padding-bottom: 30px;
    padding-top: 115px;
}

.registerInfo {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.loginPrompt {
    background-color: #144172c5;
    width: 400px;
    height: 450px;
    float: left;
    text-align: center;
}

.registerForm {
    width: 400px;
    height: 100%;
    float: right;
}
</style>