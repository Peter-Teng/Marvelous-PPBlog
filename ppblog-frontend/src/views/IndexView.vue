<template>
  <div class="wrap">
    <div class="banner">
      <bar></bar>
      <div class="bannerEffect">
        <div class="taboo">
          {{ taboo }}
        </div>
        <div class="printer" @click="changePoem()">
          <h3 class="poem">
            {{ poem }}<span class="cursor">|</span>
          </h3>
        </div>
      </div>
    </div>
    <div class="body">
      <wave :waveColor="waveColor" :waveTime="3000"></wave>
      <div class="content">
        <el-row class="row">
          <el-col class="lefter" :span="5">
            <infoCard v-bind:info="userInfo">
            </infoCard>
            <tagCard></tagCard>
          </el-col>
          <el-col class="blank" :span="1">
          </el-col>
          <el-col class="righter" :span="18">
            <div>
              <newsCard></newsCard>
            </div>
            <el-divider content-position="left">
              <div style="font-size: 1.0em;">💡博文推荐</div>
            </el-divider>
            <div class="articleList">
              <div>
                <articleCard v-for="article in articles" :key="article.id" :article="article"></articleCard>
              </div>
              <div style="text-align: center; color: blue;">
                <el-pagination class="pagination" :page-size="pageSize" :pager-count="7"
                  layout="prev, pager, next, jumper" :total="total" @current-change="handleChange" background />
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
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
import api from '../api/index'

import bar from "../components/Navigation.vue"
import wave from "../components/Wave.vue"
import infoCard from "../components/InfoCard.vue"
import pageFoot from "../components/PageFoot.vue"
import newsCard from "../components/NewsCard.vue"
import tagCard from "../components/IndexTagCard.vue"
import articleCard from "../components/ArticlesCard.vue"

const waveColor = "#251B37fa"
const taboo = ref("")
const poem = ref("")
const total = ref(0)
const articles = ref([])
const userInfo = ref({})
const pageSize = ref(5)



const changePoem = () => {
  api.getPoem(poem)
}

const handleChange = (value) => {
  articles.value = []
  loadArticles(value)
}

const loadArticles = (pageNum) => {
  api.getPublicArticles(pageNum).then(res => {
    for (let row of res.data.data.rows) {
      articles.value.push(row)
    }
    total.value = Number(res.data.data.total)
  })
}

onMounted(() => {
  api.getInfo().then(res => {
    userInfo.value = res.data.data
    taboo.value = userInfo.value.taboo
  })
})

onMounted(() => {
  api.getPoem(poem)
})

onMounted(() => {
  loadArticles(1)
})

</script>

<style scoped>
.wrap {
  background-color: #251B37;
}

.lowerWave {
  padding-top: 5px;
}

.banner {
  display: flex;
  width: 100%;
  background-image: url("https://pp-blog-1300101944.cos.ap-guangzhou.myqcloud.com/assets%2Fimages%2FindexViewBG.jpg");
  height: calc(100vh);
  background-size: cover;
  background-position: center;
  border-radius: 0 0 5px 5px;
}

.bannerEffect {
  width: 40%;
  display: block;
  margin: 20% auto;
}

.taboo {
  font-size: 3em;
  text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
  text-align: center;
  color: azure;
}

.poem {
  font-size: 1.5em;
  text-align: center;
  line-height: 50px;
  font-weight: lighter;
}

.cursor {
  margin-left: 1px;
  animation: hideToShow 1.0s infinite;
  font-weight: 200;
}

@keyframes hideToShow {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

.printer {
  cursor: pointer;
  margin: 15px auto;
  color: antiquewhite;
  background: #17171795;
  border-radius: 10px;
  width: 450px;
  height: 50px;
  padding-left: 10px;
  padding-right: 10px;
}

.body {
  width: 100%;
}

.content {
  margin: 0px auto;
  margin: 20px 75px 0px 75px;
  width: 90%;
  border-radius: 30px;
  background-color: #00000000;
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

<style>
.el-pagination .el-icon {
  font-size: 18px !important;
}

.el-pagination.is-background .btn-prev {
  background-color: #251B37 !important;
  color: aliceblue !important;
}

.el-pagination.is-background .btn-prev:hover {
  background-color: #251B37 !important;
  color: #FFF80A !important;
}

.el-pager li {
  font-weight: bold;
  background: #251B37 !important;
  color: aliceblue !important;
}

.el-pager li:hover {
  background: #fefefe !important;
  color: #000000 !important;
}

.el-pager li.is-active {
  font-weight: bold;
  background: #fefefe !important;
  color: #000000 !important;
}

.el-pager li.is-active:hover {
  font-weight: bold;
  background: #fefefe !important;
  color: #000000 !important;
}

.el-pagination.is-background .btn-next {
  background-color: #251B37 !important;
  color: aliceblue !important;
}

.el-pagination.is-background .btn-next:hover {
  background-color: #251B37 !important;
  color: #FFF80A !important;
}

.el-pagination .el-pagination__goto {
  font-size: 1em;
  color: aliceblue !important;
  padding-right: 20px;
}

.el-pagination .el-input__wrapper {
  background-color: #251B37 !important;
}

.el-pagination .el-input__inner {
  color: aliceblue !important;
  font-size: 1em;
}
</style>
