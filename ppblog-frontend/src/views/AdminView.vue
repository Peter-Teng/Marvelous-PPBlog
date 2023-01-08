<template>
    <div class="wrap"><el-container>
            <el-header class="header">Header</el-header>
            <el-container>
                <el-aside class="menu">
                    <manu></manu>
                </el-aside>
                <el-main class="content">Content</el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import { ref, onMounted, inject } from 'vue'
import { useRouter } from 'vue-router'

import manu from '../components/admin/Manu.vue'

const router = useRouter()
const userStore = inject("userStore")

onMounted(() => {
    if (!userStore.methods.hasLogin(userStore)) {
        ElMessage.error("请先登录再访问管理员页面哦！")
        router.push('/login')
    }
})
</script>

<style scoped>
.wrap {
    background-color: #251B37;
    color: azure;
}

.header {
    height: calc(7.5vh);
}

.menu {
    height: calc(92.5vh);
    width: calc(20vw);
}
</style>