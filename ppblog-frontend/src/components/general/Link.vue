<template>
    <div v-wave class="linkWrap" @click="toLink(link.url)">
        <el-image class="linkIcon" :src="link.icon" lazy><template #error>
                <div class="image-slot">
                    <el-icon :size="defaultIconSize" :color="defaultIconColor">
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
const defaultIconSize = ref(80)
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
    height: 300px;
    width: 250px;
    margin: 10px 10px;
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
    max-height: 80px;
    max-width: 80px;
    min-width: 80px;
    min-height: 80px;
    margin: 20px auto;
}

.linkName {
    color: black;
    overflow: hidden;
    text-overflow: ellipsis;
    word-wrap: break-word;
    margin: auto 10px;
    font-size: 1.5rem;
    max-height: 35px;
}

.description {
    color: black;
    font-size: 0.9rem;
    max-height: 40%;
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    margin: 15px 15px 0px 15px;
    word-wrap: break-word;
}
</style>