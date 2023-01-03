<template>
    <div class="wrap">
        <div v-wave class="banner" :style="{ 'background-image': 'url(' + background.statistic + ')' }">
            <bar></bar>
        </div>
        <div class="body">

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
</style>