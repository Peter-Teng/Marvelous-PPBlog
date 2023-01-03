<template>
    <div class="wrap">
        <div v-wave class="banner" :style="{ 'background-image': 'url(' + background.navigation + ')' }">
            <bar></bar>
            <div class="title">
                资源导航——发现各种有趣的网站
            </div>
        </div>
        <div class="body">
            <div class="body">
                <el-row>
                    <el-col :span="3"></el-col>
                    <el-col :span="18">
                        <div class="content">
                            <div class="linkCards">
                                <linkCard></linkCard>
                            </div>
                        </div>
                    </el-col>
                    <el-col :span="3"></el-col>
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

import background from '../utils/background'
import api from '../api/index'
import bar from "../components/Navigation.vue"
import linkCard from "../components/LinksCard.vue"
import pageFoot from "../components/PageFoot.vue"

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

.body {
    width: 100%;
    margin: 50px auto;
    text-align: center;
    min-height: calc(60vh);
}

.title {
    width: 40%;
    display: block;
    margin: auto;
    font-size: 2.0em;
    text-shadow: 2px 3px 1px rgba(16, 16, 16, 0.85);
    text-align: center;
    color: azure;
}

.content {
    display: inline-block;
    box-shadow: 3.5px 3.5px 20px 20px #041C32;
    background-color: #0F0F0F;
    padding: 10px auto;
    border-radius: 25px;
}
</style>