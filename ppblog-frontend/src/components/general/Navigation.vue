<template>
    <div class="wrap">
        <el-row class="naviBar" ref="bar" :style="{ 'background': props.backgroundColor }">
            <el-col class="name" :span="3">
                <div :style="{ 'color': props.fontColor }">MarvelousPP</div>
            </el-col>
            <el-col :span="7"></el-col>
            <el-col :span="3">
                <div @click="toPage('/')" class="link" ref="toMain" @mouseenter="playAnime(toMain, 'rubberBand', true)"
                    :style="{ 'color': props.fontColor }">
                    🗺️&nbsp;首页</div>
            </el-col>
            <el-col :span="3">
                <div @click="toPage('/articles/all')" class="link" ref="toRecommend"
                    @mouseenter="playAnime(toRecommend, 'rubberBand', true)" :style="{ 'color': props.fontColor }">
                    🏆&nbsp;博文荟萃</div>
            </el-col>
            <el-col :span="3">
                <div @click="toPage('/navigation')" class="link" ref="toUsefulLinks"
                    @mouseenter="playAnime(toUsefulLinks, 'rubberBand', true)" :style="{ 'color': props.fontColor }">
                    🧭&nbsp;资源导航</div>
            </el-col>
            <el-col :span="3">
                <div @click="toPage('/statistic')" class="link" ref="toStatistic"
                    @mouseenter="playAnime(toStatistic, 'rubberBand', true)" :style="{ 'color': props.fontColor }">
                    🧮&nbsp;本站统计</div>
            </el-col>
            <el-col :span="2">
                <div @click="toAdmin()" class="link" ref="toLogin" @mouseenter="playAnime(toLogin, 'jello', true)">
                    <div class="avatar" :style="{ 'background-image': 'url(' + avatarSource + ')' }" />
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref, inject } from 'vue'
import { useRouter } from 'vue-router'

import playAnime from '../../utils/animate'

const props = defineProps({
    backgroundColor: { type: String, default: "transparent" },
    fontColor: { type: String, default: "aliceblue" },
});

const bar = ref("")
const toMain = ref("")
const toRecommend = ref("")
const toUsefulLinks = ref("")
const toStatistic = ref("")
const toLogin = ref("")
const avatarSource = ref("https://pp-blog-1300101944.cos.ap-guangzhou.myqcloud.com/assets%2Fimages%2Fpp-icon.png")

const router = useRouter()
const userStore = inject("userStore")

const toPage = (dest) => {
    router.push(dest)
}

const toAdmin = () => {
    if (userStore.methods.hasLogin(userStore)) {
        ElNotification({ title: 'Hey,主人!', message: '欢迎回来!', type: 'success' })
        router.push("/admin")
    } else {
        router.push("/login")
    }
}

</script>

<style scoped>
div {
    user-select: none;
    height: 100%;
    font-size: 1.2rem;
    overflow: hidden;
    text-overflow: ellipsis;
    color: aliceblue;
    text-align: center;
    line-height: 50px;
    font-family: "ZCOOL";
}

.wrap {
    z-index: 2001;
}

.link {
    cursor: pointer;
}

.link:hover {
    text-shadow: 2px 3px 1px #F0A500;
}

.naviBar {
    position: absolute;
    height: 50px;
    width: 100%;
}

.avatar {
    width: 2.5rem;
    height: 2.5rem;
    background-size: 2.5rem;
    vertical-align: middle;
    display: inline-block;
    border-radius: 50px;
}
</style>