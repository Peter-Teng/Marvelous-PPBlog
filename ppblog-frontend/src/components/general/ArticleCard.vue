<template>
    <div v-wave="{
        color: '#72FFFF',
        initialOpacity: 0.5,
        easing: 'ease-in',
    }" class="wrap">
        <el-row>
            <el-col :span="11" class="columns">
                <div class="title" ref="titleRef" @mouseenter="playAnime(titleRef, 'bounceIn')"
                    @click="toArticle(article.id)">{{ article.title }}
                </div>
                <el-row>
                    <el-col :span="1"></el-col>
                    <el-col :span="10" class="date">
                        <div>⏳发布于: {{ prettyDate(article.createDate) }}</div>
                    </el-col>
                    <el-col :span="1"></el-col>
                    <el-col :span="10" class="date">
                        <div>⌛最后编辑于: {{ prettyDate(article.modifyDate) }}</div>
                    </el-col>
                    <el-col :span="1"></el-col>
                </el-row>
                <div class="heat">🔥{{ article.heat }}</div>
                <div class="summary" @click="toArticle(article.id)">{{ article.summary }}</div>
                <div class="tag">🏷️[{{ article.tagName }}]</div>
            </el-col>
            <el-col :span="13" class="columns">
                <div ref="thumbnailRef" @mouseenter="playAnime(thumbnailRef, 'pulse', true)"
                    @click="toArticle(article.id)" style="width: 100%; height: 100%;"><el-image class="thumbnail"
                        :src="article.thumbnail" :fit="fit" lazy /></div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import playAnime from '../../utils/animate'
import prettyDate from '../../utils/prettyDate'

const props = defineProps({
    article: Object
});

const router = useRouter()
const titleRef = ref('')
const thumbnailRef = ref('')

const fit = ref("cover")

const toArticle = (articleId) => {
    router.push(`/article/${articleId}`)
}

</script>

<style scoped>
div {
    text-align: center;
    font-family: "ZCOOL";
}

.wrap {
    user-select: none;
    width: 90%;
    margin: 20px auto;
    height: 300px;
    border-radius: 20px;
    box-shadow: 1px 1px 2px 2px #3F3B6C;
    background-image: linear-gradient(to top, #04009a, #001699, #002297, #052c95, #133491, #193893, #1f3c95, #244097, #2542a0, #2743a8, #2a45b1, #2d46b9);
    white-space: pre-line;
}

.wrap:hover {
    animation: articleCard .3s 1;
    animation-fill-mode: forwards;
}

.columns {
    width: 100%;
    height: 300px;
}


.thumbnail {
    cursor: pointer;
    border-radius: 20px;
    width: 100%;
    height: 100%;
}

.title {
    font-family: 'ZCOOL';
    height: 4.5rem;
    text-align: center;
    width: 90%;
    margin: 15px auto 0px auto;
    cursor: pointer;
    font-size: 2rem;
    color: #F1F6F5;
    text-shadow: 2px 3px 1px rgba(22, 22, 22, 0.823);
}

.date {
    width: 95%;
    height: 10%;
    font-family: 'MUYAO';
    margin: 5px auto 0px auto;
    font-size: 0.75rem;
    color: #fff2cfec;
    font-style: oblique;
}

.heat {
    height: 5%;
    font-size: 0.9rem;
    color: #DD5353;
    font-weight: bolder;
}

.summary {
    color: rgb(232 232 233);
    height: 40%;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: left;
    margin: 10px 30px 5px 30px;
    cursor: pointer;
    font-size: 0.9rem;
    font-style: italic;
    font-weight: lighter;
    font-family: 'JetBrainsMono';
}

.tag {
    height: 5%;
    text-align: left;
    margin: 10px auto 0px 50px;
    font-size: 0.9rem;
    color: #FFF8EA;
}
</style>