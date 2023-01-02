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
import api from '../api/index'

const poem = ref("")
const caseInfo = ref("🏙️  @Powered by MarvelousPP")

onMounted(() => {
    api.getPoem(poem)
})
</script>

<style scoped>
.wrap {
    margin-top: 20px;
    width: 100%;
    height: 60px;
    border-radius: 50px 50px 0px 0px;
    background: linear-gradient(90deg, #FFE15D, #F49D1A);
}

.poem {
    font-size: 1em;
    text-align: center;
    line-height: 40px;
    font-weight: lighter;
    color: #393E46;
}

.case {
    font-size: 1.05em;
    text-align: center;
    line-height: 5px;
    color: #393E46;
}
</style>