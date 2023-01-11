<template>
    <div class="wrap">
        <el-row>
            <el-col :span="4"></el-col>
            <el-col :span="16">
                <div class="body">
                    <div class="title">标签列表</div>
                    <div class="content">
                        <el-table :data="tags" style="width: 100%" max-height="calc(70vh)" border>
                            <el-table-column prop="id" label="标签ID" sortable width="250" />
                            <el-table-column prop="name" label="标签名字" sortable width="250" />
                            <el-table-column label="操作">
                                <template #default="scope">
                                    <div class="operationButtons">
                                        <el-button color="#579BB1" @click="editTag(scope.row)"
                                            class="operationButton">编辑</el-button>
                                        <el-popconfirm width="200" title="您确认要删除该标签吗?" confirm-button-text="确认"
                                            cancel-button-text="取消" :icon="InfoFilled" icon-color="#CC3636"
                                            @confirm="deleteTag(scope.row.id)">
                                            <template #reference>
                                                <el-button type="danger" class="operationButton">删除</el-button>
                                            </template>
                                        </el-popconfirm>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-button v-wave color="#F0C929" class="Botton" @click="newTag">新建标签</el-button>
                    </div>
                </div>
            </el-col>
            <el-col :span="4"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
            <el-form :model="selectedTag">
                <el-form-item label="标签ID">
                    <el-input v-model="selectedTag.id" disabled />
                </el-form-item>
                <el-form-item label="标签名称">
                    <el-input v-model="selectedTag.name" maxlength="30" show-word-limit />
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

const tags = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref("")
const isEdit = ref(false)
const selectedTag = reactive({
    id: 0,
    name: ""
})

const editTag = (data) => {
    dialogTitle.value = "编辑标签"
    isEdit.value = true
    dialogVisible.value = true
    selectedTag.id = data.id
    selectedTag.name = data.name
}

const newTag = () => {
    dialogTitle.value = "新建标签"
    isEdit.value = false
    dialogVisible.value = true
    selectedTag.id = "自动生成"
    selectedTag.name = ""
}

const deleteTag = (id) => {
    api.deleteTag(id).then(() => {
        ElNotification({
            title: '成功',
            message: '标签删除成功!',
            type: 'success',
        })
        reloadAdmin()
    })
}

const commit = () => {
    if (isEdit.value) {
        api.modifyTag(selectedTag).then(() => {
            ElNotification({
                title: '成功',
                message: '标签编辑成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    } else {
        selectedTag.id = null
        api.postTag(selectedTag).then(() => {
            ElNotification({
                title: '成功',
                message: '新建标签成功!',
                type: 'success',
            })
            reloadAdmin()
        })
    }
}

onMounted(() => {
    api.getTags().then(res => {
        tags.value = res.data.data
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

.Botton {
    margin: 1.5rem auto 0.25rem auto;
    width: 15rem;
    font-family: 'ZCOOL';
    height: 40px;
    font-size: 1rem;
    border-radius: 10px;
}
</style>