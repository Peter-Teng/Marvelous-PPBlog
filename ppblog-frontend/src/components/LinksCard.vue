<template>
    <div class="cardWrap">
        <div class="cardTitle">标题</div>
        <div class="links">
            <Link>
            </Link>
            <Link>
            </Link>
            <Link>
            </Link>
            <Link>
            </Link>
            <Link>
            </Link>
        </div>
    </div>
</template>

<script setup>
import Link from "../components/Link.vue"
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
    font-size: 1.85em;
    padding-left: 50px;
    padding-top: 25px;
}

.links {
    width: 95%;
    text-align: center;
    margin: 20px auto;
}
</style>