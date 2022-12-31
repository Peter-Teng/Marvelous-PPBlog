<template>
    <div v-wave class="wrap">
        <div class="tagList">标签列表</div>
        <div class="tags"><el-tag v-for="tag in tags" :key="tag.id" class="tagItem">
                🏷️{{ tag.name }}
            </el-tag></div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'

import api from '../api/index'

const tags = ref([])

onMounted(() => {
    api.getTags().then(res => {
        for (let item of res.data.data) {
            tags.value.push(item)
        }
    })
})
</script>

<style scoped>
.wrap {
    width: 300px;
    height: 350px;
    border-radius: 20px;
    box-shadow: 1px 1px 2px 2px #0a0a0a4a;
    background: linear-gradient(45deg, #0f0c29, #302b63, #24243e);
    ;
    margin-bottom: 50px;
    text-align: center;
    border: 2px solid #0A2647;
}

.tagList {
    margin: 40px auto 25px auto;
    font-size: 1.75em;
    color: #E7F6F2;
}

.tags {
    width: 90%;
    margin: 10px auto;
}

.tagItem {
    cursor: pointer;
    min-width: 50px;
    min-height: 25px;
    color: #112031;
    background-color: #FAF7F0;
    margin: 5px 3px;
    font-size: 1.0em;
    border: 0px;
}

.tagItem:hover {
    animation: scale 1s infinite;
}

/* 放大 */
@keyframes scale {
    0% {
        transform: scale(0.95);
    }

    50% {
        transform: scale(1.1);
    }

    100% {
        transform: scale(0.95);
    }
}
</style>