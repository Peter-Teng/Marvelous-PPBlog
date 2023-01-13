<template>
    <div class="wrap">
        <el-container>
            <el-header class="header">
                <div class="headerWrap">
                    <div class="headerTitle">
                        <img class="homeIcon" src="../assets/images/home.png" @click="toHome" />
                        <span class="headerSpan">
                            MarvelousPP同学
                        </span>
                        <img class="headerIcon" src="../assets/images/icon.png" />
                        <el-popconfirm confirm-button-text="确认" cancel-button-text="取消" width="175" :icon="Promotion"
                            icon-color="#E14D2A" title="主人确定要登出吗？" @confirm="logout(userStore)">
                            <template #reference>
                                <img class="logoutIcon" src="../assets/images/logout.png" />
                            </template>
                        </el-popconfirm>
                    </div>
                </div>
            </el-header>
            <el-container class="body">
                <el-aside class="menu">
                    <manu></manu>
                </el-aside>
                <el-scrollbar ref="scrollbarRef" height="calc(92.5vh)">
                    <el-main class="content">
                        <router-view v-if="isAdminActive" />
                    </el-main>
                </el-scrollbar>
            </el-container>
            <div class="imageBed" @click="imageBedDialogVisible = true"></div>
            <el-dialog v-model="imageBedDialogVisible" title="上传图像" width="35%" name="image">
                <el-dialog v-model="historyDialogVisible" title="上传图像" width="50%" name="image">
                    <div class="urlList">
                        之前上传的图像及链接为:
                        <div v-for="(item, index) in urlList" :key="index">[{{ item.name }}]: {{ item.url }} </div>
                    </div>
                    <template #footer>
                        <div class="dialogFooter">
                            <el-button @click="historyDialogVisible = false" type="danger" size="large">返回</el-button>
                        </div>
                    </template>
                </el-dialog>
                <el-upload drag action="" :auto-upload="false" :on-change="uploadChanged" :show-file-list="false">
                    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                    <div class="tip">
                        拖拽图像到这里或者<em>点击上传</em>
                    </div>
                </el-upload>
                <div class="squooshPrompt">
                    <el-checkbox v-model="squeeze" label="使用TinyPNG进行图像压缩?" size="large" />
                </div>
                <div class="fileName">
                    当前选择的文件是: 【<em style="color:orangered;">{{ imageFile.name }}</em>】
                </div>
                <template #footer>
                    <div class="dialogFooter">
                        <el-button @click="imageBedDialogVisible = false" type="danger" size="large">取消</el-button>
                        <el-button @click="historyDialogVisible = true" type="primary" size="large">历史记录</el-button>
                        <el-button type="success" size="large" @click="uploadImage" :loading="uploading">上传</el-button>
                    </div>
                </template>
            </el-dialog>
        </el-container>
    </div>
</template>

<script setup>
import { Promotion } from '@element-plus/icons-vue';
import { ref, onMounted, inject, provide, nextTick } from 'vue'
import { useRouter } from 'vue-router'

import manu from '../components/admin/Menu.vue'

import api from '../api/index'
import articleCache from '../storage/articleCache';

const router = useRouter()
const userStore = inject("userStore")

const imageBedDialogVisible = ref(false)
const historyDialogVisible = ref(false)
const squeeze = ref(true)
const uploading = ref(false)
const imageFile = ref({})
const urlList = ref([])

// 刷新admin router view
const isAdminActive = ref(true)
provide('reloadAdmin', () => {
    isAdminActive.value = false
    nextTick(() => {
        isAdminActive.value = true
    })
})
provide('articleCache', articleCache)

const logout = (userStore) => {
    userStore.methods.logout(userStore)
    router.push('/')
}

const toHome = () => {
    router.push('/')
}

const uploadChanged = (file) => {
    const isIMAGE = file.raw.type.lastIndexOf("image") == 0
    const withinLimit = file.size / 1024 / 1024 < 5;
    if (!isIMAGE) {
        ElMessage.error('上传文件只能是图片格式!')
        return false;
    }
    if (!withinLimit) {
        ElMessage.error('上传文件大小不能超过5MB!')
        return false;
    }
    imageFile.value = file
}

const uploadImage = () => {
    uploading.value = true
    api.uploadImage(imageFile.value, squeeze.value).then(res => {
        ElNotification({
            title: `上传成功[${imageFile.value.raw.name}]`,
            message: `对应的图像链接为:\n${res.data.data}`,
            type: 'success',
            duration: 0
        })
        urlList.value.push({
            name: imageFile.value.raw.name,
            url: res.data.data
        })
        uploading.value = false
    })
}

onMounted(() => {
    if (!userStore.methods.hasLogin(userStore)) {
        ElMessage.error("请先登录再访问管理员页面哦！")
        router.push('/login')
    }
})
</script>

<style scoped>
.wrap {
    color: azure;
}

.header {
    background-color: #1a1d1f;
    height: calc(7.5vh);
}

.headerWrap {
    display: table;
    text-align: center;
    width: 100%;
    height: 100%;
    user-select: none;
    border-radius: 20px;
}

.headerTitle {
    display: table-cell;
    vertical-align: middle;
    font-size: 1.5rem;
    font-family: 'ZCOOL';
}

.headerSpan {
    transition: all .3s ease-in;
    animation-duration: 1.5s;
}

.headerSpan:hover {
    color: goldenrod;
}

.headerIcon {
    display: flexbox;
    vertical-align: middle;
    margin: 0 1rem;
    width: 2rem;
    height: 2rem;
}

.homeIcon {
    float: left;
    vertical-align: middle;
    margin: 0 1rem;
    width: 2rem;
    height: 2rem;
    cursor: pointer;
}

.logoutIcon {
    float: right;
    vertical-align: middle;
    margin: 0 1rem;
    width: 2rem;
    height: 2rem;
    cursor: pointer;
}

.menu {
    background-color: #25282e;
    min-height: calc(92.5vh);
    width: calc(15vw);
}

.content {
    padding: 0;
    background-color: #0a0a0a;
    min-height: calc(92.5vh);
    width: calc(85vw);
}

.imageBed {
    position: fixed;
    margin-left: calc(95vw);
    margin-top: calc(90vh);
    background-image: url("../assets/images/imageBed.svg");
    background-size: 3.5rem;
    width: 3.5rem;
    height: 3.5rem;
    border-radius: 25px;
    cursor: pointer;
}

.squooshPrompt {
    text-align: center;
    margin-top: 0.5rem;
    font-family: 'ZCOOL';
}

.fileName {
    text-align: center;
    font-family: 'ZCOOL';
}

.urlList {
    font-family: 'ZCOOL';
    margin-bottom: 20px;
}

.tip {
    font-family: 'ZCOOL';
}

.tip em {
    color: coral;
}

.dialogFooter {
    text-align: center;
}
</style>