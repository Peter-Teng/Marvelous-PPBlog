<template>
    <div v-wave class="wrap">
        <div class="avatar">
            <div ref="protrait" @mouseenter="playAnime(protrait, 'tada')">
                <el-avatar :size="180" :src="info.avatar" />
            </div>
        </div>
        <div class="username">{{ info.username }}®️</div>
        <div>
            <el-row class="icons">
                <el-col :span="2"></el-col>
                <el-col class="icon" :span="4">
                    <el-popover placement="bottom" title="Email" :width="200" trigger="click">
                        <template #reference>
                            <img src="../../assets/images/email.svg" ref="email"
                                @mouseenter="playAnime(email, 'shakeY')" />
                        </template>
                        <div style="text-align: center;">{{ info.email }}</div>
                    </el-popover>
                </el-col>
                <el-col class="icon" :span="4"><img src="../../assets/images/github.svg" ref="github"
                        @mouseenter="playAnime(github, 'shakeY')" @click="newPage(info.github)" /></el-col>
                <el-col class="icon" :span="4"><img src="../../assets/images/bilibili.svg" ref="bilibili"
                        @mouseenter="playAnime(bilibili, 'shakeY')" @click="newPage(info.bilibili)" /></el-col>
                <el-col class="icon" :span="4"><img src="../../assets/images/leetcode.png" ref="leetcode"
                        @mouseenter="playAnime(leetcode, 'shakeY')" @click="newPage(info.leetcode)" /></el-col>
                <el-col class="icon" :span="4"><el-popover placement="bottom" title="Wechat" :width="220"
                        trigger="click">
                        <template #reference>
                            <img src="../../assets/images/wechat.svg" ref="wechat"
                                @mouseenter="playAnime(wechat, 'shakeY')" />
                        </template>
                        <div style="text-align: center;">
                            <img :src="info.wechat" style="width: 175px; height: 175px; object-fit: contain;" />
                        </div>
                    </el-popover>
                </el-col>
                <el-col :span="2"></el-col>
            </el-row>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

import playAnime from '../../utils/animate'

const props = defineProps({
    info: Object
});

const protrait = ref('')
const email = ref('')
const github = ref('')
const bilibili = ref('')
const wechat = ref('')
const leetcode = ref('')

const newPage = (dest) => {
    window.open(dest)
}

</script>

<style scoped>
div {
    font-family: 'ZCOOL';
}
.wrap {
    user-select: none;
    width: 300px;
    height: 400px;
    border-radius: 5px;
    box-shadow: .5px .5px 1px 1px #fbf2cf22;
    background: url("https://pp-blog-1300101944.cos.ap-guangzhou.myqcloud.com/assets%2Fimages%2FinfoBG.jpeg");
    margin-bottom: 50px;
    text-align: center;
    border: 2px solid #282A3A;
}

.avatar {
    width: 100%;
    height: 200px;
    margin: 0px auto;
    padding-top: 20px;
}

.username {
    padding-top: 10px;
    text-shadow: 2px 3px 1px rgba(246, 114, 114, 0.732);
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