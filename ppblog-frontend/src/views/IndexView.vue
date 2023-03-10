<template>
  <el-scrollbar ref="scrollbarRef" height="calc(100vh)" @scroll="scroll">
    <div class="wrap">
      <div class="banner" :style="{ 'background-image': 'url(' + background.index.url + ')' }">
        <bar v-show="showNavigation" :background-color="navigationColor" :font-color="navigationFontColor"></bar>
        <div class="bannerEffect">
          <div class="taboo">
            {{ userInfo.taboo }}
          </div>
          <div class="printer" @click="changePoem()">
            <h3 class="poem">
              {{ displayedPoem }}<span class="cursor">|</span>
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
                <div style="font-size: 0.9rem;">💡博文推荐</div>
              </el-divider>
              <div class="articleList">
                <div>
                  <articleCard v-for="article in articles" :key="article.id" :article="article"></articleCard>
                </div>
                <div class="paginationWrap" style="text-align: center; color: blue;">
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
      <el-backtop :right="30" :bottom="80" target=".el-scrollbar__wrap" :visibility-height="400">
        <img class="backtop" src="../assets/images/rocket.svg" />
      </el-backtop>
    </div>
  </el-scrollbar>
</template>
  
<script setup>
import { onMounted, ref, watch } from 'vue'
import api from '../api/index'
import background from '../storage/background'

import bar from "../components/general/Navigation.vue"
import wave from "../components/general/Wave.vue"
import infoCard from "../components/general/InfoCard.vue"
import pageFoot from "../components/general/PageFoot.vue"
import newsCard from "../components/general/NewsCard.vue"
import tagCard from "../components/general/IndexTagCard.vue"
import articleCard from "../components/general/ArticleCard.vue"

const waveColor = "#251B37fa"
const poem = ref("本来无一物，何处惹尘埃？")
const displayedPoem = ref("")
const total = ref(0)
const articles = ref([])
const userInfo = ref({})
const pageSize = ref(5)

const showNavigation = ref(true)
const prevPos = ref(0)
const navigationColor = ref("transparent")
const navigationFontColor = ref("aliceblue")

var timer = null
const speed = 300
const signal = ref(true)

const printing = () => {
  displayedPoem.value = ""
  clearTimeout(timer)
  var counter = 1
  
  const print = () => {
    displayedPoem.value = poem.value.substring(0, counter)
    counter++

    if(counter != poem.value.length + 1) {
      timer = setTimeout(print, speed);
    }
  }

  const erase = () => {
    displayedPoem.value = poem.value.substring(0, counter)
    counter--

    if(counter != -1) {
      timer = setTimeout(erase, speed);
    }
  }
  
  const emitSignal = () => {
    signal.value = !signal.value
  }

  print()
  setTimeout(erase, speed * poem.value.length * 2)
  setTimeout(emitSignal, speed * poem.value.length * 4)
}

watch(
    () => signal.value,
    async () => {
      console.log(poem.value)
      api.getPoem(poem)
      setTimeout(printing, 1500)
    }
)

const scroll = (pos) => {
  if (pos.scrollTop < 400) {
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
  })
})

onMounted(() => {
  api.getPoem(poem)
  setTimeout(printing, 500)
})

onMounted(() => {
  loadArticles(1)
})

</script>

<style scoped>
div {
  font-family: 'ZCOOL';
}

.wrap {
  background-color: #251B37;
}

.lowerWave {
  padding-top: 5px;
}

.banner {
  display: flex;
  width: 100%;
  height: calc(100vh);
  background-size: cover;
  background-position: center;
  border-radius: 0 0 5px 5px;
}

.bannerEffect {
  user-select: none;
  width: 40%;
  display: block;
  margin: calc(40vh) auto;
}

.taboo {
  font-size: 3rem;
  text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
  text-align: center;
  color: azure;
}

.poem {
  font-size: 1.5rem;
  text-align: center;
  line-height: 50px;
  font-weight: lighter;
}

.cursor {
  margin-left: 1px;
  animation: hideToShow 1.0s infinite;
  font-weight: 200;
}

.printer {
  margin: 15px auto;
  color: antiquewhite;
  background: #17171795;
  border-radius: 10px;
  width: 90%;
  height: 50px;
  padding-left: 10px;
  padding-right: 10px;
}

.body {
  width: 100%;
}

.content {
  margin: calc(5vh) 75px 0px 75px;
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

.backtop {
  width: 2rem;
  height: 2rem;
}
</style>

<style>
.paginationWrap .el-pagination .el-icon {
  font-size: 18px !important;
}

.paginationWrap .el-pagination.is-background .btn-prev {
  background-color: #251B37 !important;
  color: aliceblue !important;
}

.paginationWrap .el-pagination.is-background .btn-prev:hover {
  background-color: #251B37 !important;
  color: #FFF80A !important;
}

.paginationWrap .el-pager li {
  font-weight: bold;
  background: #251B37 !important;
  color: aliceblue !important;
}

.paginationWrap .el-pager li:hover {
  background: #fefefe !important;
  color: #000000 !important;
}

.paginationWrap .el-pager li.is-active {
  font-weight: bold;
  background: #fefefe !important;
  color: #000000 !important;
}

.paginationWrap .el-pager li.is-active:hover {
  font-weight: bold;
  background: #fefefe !important;
  color: #000000 !important;
}

.paginationWrap .el-pagination.is-background .btn-next {
  background-color: #251B37 !important;
  color: aliceblue !important;
}

.paginationWrap .el-pagination.is-background .btn-next:hover {
  background-color: #251B37 !important;
  color: #FFF80A !important;
}

.paginationWrap .el-pagination .el-pagination__goto {
  font-size: 0.9rem;
  color: aliceblue !important;
  padding-right: 20px;
}

.paginationWrap .el-pagination .el-input__wrapper {
  background-color: #251B37 !important;
}

.paginationWrap .el-pagination .el-input__inner {
  color: aliceblue !important;
  font-size: 0.9rem;
}
</style>
