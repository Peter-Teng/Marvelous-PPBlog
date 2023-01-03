<template>
    <div class="wrap">
        <div v-wave class="banner" v-if="article.thumbnail" :style="{ 'background-image': 'url(' + article.thumbnail + ')' }">
            <bar></bar>
        </div>
        <div class="body">
            <el-row>
                <el-col :span="2"></el-col>
                <el-col :span="20">
                    <div class="articleInfo">
                        <div class="title">
                            {{ article.title }}
                        </div>
                        <div class="tag">
                            [{{ article.tagName }}]
                        </div>
                        <div class="infoList">
                            <div class="infoItem">👨‍🎓{{ userInfo.username }}</div>
                            <div class="basicInfo">
                                <div class="infoItem">📅首次创建时间： {{ article.createDate }}</div>
                                <div class="infoItem">📅最后修改时间： {{ article.modifyDate }}</div>
                            </div>
                            <div class="infoItem">🔥热度值{{ article.heat }}</div>
                        </div>
                    </div>
                    <div class="articleBody">
                        <div v-html="renderedContent"></div>
                    </div>
                </el-col>
                <el-col :span="2"></el-col>
            </el-row>
        </div>
        <div>
            <pageFoot></pageFoot>
        </div>
        <el-backtop :right="30" :bottom="80">
            <div>🚀</div>
        </el-backtop>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

import api from '../api/index'
import md from '../utils/marked'

import bar from "../components/Navigation.vue"
import pageFoot from "../components/PageFoot.vue"

const route = useRoute()
const article = ref({})
const userInfo = ref({})
const articleId = ref(0)

const renderedContent = ref('')

const loadArticle = (articleId) => {
    api.getArticleById(articleId).then(res => {
        article.value = res.data.data
        renderedContent.value = md.render(article.value.content)
        console.log(renderedContent.value)
    })
}

onMounted(() => {
    articleId.value = route.params.articleId
    loadArticle(articleId.value)
})

onMounted(() => {
    api.getInfo().then(res => {
        userInfo.value = res.data.data
    })
})
</script>

<style scoped>
@import url("../assets/css/monokai.css");
.wrap {
    background-color: #251B37;
}

.banner {
    display: flex;
    width: 100%;
    height: 250px;
    box-shadow: 0px 5px 0px 0px #3c3c3cda;
    background-size: cover;
    background-position: center;
    border-radius: 0 0 5px 5px;
}

.body {
    width: 100%;
    margin: 50px auto;
    text-align: center;
    min-height: calc(60vh);
}

.articleInfo {
    display: inline-block;
}

.title {
    font-family: 'JetBrainsMono';
    font-size: 3.5em;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: azure;
}

.tag {
    margin: 2px auto;
    font-size: 1.5em;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: #EEEEEE;
    font-family: "BubbleTea";
}

.infoList{
    margin-top: 3px;
    margin-bottom: 10px;
}
.infoItem {
    display: inline-block;
    font-size: 1em;
    color: #D2DAFF;
    margin: 2px 10px 3px 10px;
    font-family: "ZCOOL";
}

.articleBody {
    font-family: 'Article';
    width: 80%;
    text-align: left;
    margin: 5px auto;
    font-size: 1.1em;
    text-shadow: 1px 1.5px 0.5px #001d6ecf;
    color: #F9F9F9;
}
</style>