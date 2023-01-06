<template>
    <el-scrollbar ref="scrollbarRef" height="calc(100vh)" @scroll="scroll">
        <div class="wrap">
            <div class="banner" :style="{ 'background-image': 'url(' + background.statistic + ')' }">
                <bar v-show="showNavigation" :background-color="navigationColor" :font-color="navigationFontColor">
                </bar>
                <div class="title">
                    关于本站的各式各样🍟
                </div>
            </div>
            <div class="body">
                <el-row>
                    <el-col :span="1"></el-col>
                    <el-col :span="22" class="content">
                        <el-divider border-style="dashed" />
                        <div class="summaryBlock">
                            <div class="blockTitle">站点数据</div>
                            <el-row>
                                <el-col :span="1"></el-col>
                                <el-col :span="10">
                                    <div class="webStatistic" v-loading="loadingOverview" v-wave>
                                        <div class="statisticTitle">站内统计</div>
                                        <div class="staticItems">
                                            <div class="statisticItem" v-for="(item, key, index) in overviewStatistic"
                                                :key="index">
                                                <div class="ItemTitle">{{ item.key }}</div>
                                                <div class="ItemStat">{{ item.value }}</div>
                                            </div>
                                        </div>
                                        <div class="source">
                                            统计信息来源于<span class="sourceLink"
                                                @click="toLink(localhost)">MarvelousPP</span>
                                        </div>
                                    </div>
                                </el-col>
                                <el-col :span="2"></el-col>
                                <el-col :span="10">
                                    <div class="webStatistic" v-loading="loadingRequest" v-wave>
                                        <div class="statisticTitle">访问数据统计</div>
                                        <div class="staticItems">
                                            <div class="statisticItem">
                                                <div class="ItemTitle">Field</div>
                                                <div class="ItemStat">Number</div>
                                            </div>
                                        </div>
                                        <div class="source">
                                            统计信息来源于<span class="sourceLink" @click="toLink(localhost)">Source</span>
                                        </div>
                                    </div>
                                </el-col>
                                <el-col :span="1"></el-col>
                            </el-row>
                        </div>
                        <el-divider border-style="dashed" border-color="black" />
                        <div class="summaryBlock">
                            <div class="blockTitle">统计图表</div>
                            <div class="chartWrap" v-loading="loadingBlogChart" element-loading-text="正在加载图表"
                                element-loading-background="rgba(255, 255, 255, 0.5)">
                                <div id="blogChart" class="chart" v-wave></div>
                            </div>
                            <div class="chartWrap" v-loading="loadingTagChart" element-loading-text="正在加载图表"
                                element-loading-background="rgba(255, 255, 255, 0.5)">
                                <div id="tagChart" class="chart" v-wave></div>
                            </div>
                            <div class="chartWrap" v-loading="loadingLinkChart" element-loading-text="正在加载图表"
                                element-loading-background="rgba(255, 255, 255, 0.5)">
                                <div id="linkChart" class="chart" v-wave></div>
                            </div>
                        </div>
                    </el-col>
                    <el-col :span="1"></el-col>
                </el-row>
            </div>
            <div>
                <pageFoot></pageFoot>
            </div>
            <el-backtop :right="30" :bottom="80" target=".el-scrollbar__wrap" :visibility-height="400">
                <div>🚀</div>
            </el-backtop>
        </div>
    </el-scrollbar>
</template>


<script setup>
import { onMounted, ref, inject } from 'vue'

import background from '../utils/background'
import Charts from '../utils/chart'
import api from '../api/index'
import bar from "../components/general/Navigation.vue"
import pageFoot from "../components/general/PageFoot.vue"

let echarts = inject('echarts')

const localhost = ref("http://www.marvelouspp.com")
const overviewStatistic = ref({})
const loadingOverview = ref(true)

const loadingRequest = ref(true)

const blogChartStatistic = ref({})
const loadingBlogChart = ref(true)

const tagChartStatistic = ref([])
const loadingTagChart = ref(true)

const linkChartStatistic = ref([])
const loadingLinkChart = ref(true)

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
    if (prevPos.value - pos.scrollTop > 50) {
        showNavigation.value = true
        if (pos.scrollTop > 200) {
            navigationColor.value = "#202020f0"
            navigationFontColor.value = "#FAF8F1"
        }
    }
    prevPos.value = pos.scrollTop
}

const toLink = (dest) => {
    window.open(dest)
}

onMounted(() => {
    api.getOverviewStatistic().then(res => {
        overviewStatistic.value = res.data.data
        loadingOverview.value = false
    })

    api.getBlogRecords().then(res => {
        blogChartStatistic.value = res.data.data
        Charts.blogChart(echarts, blogChartStatistic.value, loadingBlogChart)
    })

    api.getTagStatistic().then(res => {
        tagChartStatistic.value = res.data.data
        Charts.tagChart(echarts, tagChartStatistic.value, loadingTagChart);
    })

    api.getLinkStatistic().then(res => {
        linkChartStatistic.value = res.data.data
        Charts.linkChart(echarts, linkChartStatistic.value, loadingLinkChart);
    })
})
</script>

<style scoped>
div {
    font-family: 'ZCOOL';
}

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

.title {
    width: 40%;
    display: block;
    margin: auto;
    font-size: 0.25rem;
    text-shadow: 2px 3px 1px #3a3b3e;
    text-align: center;
    color: azure;
}

.body {
    width: 100%;
    margin: 50px auto;
    text-align: center;
    user-select: none;
}

.content {
    min-height: calc(60vh);
    box-shadow: 3.5px 3.5px 20px 20px #041C32;
    background-color: #F0F0F0;
    padding: 10px auto;
    border-radius: 25px;
}

.summaryBlock {
    min-height: calc(30vh);
}

.blockTitle {
    text-align: left;
    margin: 10px 50px;
    font-size: 0.2rem;
}

.webStatistic {
    background-color: beige;
    border-radius: 20px;
}

.webStatistic:hover {
    box-shadow: 1px 1px 20px 1px #EEB76B;
}

.statisticTitle {
    text-align: left;
    padding-top: 10px;
    margin: 5px 15px;
    font-size: 0.175rem;
}

.staticItems {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    text-align: center;
}

.statisticItem {
    min-width: 35%;
    text-align: center;
    padding-top: 10px;
    padding-bottom: 15px;
    margin: 10px 25px;
}

.ItemTitle {
    font-size: 0.15rem;
}

.ItemStat {
    margin-top: 10px;
    font-size: 0.3rem;
    color: #150485;
}

.source {
    text-align: left;
    font-size: 0.05em;
    margin: 5px 30px;
    padding-bottom: 10px;
}

.sourceLink {
    cursor: pointer;
    color: chocolate;
}

.chart {
    margin: 20px auto;
    display: inline-block;
    min-width: 1200px;
    min-height: 500px;
    border-radius: 20px;
    padding: 5px;
}

.chart:hover {
    box-shadow: 0.25px 0.25px 10px 1px #0F3460;
}
</style>