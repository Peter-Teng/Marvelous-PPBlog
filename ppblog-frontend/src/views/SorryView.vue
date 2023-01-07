<template>
    <div class="wrap" :style="{ 'background-image': 'url(' + background.sorry + ')' }">
        <div class="content">
        <img src="../assets/images/UFO.png" class="image" />
            <div class="CHN">
                {{ textCHN }}
            </div>
            <div class="ENG">
                {{ textENG }}
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import $ from 'jquery'

import background from '../utils/background'

const router = useRouter()
const textCHN = ref("抱歉,本站尚未完成适合您设备宽度的适配\n请使用PC(最大化)或平板(横屏)后刷新访问")
const textENG = ref("Sorry! The website does not support your device. \nPlease visit via PC (maximize the browser) or tablets (landscape mode)")

onMounted(() => {
    let width = $("body").width();
    if (width >= 750) {
        router.push("/")
    }
})
</script>

<style scoped>
.wrap {
    width: 100%;
    height: calc(100vh);
    background-repeat: no-repeat;
    background-size: cover;
    display: table;
    background-position: center;
    text-align: center;
}

.image {
    width: 75px;
    height: 75px;
    vertical-align: middle;
}

.content {
    width: 90%;
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    color: aliceblue;
}

.CHN {
    font-size: 5rem;
    font-family: 'ZCOOL';
}

.ENG {
    font-size: 2.5rem;
}
</style>