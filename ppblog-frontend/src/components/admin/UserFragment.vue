<template>
    <div class="wrap">
        <el-row>
            <el-col :span="2"></el-col>
            <el-col :span="20">
                <div class="body">
                    <div class="title">博主信息编辑界面</div>
                    <div class="content">
                        <el-form :model="userInfo" class="userInfoForm">
                            <el-form-item label="用户ID">
                                <el-input v-model="userInfo.id" disabled />
                            </el-form-item>
                            <el-form-item label="用户名">
                                <el-input v-model="userInfo.username" maxlength="10" show-word-limit />
                            </el-form-item>
                            <el-form-item label="修改密码">
                                <el-button v-wave color="#D61C4E" class="Botton"
                                    @click="dialogVisible = true">点击修改密码</el-button>
                            </el-form-item>
                            <el-form-item label="头像">
                                <el-input v-model="userInfo.avatar" style="width: 90%;" />
                                <el-image class="avatar" :src="userInfo.avatar" lazy>
                                    <template #error>
                                        <div class="image-slot">
                                            <el-icon :size="30">
                                                <PictureRounded />
                                            </el-icon>
                                        </div>
                                    </template>
                                </el-image>
                            </el-form-item>
                            <el-form-item label="标语">
                                <el-input v-model="userInfo.taboo" maxlength="20" show-word-limit />
                            </el-form-item>
                            <el-form-item label="Email">
                                <el-input v-model="userInfo.email" />
                            </el-form-item>
                            <el-form-item label="Github">
                                <el-input v-model="userInfo.github" />
                            </el-form-item>
                            <el-form-item label="Bilibili">
                                <el-input v-model="userInfo.bilibili" />
                            </el-form-item>
                            <el-form-item label="Wechat">
                                <el-input v-model="userInfo.wechat" />
                            </el-form-item>
                            <el-form-item label="Leetcode">
                                <el-input v-model="userInfo.leetcode" />
                            </el-form-item>
                        </el-form>
                        <el-button v-wave color="#F0A500" class="Botton" @click="commit">保存修改</el-button>
                    </div>
                </div>
            </el-col>
            <el-col :span="2"></el-col>
        </el-row>
        <el-dialog v-model="dialogVisible" title="修改密码" width="35%">
            <el-form :model="userInfo">
                <el-form-item label="用户名">
                    <el-input v-model="userInfo.username" maxlength="10" show-word-limit disabled/>
                </el-form-item>
                <el-form-item label="输入新的密码">
                    <el-input type="password" v-model="userInfo.password" maxlength="16" show-word-limit />
                </el-form-item>
                <el-form-item label="确认新的密码">
                    <el-input type="password" v-model="confirmPassword" maxlength="16" show-word-limit />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button type="danger" @click="dialogVisible = false">取消</el-button>
                    <el-button type="success" @click="modifyPassword">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { onMounted, ref, reactive, inject } from 'vue'
import api from '../../api/index'
import { useRouter } from 'vue-router'

const reloadAdmin = inject('reloadAdmin')
const userStore = inject("userStore")
const router = useRouter()

const userInfo = reactive({
    id: "0",
    username: "",
    password: "",
    avatar: "",
    taboo: "",
    email: "",
    github: "",
    bilibili: "",
    wechat: "",
    leetcode: "",
})

const dialogVisible = ref(false)
const confirmPassword = ref("")

const modifyPassword = () => {
    dialogVisible.value = false
    if(userInfo.password.length < 4 || userInfo.password.length > 16) {
        ElMessage.warning("密码要在4到16个字符之间哦!")
        return;
    }
    if(userInfo.password != confirmPassword.value) {
        ElMessage.warning("两次输入的密码不一致哦")
        return;
    }
    var tmpUser = {
        id: userInfo.id,
        username: userInfo.username,
        password: userInfo.password
    }
    api.modifyInfo(tmpUser).then(()=>{
        ElNotification({ title: '成功', message: '用户密码修改成功,请重新登录!', type: 'success' })
        userStore.methods.logout(userStore)
        router.push('/')
    })
}

const commit = () => {
    if(userInfo.username.length < 3 || userInfo.username.length > 8) {
        ElMessage.warning("用户名要在3到8个字符之间哦")
        return;
    }
    userInfo.password = ""
    api.modifyInfo(userInfo).then(()=>{
        ElNotification({ title: '成功', message: '用户信息修改成功,请重新登录!', type: 'success' })
        userStore.methods.logout(userStore)
        router.push('/')
    })
}

onMounted(() => {
    api.getInfo().then(res => {
        for (let key in res.data.data) {
            userInfo[key] = res.data.data[key]
        }
    })
})

</script>

<style scoped>
.wrap {
    text-align: center;
}

.wrap div {
    font-family: 'ZCOOL';
    color: azure;
}

.body {
    width: 100%;
    height: 100%;
}

.title {
    margin-top: 2rem;
    font-size: 2rem;
}

.content {
    margin-top: 1rem;
}

.Botton {
    width: 15rem;
    font-family: 'ZCOOL';
    height: 2rem;
    font-size: 1rem;
    border-radius: 10px;
}

.avatar {
    width: 3rem;
    height: 3rem;
    border-radius: 50px;
    margin-left: 2.5rem;
}
</style>

<style>
.userInfoForm .el-form-item__label {
    color: azure;
    font-family: 'ZCOOL';
}
</style>