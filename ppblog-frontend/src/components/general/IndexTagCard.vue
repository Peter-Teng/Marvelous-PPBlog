<template>
    <div v-wave class="wrap">
        <div class="tagList">🏖️标签列表</div>
        <div class="tags"><el-tag v-for="tag in tags" :key="tag.id" class="tagItem" @click="toArticles(tag.name)">
                #{{ tag.name }}
            </el-tag></div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

import api from '../../api/index'

const tags = ref([])

const router = useRouter()

onMounted(() => {
    api.getTags().then(res => {
        for (let item of res.data.data) {
            tags.value.push(item)
        }
    })
})

const toArticles = (tagName) => {
    router.push(`/articles/${tagName}`)
}

</script>

<style scoped>
.wrap {
    user-select: none;
    width: 300px;
    height: 350px;
    border-radius: 20px;
    box-shadow: .5px .5px 1px 1px #eef1ff2f;
    background: linear-gradient(45deg, #0f0c29, #302b63, #24243e);
    margin-bottom: 50px;
    text-align: center;
    border: 2px solid #0A2647;
}

.wrap:hover {
    animation: shadow .3s 1;
    animation-fill-mode: forwards;
}

.tagList {
    margin: 40px auto 25px auto;
    font-size: 1.75em;
    color: #E7F6F2;
}

.tags {
    text-align: left;
    width: 90%;
    margin: 10px auto;
}

.tagItem {
    cursor: pointer;
    min-width: 50px;
    min-height: 25px;
    color: #112031;
    background-color: #F9F9F9;
    margin: 5px 3px;
    font-size: 0.75em;
    border: 0px;
}

.tagItem:hover {
    background-color: #F7A76C;
    color: #FAF7F0;
}

</style>