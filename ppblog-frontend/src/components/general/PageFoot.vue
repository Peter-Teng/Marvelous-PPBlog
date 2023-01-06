<template>
    <div class="wrap">
        <div class="poem">
            {{ poem }}
        </div>
        <div class="case">
            {{ caseInfo }}
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import api from '../../api/index'

const poem = ref("")
const caseInfo = ref("🏙️  @Powered by MarvelousPP")

onMounted(() => {
    api.getPoem(poem)
})
</script>

<style scoped>
div {
    font-family: 'ZCOOL';
}

.wrap {
    user-select: none;
    margin-top: 20px;
    width: 100%;
    height: 60px;
    border-radius: 50px 50px 0px 0px;
    text-align: center;
    background: linear-gradient(-90deg, #FD841F, #E14D2A, #CD104D, #9C2C77);
    animation: gradientBG 10s linear infinite;
}

.poem {
    font-size: 0.9rem;
    text-align: center;
    line-height: 40px;
    font-weight: lighter;
    color: #393E46;
}

.case {
    font-size: 0.9rem;
    text-align: center;
    line-height: 5px;
    color: #393E46;
}
</style>