<template>
    <div class="wrap">
        <el-menu active-text-color="#ffd04b" background-color="#545c64" text-color="#fff" @select="manuSelected">
            <el-sub-menu index="1">
                <template #title>
                    <el-icon>
                        <location />
                    </el-icon>
                    <span>文章管理</span>
                </template>
                <el-menu-item index="1-1">item one</el-menu-item>
                <el-menu-item index="1-2">item two</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                    <el-icon>
                        <location />
                    </el-icon>
                    <span>标签管理</span>
                </template>
                <el-menu-item index="2-1">item one</el-menu-item>
                <el-menu-item index="2-2">item two</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>

<script setup>

const manuSelected = (key, keyPath) => {
    console.log(key, keyPath)
}

</script>

<style scoped>
.wrap {
    width: 100%;
    height: 100%;
}
</style>