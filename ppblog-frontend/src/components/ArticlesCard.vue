<template>
    <div class="wrap">
        {{ article.title }}
    </div>
</template>

<script setup>
const props = defineProps({
    article: Object
});
</script>

<style scoped>
.wrap {
    width: 90%;
    margin: 30px auto;
    height: 300px;
    border-radius: 3px;
    box-shadow: 1px 1px 2px 2px #1b1b1ba3;
    background: rgba(255, 255, 255, 0.878);
}
</style>