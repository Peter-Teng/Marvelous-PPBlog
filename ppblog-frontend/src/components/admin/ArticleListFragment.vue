<template>
    <div class="wrap">
        <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="22">
                <div class="body">
                    <div class="title">文章列表</div>
                    <div class="content">
                        <el-table :data="articleList" style="width: 100%" max-height="calc(70vh)" border>
                            <el-table-column label="可见" width="75">
                                <template #default="scope">
                                    <el-switch :model-value="true" v-if="scope.row.status == 1"
                                        @change="flipDraft(scope.row, false)" style="--el-switch-on-color: #38E54D;" />
                                    <el-switch :model-value="false" v-else @change="flipDraft(scope.row)"
                                        style="--el-switch-off-color: #D61C4E" />
                                </template>
                            </el-table-column>
                            <el-table-column label="查看详情" width="100">
                                <template #default="scope">
                                    <div class="operationButtons">
                                        <el-button color="#FAC213" @click="toDetail(scope.row)"
                                            class="operationButton">详情</el-button>
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column prop="id" label="文章ID" sortable width="200" />
                            <el-table-column prop="thumbnail" label="相关配图" width="100">
                                <template #default="scope">
                                    <div style="text-align: center;">
                                        <el-button color="#612897" @click="showThumbnail(scope.row)" :icon="Search"
                                            circle></el-button>
                                    </div>
                                </template>
                            </el-table-column>
                            <el-table-column prop="title" label="文章标题" sortable width="200" />
                            <el-table-column prop="summary" label="文章摘要" width="500" />
                            <el-table-column prop="tagName" label="文章标签" width="150" />
                            <el-table-column prop="heat" label="文章热度" width="100" />
                            <el-table-column prop="createDate" label="发布时间" sortable width="200" />
                            <el-table-column prop="modifyDate" label="最后编辑时间" sortable width="200" />
                            <el-table-column label="操作" fixed="right" width="225">
                                <template #default="scope">
                                    <div class="operationButtons">
                                        <el-button color="#579BB1" @click="editArticle(scope.row)"
                                            class="operationButton">编辑</el-button>
                                        <el-popconfirm width="400" title="您确认要删除该文章吗?(若文章处于可见状态,首次删除先将使其转为不可见)"
                                            confirm-button-text="确认" cancel-button-text="取消" :icon="InfoFilled"
                                            icon-color="#CC3636" @confirm="deleteArticle(scope.row)">
                                            <template #reference>
                                                <el-button type="danger" class="operationButton">删除</el-button>
                                            </template>
                                        </el-popconfirm>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="paginationWrap" style="text-align: center; color: blue;">
                            <el-pagination class="pagination" :page-size="pageSize" :pager-count="7"
                                layout="prev, pager, next, jumper" :total="total" @current-change="handleChange"
                                background />
                        </div>
                    </div>
                </div>
            </el-col>
            <el-col :span="1"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" title="查看标题图像" width="30%">
            <el-image :src="thumbnailUrl" class="thumbnailShow" :fit="fit">
                <template #error>
                    <div class="image-slot">
                        <el-icon :size="150"><icon-picture /></el-icon>
                    </div>
                </template>
            </el-image>
            <div class="introduction">{{ theTitle }}</div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button type="success" @click="dialogVisible = false">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { onMounted, ref, inject } from 'vue'
import api from '../../api/index'
import { InfoFilled, Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const articleCache = inject('articleCache')
const reloadAdmin = inject('reloadAdmin')
const router = useRouter()

const articleList = ref([])

const dialogVisible = ref(false)
const thumbnailUrl = ref("")
const fit = ref("contain")
const theTitle = ref("")

const total = ref(0)
const pageSize = ref(10)

const handleChange = (value) => {
    articleList.value = []
    loadArticles(value, pageSize.value)
}

const loadArticles = (requestPageNum, requestPageSize) => {
    api.getAllArticles(requestPageNum, requestPageSize).then(res => {
        articleList.value = res.data.data.rows
        total.value = Number(res.data.data.total)
    })
}

const flipDraft = (row, isDelete) => {
    var article = {
        id: row.id,
        status: 1 - row.status
    }
    row.status = 1 - row.status
    api.modifyArticle(article).then(() => {
        if (isDelete) {
            ElNotification({ title: '转为不可见', message: '文章状态转为不可见!', type: 'warning' })
        } else {
            ElNotification({ title: '成功', message: '文章状态修改成功!', type: 'success' })
        }
    }, () => {
        row.status = 1 - row.status
    })
}

const showThumbnail = (row) => {
    thumbnailUrl.value = row.thumbnail
    theTitle.value = row.title
    dialogVisible.value = true
}

const toDetail = (row) => {
    router.push(`/article/${row.id}`)
}

const editArticle = (row) => {
    articleCache.cached = true

    api.getArticleById(row.id).then(res => {
        articleCache.data.id = res.data.data.id
        articleCache.data.title = res.data.data.title
        articleCache.data.summary = res.data.data.summary
        articleCache.data.thumbnail = res.data.data.thumbnail
        articleCache.data.tagId = res.data.data.tagId
        articleCache.data.status = res.data.data.status
        articleCache.data.content = res.data.data.content
        router.push('/admin/article/edit')
    })
}

const deleteArticle = (row) => {
    if (row.status == 1) {
        flipDraft(row, true)
    } else {
        api.deleteArticle(row.id).then(() => {
            ElNotification({ title: '成功', message: '文章删除成功!', type: 'success' })
            reloadAdmin()
        })
    }
}

onMounted(() => {
    loadArticles(1, pageSize.value)
})

</script>

<style scoped>
@import url("../../assets/css/my-el-table.css");

.wrap {
    text-align: center;
}

.wrap div {
    font-family: 'ZCOOL';
}

.body {
    width: 100%;
    height: 100%;
}

.title {
    margin-top: 1.25rem;
    font-size: 2rem;
}

.content {
    margin-top: 1rem;
}

.operationButtons {
    text-align: center;
}

.operationButton {
    width: 5rem;
    font-family: 'ZCOOL';
    color: black;
    height: 2rem;
    font-size: 1rem;
}

.pagination {
    margin-top: 1rem;
    display: inline-flex;
}

.thumbnailShow {
    height: 15rem;
    width: 20rem;
    border-radius: 25px;
    border: 1px solid #000000;
}

.introduction {
    font-size: 1.75rem;
    margin-top: 1rem;
}
</style>