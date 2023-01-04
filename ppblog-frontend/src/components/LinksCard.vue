<template>
    <div class="cardWrap">
        <el-divider border-style="dashed" />
        <div class="cardTitle">{{ title }}</div>
        <div class="links">
            <Link v-for="link in links" :key="link.id" :link="link">
            </Link>
        </div>
        <el-divider border-style="dashed" />
    </div>
</template>

<script setup>
import Link from "../components/Link.vue"

const props = defineProps({
    title: String,
    links: Array
});
</script>

<style scoped>
.cardWrap {
    width: 100%;
    color: aliceblue;
    background-color: #0F0F0F;
    border-radius: 25px;
    min-height: 200px;
    width: 100%;
    text-align: left;
}

.cardTitle {
    font-family: 'ZCOOL';
    font-size: 2.25em;
    padding-left: 50px;
    padding-top: 25px;
}

.links {
    width: 95%;
    display: flex;
    flex-wrap: wrap;
    text-align: center;
    margin: 20px auto;
}
</style>