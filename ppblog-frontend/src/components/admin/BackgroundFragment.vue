<template>
    <div class="wrap">
        <el-row>
            <el-col :span="2"></el-col>
            <el-col :span="20">
                <div class="title">页面背景管理</div>
                <div class="content">
                    <el-row>
                        <el-col :span="2"></el-col>
                        <el-col :span="8">
                            <div>
                                <div @click="editBackground('主页背景', background.index)">
                                    <el-image :src="background.index.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">主页图像</div>
                                <div @click="editBackground('文章列表页背景', background.articlesList)">
                                    <el-image :src="background.articlesList.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">文章列表页图像</div>
                                <div @click="editBackground('导航链接页背景', background.navigation)">
                                    <el-image :src="background.navigation.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">导航链接页图像</div>
                                <div @click="editBackground('404页背景', background.notFound)">
                                    <el-image :src="background.notFound.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">404页图像</div>
                            </div>
                        </el-col>
                        <el-col :span="4"></el-col>
                        <el-col :span="8">
                            <div>
                                <div @click="editBackground('登录页背景', background.login)">
                                    <el-image :src="background.login.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">登录页图像</div>
                                <div @click="editBackground('本站统计页背景', background.statistic)">
                                    <el-image :src="background.statistic.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">本站统计页图像</div>
                                <div @click="editBackground('错误页背景', background.sorry)">
                                    <el-image :src="background.sorry.url" class="backgroundCanvas" :fit="fit">
                                        <template #error>
                                            <div class="image-slot">
                                                <el-icon :size="150"><icon-picture /></el-icon>
                                            </div>
                                        </template>
                                    </el-image>
                                </div>
                                <div class="introduction">错误页图像</div>
                            </div>
                        </el-col>
                        <el-col :span="2"></el-col>
                    </el-row>
                </div>
            </el-col>
            <el-col :span="2"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" :title="dialogTitle" width="45%">
            <el-form :model="selectedBackground">
                <el-form-item label="背景ID">
                    <el-input v-model="selectedBackground.id" disabled />
                </el-form-item>
                <el-form-item label="背景URL">
                    <el-input v-model="selectedBackground.url" type="textarea" />
                </el-form-item>
                <el-image :src="selectedBackground.url" class="previewBackground" :fit="fit">
                    <template #error>
                        <div class="image-slot">
                            <el-icon :size="150"><icon-picture /></el-icon>
                        </div>
                    </template>
                </el-image>
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
import background from '../../storage/background'
import { Picture as IconPicture } from '@element-plus/icons-vue'
import { ref, reactive, inject } from 'vue'
import api from '../../api/index'

const reloadAdmin = inject('reloadAdmin')

const fit = "contain"
const dialogVisible = ref(false)
const dialogTitle = ref("")
const selectedBackground = reactive({
    id: 0,
    url: ""
})

const editBackground = (name, data) => {
    dialogTitle.value = "编辑" + name
    dialogVisible.value = true
    selectedBackground.id = data.id
    selectedBackground.url = data.url
}

const commit = () => {
    api.modifyBackground(selectedBackground).then(() => {
        ElNotification({
            title: '成功',
            message: '修改背景图像成功!',
            type: 'success',
        })
        for(let key in background) {
            var item = background[key]
            if(item.id == selectedBackground.id) {
                item.url = selectedBackground.url
                break
            }
        }
        reloadAdmin()
    })
}

</script>


<style scoped>
.wrap {
    text-align: center;
}

.wrap div {
    font-family: 'ZCOOL';
}

.title {
    margin-top: 2.5rem;
    font-size: 2rem;
}

.content {
    margin-top: 2rem;
}

.backgroundCanvas {
    width: 100%;
    height: 15rem;
    border-radius: 30px;
    border: 2px dotted azure;
    cursor: pointer;
}

.backgroundCanvas:hover {
    border: 2px dotted transparent;
    box-shadow: 1px 1px 10px 5px azure;
}

.previewBackground {
    width: 20rem;
    height: 15rem;
    margin: 1rem auto;
    border-radius: 30px;
    border: 1px dashed black;
}

.image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

.introduction {
    font-size: 1.5rem;
    margin: 1rem auto 2rem auto;
}
</style>