<template>
    <div class="wrap">
        <el-scrollbar height="100%">
            <el-menu active-text-color="#FF4301" background-color="#202040" text-color="#EEEEEE" default-active="/admin"
                router>
                <el-menu-item index="/admin">
                    <el-icon>
                        <HomeFilled />
                    </el-icon>
                    <span>首页</span>
                </el-menu-item>
                <el-sub-menu index="">
                    <template #title>
                    <el-icon>
                        <files />
                    </el-icon>
                    <span>文章(Articles)管理</span>
                    </template>
                    <el-menu-item index="/admin/ariticle/list">
                        <el-icon>
                            <Files />
                        </el-icon>文章列表
                    </el-menu-item>
                    <el-menu-item index="/admin/article/edit">
                        <el-icon>
                            <EditPen />
                        </el-icon>撰写文章
                    </el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/admin/tag">
                    <el-icon>
                        <PriceTag />
                    </el-icon>
                    <span>标签(Tags)管理</span>
                </el-menu-item>
                <el-menu-item index="/admin/news">
                    <el-icon>
                        <bell />
                    </el-icon>
                    <span>新闻(News)管理</span>
                </el-menu-item>
                <el-menu-item index="/admin/link">
                    <el-icon>
                        <paperclip />
                    </el-icon>
                    <span>链接(Links)管理</span>
                </el-menu-item>
                <el-menu-item index="/admin/background">
                    <el-icon>
                        <Picture />
                    </el-icon>
                    <span>背景(Background)管理</span>
                </el-menu-item>
            </el-menu>
        </el-scrollbar>
    </div>
</template>

<script setup>

</script>

<style scoped>
.wrap {
    user-select: none;
    width: 100%;
    height: 100%;
}
</style>