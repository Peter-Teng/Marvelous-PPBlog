<template>
    <el-scrollbar ref="scrollbarRef" height="calc(100vh)" @scroll="scroll">
        <div class="wrap">
            <div class="banner" v-if="article.thumbnail"
                :style="{ 'background-image': 'url(' + article.thumbnail + ')' }">
                <bar v-show="showNavigation" :background-color="navigationColor" :font-color="navigationFontColor">
                </bar>
            </div>
            <div class="body">
                <el-row>
                    <el-col :span="1"></el-col>
                    <el-col :span="22">
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
                            <div v-html="renderedContent" v-loading="rendering"></div>
                        </div>
                    </el-col>
                    <el-col :span="1"></el-col>
                </el-row>
            </div>
            <div>
                <pageFoot></pageFoot>
            </div>
            <el-backtop :right="30" :bottom="80" target=".el-scrollbar__wrap" :visibility-height="400">
                <img class="backtop" src="../assets/images/rocket.svg" />
            </el-backtop>
        </div>
    </el-scrollbar>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

import api from '../api/index'
import md from '../utils/marked'

import bar from "../components/general/Navigation.vue"
import pageFoot from "../components/general/PageFoot.vue"

const route = useRoute()
const article = ref({})
const userInfo = ref({})
const articleId = ref(0)

const rendering = ref(true)
const renderedContent = ref('')

const showNavigation = ref(true)
const prevPos = ref(0)
const navigationColor = ref("transparent")
const navigationFontColor = ref("aliceblue")

const scroll = (pos) => {
    if (pos.scrollTop < 200) {
        showNavigation.value = true
        navigationColor.value = "#202020f3"
        navigationFontColor.value = "aliceblue"
        return
    }
    if (pos.scrollTop - prevPos.value > 50) {
        showNavigation.value = false
    }
    if (prevPos.value - pos.scrollTop > 25) {
        showNavigation.value = true
        if (pos.scrollTop > 200) {
            navigationColor.value = "#EEEEEEF0"
            navigationFontColor.value = "#202020"
        }
    }
    prevPos.value = pos.scrollTop
}

const loadArticle = (articleId) => {
    api.getArticleById(articleId).then(res => {
        article.value = res.data.data
        renderedContent.value = md.render(article.value.content)
    })
}

onMounted(() => {
    articleId.value = route.params.articleId
    loadArticle(articleId.value)
    rendering.value = false
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
    min-height: calc(50vh);
}

.articleInfo {
    display: inline-block;
}

.title {
    font-family: 'JetBrainsMono';
    font-size: 2.5rem;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: azure;
}

.tag {
    margin: 2px auto;
    font-size: 1.25rem;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: #EEEEEE;
    font-family: "ZCOOL";
}

.infoList {
    margin-top: 3px;
    margin-bottom: 10px;
}

.infoItem {
    display: inline-block;
    font-size: 0.9rem;
    color: #D2DAFF;
    margin: 2px 10px 3px 10px;
    font-family: "ZCOOL";
}

.articleBody {
    font-family: 'JetBrainsMono';
    width: 85%;
    text-align: left;
    margin: 5px auto;
    font-size: 1rem;
    text-shadow: 1px 1.5px 0.5px #001d6ecf;
    color: #F9F9F9;
}

.backtop {
    width: 2rem;
    height: 2rem;
}
</style>