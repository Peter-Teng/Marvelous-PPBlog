<template>
    <div v-wave class="linkWrap" @click="toLink(link.url)">
        <el-image class="linkIcon" :src="link.icon" lazy><template #error>
                <div class="image-slot">
                    <el-icon :color="defaultIconColor" style="margin-top: 15px;">
                        <PictureRounded />
                    </el-icon>
                </div>
            </template></el-image>
        <div class="linkName" ref="titleRef">{{ link.name }}</div>
        <div class="description">
            {{ link.description }}
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { PictureRounded } from '@element-plus/icons-vue'

const props = defineProps({
    link: Object
});

const defaultIconColor = ref("#000000")
const titleRef = ref('')

const toLink = (dest) => {
    window.open(dest)
}

onMounted(() => {
    if (props.link.topped > 0) {
        titleRef.value.classList.add('topped')
    }
})
</script>

<style scoped>
.linkWrap {
    user-select: none;
    display: flexbox;
    background-color: #F3F3F3;
    box-shadow: 1.5px 1.5px 5px 1px #02237fef;
    min-height: 30%;
    height: 20rem;
    width: 22.5%;
    margin: 10px 1%;
    border-radius: 10px;
    cursor: pointer;
    text-align: center;
}

.topped {
    background-color: #FFCE45cc;
    border-radius: 10px;
}

.linkWrap:hover {
    box-shadow: 2px 2px 25px 5px #E3E3E3;
}

.linkWrap:hover .linkIcon {
    animation: rotate 10.0s linear infinite;
}


.linkIcon {
    font-size: 3.25rem;
    width: 5rem;
    height: 5rem;
    text-align: center;
    margin: 5% auto;
}

.linkName {
    color: black;
    overflow: hidden;
    text-overflow: ellipsis;
    word-wrap: break-word;
    margin: 0 10px;
    font-size: 1.5rem;
    height: 2rem;
}

.description {
    color: black;
    font-size: 0.9rem;
    height: 6rem;
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    margin: 15px 15px 0px 15px;
    word-wrap: break-word;
}
</style>