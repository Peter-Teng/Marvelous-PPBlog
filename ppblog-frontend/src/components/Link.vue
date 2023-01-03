<template>
    <div class="linkWrap">
        <el-row class="cover">
            <el-col :span="10"><img class="linkIcon" src="../assets/images/github.svg" /></el-col>
            <el-col :span="14">
                <div class="linkName">name</div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>

</script>

<style scoped>
.linkWrap {
    display: inline-block;
    background-color: #E3E3E3;
    box-shadow: 1.5px 1.5px 5px 1px #02237fef;
    height: 100px;
    width: 250px;
    margin: 10px 10px;
    border-radius: 10px;
    cursor: pointer;
}

.linkIcon {
    max-height: 80px;
    max-width: 80px;
    margin: 10px;
}

.linkName {
    color: black;
    text-align: center;
    min-height: 100px;
    line-height: 100px;
    font-size: 1.35em;
}
</style>