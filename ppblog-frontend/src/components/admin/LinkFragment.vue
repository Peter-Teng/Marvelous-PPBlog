<template>
    <div class="wrap">
        <el-row>
            <el-col :span="1"></el-col>
            <el-col :span="22">
                <div class="body">
                    <div class="title">导航链接列表</div>
                    <div class="content">
                        <el-table :data="displayedLinks" style="width: 100%" max-height="calc(70vh)" border>
                            <el-table-column label="置顶" width="75">
                                <template #default="scope">
                                    <el-switch :model-value="true" v-if="scope.row.topped == 1"
                                        @change="flipTop(scope.row)" style="--el-switch-on-color: #FAC213;" />
                                    <el-switch :model-value="false" v-else @change="flipTop(scope.row)"
                                        style="--el-switch-off-color: #404258" />
                                </template>
                            </el-table-column>
                            <el-table-column prop="id" label="导航链接ID" sortable width="200" />
                            <el-table-column prop="icon" label="ICON" width="75">
                                <template #default="scope">
                                    <el-image class="icon" :src="scope.row.icon" lazy>
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :color="iconColor" :size="35">
                                                    <PictureRounded />
                                                </el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </template>
                            </el-table-column>
                            <el-table-column prop="name" label="导航链接名字" sortable width="200" />
                            <el-table-column prop="category" label="导航链接归类" sortable width="150" />
                            <el-table-column prop="description" label="导航链接描述" width="500" />
                            <el-table-column prop="url" label="导航链接URL" width="350" />
                            <el-table-column fixed="right" width="225">
                                <template #header>
                                    <span>搜索:</span>
                                    <el-input v-model="searchText" placeholder="输入关键词" class="tableSearch"
                                        @input="filterLinks" />
                                </template>
                                <template #default="scope">
                                    <div class="operationButtons">
                                        <el-button color="#579BB1" @click="editLink(scope.row)"
                                            class="operationButton">编辑</el-button>
                                        <el-popconfirm width="200" title="您确认要删除该导航链接吗?" confirm-button-text="确认"
                                            cancel-button-text="取消" :icon="InfoFilled" icon-color="#CC3636"
                                            @confirm="deleteLink(scope.row.id)">
                                            <template #reference>
                                                <el-button type="danger" class="operationButton">删除</el-button>
                                            </template>
                                        </el-popconfirm>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-button v-wave color="#29C7AC" class="Botton" @click="newLink">新建导航链接</el-button>
                    </div>
                </div>
            </el-col>
            <el-col :span="1"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" :title="dialogTitle" width="50%">
            <el-form :model="selectedLink">
                <el-form-item label="导航链接ID">
                    <el-input v-model="selectedLink.id" disabled />
                </el-form-item>
                <el-form-item label="导航链接名称">
                    <el-input v-model="selectedLink.name" maxlength="20" show-word-limit />
                </el-form-item>
                <el-form-item label="导航链接类别名称">
                    <el-input v-model="selectedLink.category" maxlength="15" show-word-limit />
                </el-form-item>
                <el-form-item label="导航链接ICON">
                    <el-input v-model="selectedLink.icon" style="width: 85%;" />
                    <el-image class="formIcon" :src="selectedLink.icon" lazy>
                        <template #error>
                            <div class="image-slot">
                                <el-icon :size="30">
                                    <PictureRounded />
                                </el-icon>
                            </div>
                        </template>
                    </el-image>
                </el-form-item>
                <el-form-item label="导航链接描述">
                    <el-input v-model="selectedLink.description" />
                </el-form-item>
                <el-form-item label="导航链接URL">
                    <el-input v-model="selectedLink.url" />
                </el-form-item>
                <el-form-item label="导航是否置顶">
                    <el-switch v-model="selectedLink.topped"
                        style="--el-switch-on-color: #FAC213; --el-switch-off-color: #404258" />
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
import { onMounted, ref, reactive, inject } from 'vue'
import api from '../../api/index'
import { InfoFilled } from '@element-plus/icons-vue'

const allLinks = ref([])
const displayedLinks = ref([])
const searchText = ref('')
const iconColor = ref("#ffffff")

const dialogVisible = ref(false)
const dialogTitle = ref("")
const isEdit = ref(false)
const selectedLink = reactive({
    id: 0,
    name: "",
    category: "",
    icon: "",
    description: "",
    url: "",
    topped: false
})

const reloadAdmin = inject('reloadAdmin')

const filterLinks = () => {
    if (searchText.value.trim() === '') {
        displayedLinks.value = allLinks.value
        return;
    }
    let result = []
    for (let item of allLinks.value) {
        var nameIncluded = item.name.toLowerCase().includes(searchText.value.toLowerCase().trim())
        var categoryIncluded = item.category.toLowerCase().includes(searchText.value.toLowerCase().trim())
        var descriptionIncluded = item.description.toLowerCase().includes(searchText.value.trim())
        if (nameIncluded || categoryIncluded || descriptionIncluded) {
            result.push(item)
        }
    }
    displayedLinks.value = result
}

const flipTop = (row) => {
    var link = {
        id: row.id,
        topped: 1 - row.topped
    }
    row.topped = 1 - row.topped
    api.modifyLink(link).then(() => {
        ElNotification({ title: '成功', message: '导航链接状态修改成功!', type: 'success' })
    }, () => {
        // rollback
        row.topped = 1 - row.topped
    })
}

const newLink = () => {
    dialogTitle.value = "新建导航链接"
    isEdit.value = false
    dialogVisible.value = true
    selectedLink.id = "自动生成"
    selectedLink.name = ""
    selectedLink.category = ""
    selectedLink.icon = ""
    selectedLink.description = ""
    selectedLink.url = ""
    selectedLink.topped = false
}

const editLink = (data) => {
    dialogTitle.value = "编辑导航链接"
    isEdit.value = true
    dialogVisible.value = true
    selectedLink.id = data.id
    selectedLink.name = data.name
    selectedLink.category = data.category
    selectedLink.icon = data.icon
    selectedLink.description = data.description
    selectedLink.url = data.url
    selectedLink.topped = data.topped == 1
}

const commit = () => {
    selectedLink.topped = selectedLink.topped ? 1 : 0
    if (isEdit.value) {
        api.modifyLink(selectedLink).then(() => {
            ElNotification({
                title: '成功',
                message: '导航链接编辑成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    } else {
        selectedLink.id = null
        api.postLink(selectedLink).then(() => {
            ElNotification({
                title: '成功',
                message: '新建导航链接成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    }
    selectedLink.topped = false
}

const deleteLink = (id) => {
    api.deleteLink(id).then(() => {
        ElNotification({ title: '成功', message: '导航链接删除成功!', type: 'success' })
        reloadAdmin()
    })
}

onMounted(() => {
    api.getLinks().then(res => {
        allLinks.value = res.data.data
        displayedLinks.value = allLinks.value
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

.operationButton {
    width: 5rem;
    font-family: 'ZCOOL';
    color: black;
    height: 2rem;
    font-size: 1rem;
}

.tableSearch {
    width: 8.5rem;
    height: 1.5rem;
}

.icon {
    width: 2.5rem;
    height: 2.5rem;
    vertical-align: middle;
    text-align: center;
}

.formIcon {
    width: 2rem;
    height: 2rem;
    margin-left: 2rem;
    vertical-align: middle;
    text-align: center;
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