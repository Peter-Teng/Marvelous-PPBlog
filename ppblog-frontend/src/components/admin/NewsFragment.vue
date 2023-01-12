<template>
    <div class="wrap">
        <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="22">
                <div class="body">
                    <div class="title">公告列表</div>
                    <div class="content">
                        <el-table :data="displayedNewsList" style="width: 100%" max-height="calc(70vh)" border>
                            <el-table-column label="展示" width="75">
                                <template #default="scope">
                                    <el-switch :model-value="true" v-if="scope.row.valid == 1"
                                        @change="flipValid(scope.row)" style="--el-switch-on-color: #13ce66;" />
                                    <el-switch :model-value="false" v-else @change="flipValid(scope.row)"
                                        style="--el-switch-off-color: #ff4949" />
                                </template>
                            </el-table-column>
                            <el-table-column label="置顶" width="75">
                                <template #default="scope">
                                    <el-switch :model-value="true" v-if="scope.row.topped == 1"
                                        @change="flipTop(scope.row)" style="--el-switch-on-color: #FAC213;" />
                                    <el-switch :model-value="false" v-else @change="flipTop(scope.row)"
                                        style="--el-switch-off-color: #404258" />
                                </template>
                            </el-table-column>
                            <el-table-column prop="id" label="公告ID" sortable width="250" />
                            <el-table-column prop="title" label="公告标题" width="250" />
                            <el-table-column prop="content" label="公告内容" width="500" />
                            <el-table-column fixed="right" width="225">
                                <template #header>
                                    <span>搜索:</span>
                                    <el-input v-model="searchText" placeholder="输入关键词" class="tableSearch"
                                        @input="filterNews" />
                                </template>
                                <template #default="scope">
                                    <div class="operationButtons">
                                        <el-button color="#579BB1" @click="editNews(scope.row)"
                                            class="operationButton">编辑</el-button>
                                        <el-popconfirm width="200" title="您确认要删除该公告吗?" confirm-button-text="确认"
                                            cancel-button-text="取消" :icon="InfoFilled" icon-color="#CC3636"
                                            @confirm="deleteNews(scope.row.id)">
                                            <template #reference>
                                                <el-button type="danger" class="operationButton">删除</el-button>
                                            </template>
                                        </el-popconfirm>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-button v-wave color="#540375" class="Botton" @click="newNews">新建公告</el-button>
                    </div>
                </div>
            </el-col>
            <el-col :span="1"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" :title="dialogTitle" width="55%">
            <el-form :model="selectedNews">
                <el-form-item label="公告ID">
                    <el-input v-model="selectedNews.id" disabled />
                </el-form-item>
                <el-form-item label="公告标题">
                    <el-input v-model="selectedNews.title" maxlength="50" show-word-limit />
                </el-form-item>
                <el-form-item label="公告内容">
                    <el-input v-model="selectedNews.content" :autosize="{ minRows: 3, maxRows: 5 }" type="textarea" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button type="danger" @click="dialogVisible = false">取消</el-button>
                    <el-button type="success" @click="commit">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, inject } from 'vue'
import api from '../../api/index'
import { InfoFilled } from '@element-plus/icons-vue'

const reloadAdmin = inject('reloadAdmin')

const allNewsList = ref([])
const displayedNewsList = ref([])

const searchText = ref('')
const dialogVisible = ref(false)
const dialogTitle = ref("")
const isEdit = ref(false)
const selectedNews = reactive({
    id: 0,
    title: "",
    content: ""
})

const filterNews = () => {
    if (searchText.value.trim() === '') {
        displayedNewsList.value = allNewsList.value
        return;
    }
    let result = []
    for (let item of allNewsList.value) {
        var titleIncluded = item.title.toLowerCase().includes(searchText.value.toLowerCase().trim())
        var contentIncluded = item.content.toLowerCase().includes(searchText.value.toLowerCase().trim())
        if (titleIncluded || contentIncluded) {
            result.push(item)
        }
    }
    displayedNewsList.value = result
}

const flipTop = (row) => {
    if (row.valid == 0) {
        ElNotification({ title: '失败', message: '啊哦,这条公告似乎是无效的哦！', type: 'error' })
        return
    }
    if (1 - row.topped == 1) {
        for (let item of allNewsList.value) {
            if (item.topped == 1) {
                ElNotification({ title: '失败', message: '同一时间只能有一条公告置顶哦!', type: 'error' })
                return
            }
        }
    }
    var news = {
        id: row.id,
        topped: 1 - row.topped
    }
    row.topped = 1 - row.topped
    api.modifyNews(news).then(() => { 
        ElNotification({ title: '成功', message: '公告置顶状态修改成功!', type: 'success' })
    }, () => {
        // rollback
        row.topped = 1 - row.topped
    })
}

const flipValid = (row) => {
    var news = {
        id: row.id,
        valid: 1 - row.valid,
        topped: row.topped
    }
    if (1 - row.valid == 0) {
        news.topped = 0
    }
    row.valid = 1 - row.valid
    api.modifyNews(news).then(() => {
        row.topped = news.topped       
        ElNotification({ title: '成功', message: '公告生效状态修改成功!', type: 'success' })
    }, () => {
        // rollback
        row.valid = 1 - row.valid
    })
}

const editNews = (data) => {
    dialogTitle.value = "编辑公告"
    isEdit.value = true
    dialogVisible.value = true
    selectedNews.id = data.id
    selectedNews.title = data.title
    selectedNews.content = data.content
}

const newNews = () => {
    dialogTitle.value = "新建公告"
    isEdit.value = false
    dialogVisible.value = true
    selectedNews.id = "自动生成"
    selectedNews.title = ""
    selectedNews.content = ""
}

const deleteNews = (id) => {
    api.deleteNews(id).then(() => {
        ElNotification({ title: '成功', message: '公告删除成功!', type: 'success' })
        reloadAdmin()
    })
}

const commit = () => {
    if (isEdit.value) {
        api.modifyNews(selectedNews).then(() => {
            ElNotification({
                title: '成功',
                message: '标签公告成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    } else {
        selectedNews.id = null
        api.postNews(selectedNews).then(() => {
            ElNotification({
                title: '成功',
                message: '新建公告成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    }
}

onMounted(() => {
    api.getAllNews().then(res => {
        allNewsList.value = res.data.data
        displayedNewsList.value = allNewsList.value
    })
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
    margin-top: 1rem;
    font-size: 2rem;
}

.content {
    margin-top: 1rem;
}

.operationButtons {
    text-align: center;
}

.tableSearch {
    width: 8.5rem;
    height: 1.5rem;
}


.operationButton {
    width: 5rem;
    font-family: 'ZCOOL';
    color: black;
    height: 2rem;
    font-size: 1rem;
}

.Botton {
    margin: 1.5rem auto 0.25rem auto;
    width: 15rem;
    font-family: 'ZCOOL';
    height: 40px;
    font-size: 1rem;
    border-radius: 10px;
}
</style>