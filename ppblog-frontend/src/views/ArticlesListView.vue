<template>
    <el-scrollbar ref="scrollbarRef" height="calc(100vh)" @scroll="scroll">
        <div class="wrap">
            <div class="banner" :style="{ 'background-image': 'url(' + background.articlesList + ')' }">
                <bar v-show="showNavigation" :background-color="navigationColor" :font-color="navigationFontColor">
                </bar>
                <div class="bannerEffect">
                    <div class="source">
                        {{ source }}
                    </div>
                    <div class="hitokoto">
                        {{ hitokoto }}
                    </div>
                </div>
            </div>
            <div class="body">
                <el-row>
                    <el-col :span="3"></el-col>
                    <el-col :span="18">
                        <div class="tags">
                            <tagCard></tagCard>
                        </div>
                        <div class="articleList">
                            <div>
                                <articleCard v-for="article in articles" :key="article.id" :article="article">
                                </articleCard>
                            </div>
                            <div style="text-align: center; color: blue;" class="paginationWrap">
                                <el-pagination class="pagination" :page-size="pageSize" :pager-count="7"
                                    layout="prev, pager, next, jumper" :total="total" @current-change="handleChange"
                                    background />
                            </div>
                        </div>
                    </el-col>
                    <el-col :span="3"></el-col>
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
import { onMounted, ref, watch } from 'vue'
import { useRoute } from 'vue-router'

import api from '../api/index'

import background from '../storage/background'
import bar from "../components/general/Navigation.vue"
import tagCard from "../components/general/ArticleTagCard.vue"
import pageFoot from "../components/general/PageFoot.vue"
import articleCard from "../components/general/ArticleCard.vue"

const hitokoto = ref("今日の一言は何ですか？？")
const source = ref("名前がない人")
const tagName = ref("")
const route = useRoute()

const total = ref(0)
const articles = ref([])
const pageSize = ref(10)

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

const handleChange = (value) => {
    articles.value = []
    loadArticles(value)
}

const loadArticles = (tagName, pageNum, pageSize) => {
    articles.value = []
    api.getPublicArticlesByTag(tagName, pageNum, pageSize).then(res => {
        for (let row of res.data.data.rows) {
            articles.value.push(row)
        }
        total.value = Number(res.data.data.total)
    })
}

onMounted(() => {
    api.getHitokoto().then(res => {
        hitokoto.value = res.data.hitokoto
        source.value = res.data.from
    })
})

onMounted(() => {
    tagName.value = route.params.tagName
    loadArticles(tagName.value, 1, pageSize.value)
})

watch(
    () => route.params.tagName,
    async newTagName => {
        tagName.value = newTagName
        loadArticles(tagName.value, 1, pageSize.value)
    }
)

</script>

<style scoped>
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

.bannerEffect {
    width: 40%;
    display: block;
    margin: auto;
}

.source {
    font-size: 2rem;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: azure;
    font-family: "MUYAO";
}

.hitokoto {
    font-size: 1.75rem;
    padding-top: 20px;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    font-family: "MUYAO";
    color: azure;
}

.body {
    width: 100%;
    margin: 50px auto;
    text-align: center;
}

.tags {
    width: 100%;
    display: inline-block;
}

.articleList {
    padding-top: 5px;
    width: 100%;
    margin: 0 auto;
    border-radius: 20px;
    background: #0101010f;
}

.pagination {
    margin: 5px auto auto 0px;
    padding-bottom: 10px;
    display: inline-flex;
}

.backtop {
    width: 2rem;
    height: 2rem;
}
</style>