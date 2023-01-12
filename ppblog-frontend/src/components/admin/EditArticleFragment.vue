<template>
    <div class="wrap">
        <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="22">
                <div class="body">
                    <div class="title">撰写文章</div>
                    <el-form :model="article" label-width="10rem" class="articleEdit">
                        <el-form-item label="文章标题">
                            <el-input v-model="article.title" />
                        </el-form-item>
                        <el-form-item label="文章标题图像URL">
                            <el-input v-model="article.thumbnail" style="width: 60rem;" />
                            <el-button color="#612897" @click="dialogVisible = true" :icon="Search"
                                style="margin-left: 2rem;" circle></el-button>
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
                        <el-form-item label="是否保存为草稿(不可见)">
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
        <el-dialog v-model="dialogVisible" title="查看标题图像" width="50%">
            <el-image :src="article.thumbnail" class="thumbnailShow" :fit="fit" />
            <template #footer>
                <span class="dialog-footer">
                    <el-button type="success" @click="dialogVisible = false">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, inject, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { Search, IconPicture } from '@element-plus/icons-vue'

import api from '../../api/index'

const articleCache = inject('articleCache')

const router = useRouter()
const fit = ref("contain")
const dialogVisible = ref(false)

const tags = ref([])
const article = reactive({
    id: null,
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
    if (articleCache.cached) {
        article.id = articleCache.data.id
        article.title = articleCache.data.title
        article.summary = articleCache.data.summary
        article.thumbnail = articleCache.data.thumbnail
        article.tagId = articleCache.data.tagId
        article.status = articleCache.data.status
        article.content = articleCache.data.content
    }
})

onUnmounted(() => {
    articleCache.cached = false
})

const submit = () => {
    article.status = article.status ? 0 : 1
    if (articleCache.cached) {
        api.modifyArticle(article).then(() => {
            ElNotification({
                title: '成功',
                message: '文章编辑成功!',
                type: 'success',
            })
            router.push("/admin/ariticle/list")
        })
    } else {
        api.postArticle(article).then(() => {
            ElNotification({
                title: '成功',
                message: '文章发布成功啦',
                type: 'success',
            })
            router.push("/admin/ariticle/list")
        })
    }
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
    margin: 2rem auto 1rem auto;
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

.thumbnailShow {
    height: 24rem;
    width: 32rem;
    border-radius: 25px;
    border: 1px solid #000000;
}
</style>

<style>
.articleEdit .el-form-item__label {
    font-family: 'ZCOOL';
    font-size: 1.1rem;
    color: #E6E6E6;
}
</style>