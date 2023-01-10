<template>
    <div class="wrap">
        <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="22">
                <div class="body">
                    <div class="title">文章编辑</div>
                    <el-form :model="article" label-width="10rem" class="articleEdit">
                        <el-form-item label="文章标题">
                            <el-input v-model="article.title" />
                        </el-form-item>
                        <el-form-item label="文章标题图像URL">
                            <el-input v-model="article.thumbnail" />
                        </el-form-item>
                        <el-form-item label="文章摘要">
                            <el-input v-model="article.summary" type="textarea" :rows="3" />
                        </el-form-item>
                        <el-form-item label="文章正文">
                            <v-md-editor v-model="article.content" height="30rem"></v-md-editor>
                        </el-form-item>
                        <el-form-item label="请选择文章分类标签">
                            <el-select v-model="article.tagId" placeholder="选择标签" size="large">
                                <el-option v-for="tag in tags" :key="tag.id" :label="tag.name" :value="tag.id" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="是否保存为草稿">
                            <el-switch v-model="article.status" />
                        </el-form-item>
                    </el-form>
                    <el-form-item>
                        <el-button v-wave color="#F0C929" class="Botton" @click="submit">提交</el-button>
                    </el-form-item>
                </div>
            </el-col>
            <el-col :span="1"></el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'

import api from '../../api/index'

const router = useRouter()

const tags = ref([])
const article = reactive({
    title: '',
    summary: '',
    thumbnail: '',
    tagId: '',
    status: false,
    content: ''
})

onMounted(() => {
    api.getTags().then(res => {
        for (let item of res.data.data) {
            tags.value.push(item)
        }
    })
})

const submit = () => {
    article.status = article.status ? 0 : 1
    api.postArticle(article).then(() => {
        ElNotification({
            title: '成功',
            message: '文章发布成功啦',
            type: 'success',
        })
        router.push("/admin/ariticle/list")
    })
}

</script>

<style scoped>
.wrap {
    width: 100%;
    height: 100%;
    text-align: center;
}

.body {
    margin-top: 1rem;
    text-align: center;
}

.title {
    margin: 1rem auto;
    font-family: 'ZCOOL';
    font-size: 2rem;
}

.buttonWrap {
    text-align: center;
}

.Botton {
    margin: 15px auto;
    width: 200px;
    font-family: 'ZCOOL';
    height: 40px;
    font-size: 1rem;
    border-radius: 10px;
}

.articleEdit {
    color: #2c3e50;
    text-align: left;
}
</style>

<style>
.articleEdit .el-form-item__label {
    font-family: 'ZCOOL';
    font-size: 1.1rem;
    color: #E6E6E6;
}
</style>