<template>
    <div class="wrap">
        <el-row>
            <el-col :span="2"></el-col>
            <el-col :span="20">
                <div class="body">
                    <div class="title">用户信息编辑界面</div>
                    <div class="content">
                    </div>
                </div>
            </el-col>
            <el-col :span="2"></el-col>
        </el-row>
    </div>
</template>

<script setup>

</script>

<style scoped>
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
    margin-top: 2.5rem;
    font-size: 2rem;
}

.content {
    margin-top: 1rem;
}
</style>