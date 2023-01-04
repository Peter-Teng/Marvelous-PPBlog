<template>
    <div v-wave class="wrap">
        <el-row>
            <el-col :span="4">
                <div ref="iconRef" class="icon" @click="playAnime(iconRef, 'heartBeat')">📢</div>
            </el-col>
            <el-col :span="20">
                <div v-for="item in news" :key="item.id">
                    <div class="newsWrap">
                        <div class="title">~ {{ item.title }} ~</div>
                        <div class="content">{{ item.content }}</div>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'

import api from '../api/index'
import playAnime from '../utils/animate'

const news = ref([])
const iconRef = ref("")

onMounted(() => {
    api.getNews().then(res => {
        for (let item of res.data.data) {
            if (item.topped == 1) {
                news.value.unshift(item)
            } else {
                news.value.push(item)
            }
        }
    })
})


</script>

<style scoped>
.wrap {
    width: 95%;
    margin: 0 auto;
    height: 230px;
    border-radius: 5px;
    box-shadow: 1px 1px 2px 2px #0a0a0a4a;
    background: linear-gradient(220.55deg, #9F25FF 0%, #FF7A00 100%);
}

.wrap:hover {
    animation: newsShadow .3s 1;
    animation-fill-mode: forwards;
}

.icon {
    font-size: 4.5em;
    line-height: 230px;
    animation: scale 1.5s infinite;
    text-align: center;
    user-select:none; 
}


.newsWrap{
    width: 90%;
    height: 100%;
    margin: 10px auto;
}

.title {
    padding: 5px auto 0px auto;
    text-align: center;
    font-size: 1.35em;
    color: #E7F6F2;
    font-family: "Article";
}

.content {
    text-align: left;
    font-size: 1.2em;
    margin-top: 5px;
    font-family: "MUYAO";
}
</style>