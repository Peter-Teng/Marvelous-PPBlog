<template>
    <div v-wave class="wrap">
        <div class="tagList">🏕️标签们——(The Tags)</div>
        <div class="tags"><el-tag v-for="tag in tags" :key="tag.id" class="tagItem" @click="toArticles(tag.name)">
                <div class="tag" >#{{ tag.name }}</div>
            </el-tag></div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

import api from '../api/index'

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
    width: 1200px;
    height: 250px;
    border-radius: 20px;
    box-shadow: .5px .5px 1px 1px #eef1ff2f;
    background-image: linear-gradient( 135deg, #FD6585 10%, #0D25B9 100%);
    margin-bottom: 50px;
    text-align: center;
    border: 2px solid #0A2647;
}

.tagList {
    margin: 20px auto 25px auto;
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
    min-width: 100px;
    min-height: 30px;
    color: #EFF5F5;
    background-color: #6C4AB6;
    box-shadow: .5px .5px 1px 1px #46346f;
    margin: 5px 3px;
    font-size: 0.85em;
    border: 0px;
}

.tagItem:hover {
    background-color: #EFF5F5;
    color: #9F73AB;
}
</style>