<template>
    <div class="wrap">
        <div v-wave class="banner" :style="{ 'background-image': 'url(' + background.statistic + ')' }">
            <bar></bar>
            <div class="title">
                关于本站的各式各样🍟
            </div>
        </div>
        <div class="body">
            <el-row>
                <el-col :span="1"></el-col>
                <el-col :span="22" class="content">
                    <el-divider border-style="dashed" />
                    <div class="summary">
                        <div class="summaryTitle">站点数据</div>
                        <el-row>
                            <el-col :span="1"></el-col>
                            <el-col :span="10">
                                <div class="webStatistic">
                                    <div class="statisticTitle">站内统计</div>
                                    <div class="staticItems">
                                        <div class="statisticItem">
                                            <div class="ItemTitle">统计title</div>
                                            <div class="ItemStat">888</div>
                                        </div>
                                    </div>
                                    <div class="source">
                                        统计信息来源于<span class="sourceLink" @click="toLink(localhost)">MarvelousPP</span>
                                    </div>
                                </div>
                            </el-col>
                            <el-col :span="2"></el-col>
                            <el-col :span="10">
                                <div class="webStatistic">
                                    <div class="statisticTitle">访问数据统计</div>
                                    <div class="staticItems">
                                        <div class="statisticItem">
                                            <div class="ItemTitle">统计title</div>
                                            <div class="ItemStat">888</div>
                                        </div>
                                    </div>
                                    <div class="source">
                                        统计信息来源于<span class="sourceLink" @click="toLink(localhost)">访问数据来源</span>
                                    </div>
                                </div>
                            </el-col>
                            <el-col :span="1"></el-col>
                        </el-row>
                    </div>
                    <el-divider border-style="dashed" border-color="black" />
                </el-col>
                <el-col :span="1"></el-col>
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

import background from '../utils/background'
import api from '../api/index'
import bar from "../components/Navigation.vue"
import pageFoot from "../components/PageFoot.vue"

const localhost = ref("http://www.baidu.com")

const toLink = (dest) => {
    window.open(dest)
}
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
    font-size: 2.0em;
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

.summaryTitle {
    text-align: left;
    margin: 10px 50px;
    font-size: 1.85em;
}

.webStatistic {
    background-color: beige;
    border-radius: 20px;
}

.statisticTitle {
    text-align: left;
    padding-top: 10px;
    margin: 5px 15px;
    font-size: 1.5em;
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
    font-size: 1.35em;
}

.ItemStat {
    margin-top: 10px;
    font-size: 2.5em;
}

.source {
    text-align: left;
    font-size: 0.8em;
    margin: 5px 30px;
    padding-bottom: 10px;
}

.sourceLink {
    cursor: pointer;
    color: chocolate;
}
</style>