<template>
    <div class="wrap"><el-container>
            <el-header class="header">
                <div class="headerWrap">
                    <div class="headerTitle">
                        <span>
                            MarvelousPP同学
                        </span>
                        <img class="headerIcon" src="../assets/images/icon.png" />
                        <el-popconfirm confirm-button-text="确认" cancel-button-text="取消" width="175"
                            :icon="Promotion" icon-color="#E14D2A" title="主人确定要登出吗？" @confirm="logout(userStore)">
                            <template #reference>
                                <img class="logoutIcon" src="../assets/images/logout.svg" />
                            </template>
                        </el-popconfirm>
                    </div>
                </div>
            </el-header>
            <el-container>
                <el-aside class="menu">
                    <manu></manu>
                </el-aside>
                <el-main class="content"><router-view /></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import { Promotion } from '@element-plus/icons-vue';
import { ref, onMounted, inject } from 'vue'
import { useRouter } from 'vue-router'

import manu from '../components/admin/Manu.vue'

const router = useRouter()
const userStore = inject("userStore")

const logout = (userStore) => {
    userStore.methods.logout(userStore)
    router.push('/')
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
    background-color: #1F1D36;
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

.headerIcon {
    display: flexbox;
    vertical-align: middle;
    margin: 0 1rem;
    width: 2rem;
    height: 2rem;
}

.logoutIcon {
    float: right;
    vertical-align: middle;
    margin: 0 1rem;
    width: 1.5em;
    height: 1.5rem;
    cursor: pointer;
}

.menu {
    background-color: #202040;
    height: calc(92.5vh);
    width: calc(15vw);
}

.content {
    background-color: #251B37;
}
</style>