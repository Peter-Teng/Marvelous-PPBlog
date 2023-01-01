<template>
    <div class="wrap">
        <div class="banner">
            <bar></bar>
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
                            <articleCard v-for="article in articles" :key="article.id" :article="article"></articleCard>
                        </div>
                        <div style="text-align: center; color: blue;">
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
        <el-backtop :right="30" :bottom="80">
            <div>🚀</div>
        </el-backtop>
    </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import { useRoute } from 'vue-router'

import api from '../api/index'

import bar from "../components/Navigation.vue"
import tagCard from "../components/ArticleTagCard.vue"
import pageFoot from "../components/PageFoot.vue"
import articleCard from "../components/ArticlesCard.vue"

const hitokoto = ref("今日の一言は何ですか？？")
const source = ref("名前がない人")
const tagName = ref("")
const route = useRoute()

const total = ref(0)
const articles = ref([])
const pageSize = ref(10)

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
    background-image: url("https://pp-blog-1300101944.cos.ap-guangzhou.myqcloud.com/assets%2Fimages%2FArticleListTop.jpg");
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
    font-size: 1.85em;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: azure;
    font-family: "MUYAO";
}

.hitokoto {
    font-size: 1.65em;
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
</style>