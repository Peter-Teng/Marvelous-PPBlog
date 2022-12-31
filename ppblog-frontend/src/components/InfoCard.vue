<template>
    <div v-wave class="wrap">
        <div class="avatar">
            <div ref="protrait" @mouseenter="playAnime(protrait, 'tada')">
                <el-avatar :size="180" :src="info.avatar" />
            </div>
        </div>
        <div class="username">{{ info.username + "®" }}</div>
        <div>
            <el-row class="icons">
                <el-col class="icon" :span="6"><img src="../assets/images/email.svg" ref="email"
                        @mouseenter="playAnime(email, 'shakeY')" /></el-col>
                <el-col class="icon" :span="6"><img src="../assets/images/github.svg" ref="github"
                        @mouseenter="playAnime(github, 'shakeY')" @click="newPage(info.github)" /></el-col>
                <el-col class="icon" :span="6"><img src="../assets/images/bilibili.svg" ref="bilibili"
                        @mouseenter="playAnime(bilibili, 'shakeY')" @click="newPage(info.bilibili)" /></el-col>
                <el-col class="icon" :span="6"><img src="../assets/images/wechat.svg" ref="wechat"
                        @mouseenter="playAnime(wechat, 'shakeY')" /></el-col>
            </el-row>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

import playAnime from '../utils/animate'

const props = defineProps({
    info: Object
});

const protrait = ref('')
const email = ref('')
const github = ref('')
const bilibili = ref('')
const wechat = ref('')

const newPage = (dest) => {
    window.open(dest)
}

</script>

<style scoped>
.wrap {
    width: 300px;
    height: 400px;
    border-radius: 5px;
    box-shadow: 1px 1px 2px 2px #0a0a0a4a;
    background: linear-gradient(120deg, #F6D242, #FF52E5);
    margin-bottom: 50px;
    text-align: center;
}

.avatar {
    width: 100%;
    height: 200px;
    margin: 0px auto;
    padding-top: 20px;
}

.username {
    padding-top: 10px;
    text-shadow: 2px 3px 1px rgba(255, 47, 47, 0.823);
    font-size: 2.5em;
    font-weight: bold;
    color: black;
}

.icons {
    width: 75%;
    margin: 0px auto;
    padding-top: 40px;
}

.icon {
    cursor: pointer;
    display: flex;
    width: 35px;
    height: 35px;
}

img {
    padding: 5px;
    width: 100%;
    object-fit: scale-down;
}
</style>