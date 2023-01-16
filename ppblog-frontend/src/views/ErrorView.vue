<template>
    <div class="wrap" :style="{ 'background-image': 'url(' + background.notFound.url + ')' }">
        <div class="content">
            <img src="../assets/images/404.png" class="image" />
            <div class="ErrorText">
                请求的资源不存在哦!
            </div>
            <div class="ErrorText">
                The request resource does not exist!
            </div>
        </div>
    </div>
</template>

<script setup>
import background from '../storage/background'
</script>

<style scoped>
.wrap {
    width: 100%;
    height: calc(100vh);
    background-repeat: no-repeat;
    background-size: cover;
    display: table;
    background-position: center;
    text-align: center;
}

.image {
    width: 5rem;
    height: 5rem;
    vertical-align: middle;
}

.content {
    width: 90%;
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    color: aliceblue;
}

.ErrorText {
    margin-top: 1rem;
    font-size: 3rem;
    font-family: 'ZCOOL';
}
</style>