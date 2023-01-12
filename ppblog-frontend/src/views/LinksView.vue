<template>
    <el-scrollbar ref="scrollbarRef" height="calc(100vh)" @scroll="scroll">
        <div class="wrap">
            <div class="banner" :style="{ 'background-image': 'url(' + background.navigation.url + ')' }">
                <bar v-show="showNavigation" :background-color="navigationColor" :font-color="navigationFontColor">
                </bar>
                <div class="title">
                    资源导航——发现各种有趣的网站
                </div>
            </div>
            <div class="body">
                <el-row>
                    <el-col :span="3"></el-col>
                    <el-col :span="18" class="content">
                        <div>
                            <div class="linkCards" v-loading="loading">
                                <linkCard v-for="(links, key, index) in allLinks" :key="index" :links="links"
                                    :title="key">
                                </linkCard>
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
import { onMounted, ref } from 'vue'

import background from '../storage/background'
import api from '../api/index'
import bar from "../components/general/Navigation.vue"
import linkCard from "../components/general/LinksCard.vue"
import pageFoot from "../components/general/PageFoot.vue"

const allLinks = ref({})
const loading = ref(true)

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

onMounted(() => {
    api.getLinks().then(res => {
        for (let item of res.data.data) {
            if (item.category in allLinks.value) {
                if (item.topped > 0) {
                    allLinks.value[item.category].unshift(item)
                } else {
                    allLinks.value[item.category].push(item)
                }
            } else {
                allLinks.value[item.category] = [item]
            }
        }
        loading.value = false
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

.body {
    width: 100%;
    margin: 50px auto;
    text-align: center;
}

.title {
    width: 40%;
    display: block;
    margin: auto;
    font-size: 2.5rem;
    text-shadow: 2px 3px 1px #3a3b3e;
    text-align: center;
    color: azure;
}

.content {
    min-height: calc(60vh);
    display: inline-block;
    box-shadow: 3.5px 3.5px 20px 20px #041C32;
    background-color: #0F0F0F;
    padding: 10px auto;
    border-radius: 25px;
}

.linkCards {
    min-height: calc(40vh);
}

.backtop {
    width: 2rem;
    height: 2rem;
}
</style>