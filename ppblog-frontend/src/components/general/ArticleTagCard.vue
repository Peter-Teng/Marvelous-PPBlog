<template>
    <div v-wave class="wrap">
        <div class="tagList">๐๏ธๆ ็ญพไปฌโโ(The Tags)</div>
        <div class="tags">
            <div style="display: grid; width: 100%;"><el-tag class="tagAll" @click="toArticles('all')"><div>๐ฅๅจ้จๅๆ</div></el-tag></div>
            <br/>
            <el-tag v-for="tag in tags" :key="tag.id" class="tagItem" @click="toArticles(tag.name)">
                <div class="tag">#{{ tag.name }}</div>
            </el-tag>
        </div>
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
    width: 100%;
    height: 100%;
    min-height: 250px;
    border-radius: 20px;
    box-shadow: .5px .5px 1px 1px #eef1ff2f;
    background-image: linear-gradient(135deg, #FD6585 10%, #0D25B9 100%);
    margin-bottom: 30px;
    text-align: center;
    border: 2px solid #0A2647;
}

.wrap:hover {
    animation: shadow .3s 1;
    animation-fill-mode: forwards;
}

.tagList {
    font-family: 'ZCOOL';
    margin: 20px auto 25px auto;
    font-size: 1.8rem;
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
    background-color: #144272;
    box-shadow: .5px .5px 1px 1px #46346f;
    margin: 5px 3px;
    font-size: 0.9rem;
    border: 0px;
}

.tagItem:hover {
    background-color: #EFF5F5;
    color: #0081C9;
}

.tagAll{
    font-family: 'ZCOOL';
    cursor: pointer;  
    width: 150px;
    height: 35px;
    color: #153462;
    background-color: #EFF5F5;
    box-shadow: .5px .5px 1px 1px #46346f;
    margin: 5px auto 5px auto;
    font-size: 1.0rem;
    border: 0px;
}

.tagAll:hover {
    background-color: #153462;
    color: #EFF5F5;
}
</style>