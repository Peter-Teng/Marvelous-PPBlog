<template>
    <div class="wrap">
        <div class="body">
            <div class="title">欢迎来到Marvelous-PP同学博客后台管理系统</div>
            <div class="parallax">
                <div class="background" ref="scene">
                        <div data-depth="0.65" class="astronaut1"></div>
                        <div data-depth="-0.15" class="astronaut2"></div>
                        <div data-depth="0.2" class="meteor1"></div>
                        <div data-depth="0.5" class="meteor2"></div>
                        <div data-depth="-0.95" class="meteor3"></div>
                        <div data-depth="0.75" class="rocket"></div>
                        <div data-depth="0.8" class="UFO"></div>
                        <div data-depth="0.3" class="planet1"></div>
                        <div data-depth="0.15" class="planet2"></div>
                        <div data-depth="0.4" class="planet3"></div>
                        <div data-depth="0.3" class="planet4"></div>
                        <div data-depth="0.75" class="planet5"></div>
                        <div data-depth="-0.7" class="parallaxTitle">Marvelous-PPBlog</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import Parallax from 'parallax-js'
import { ref, onMounted } from 'vue'

const scene = ref('')

onMounted(() => {
    var parallaxInstance = new Parallax(scene.value);
})

</script>

<style scoped>
.wrap {
    text-align: center;
    height: 100%;
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

.parallax {
    width: 97.5%;
    height: 85%;
    position: absolute;
    top: 10%;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
}

.parallax div {
    position: absolute;
    background-size: contain;
    user-select: none;
}

.background {
    background-image: url("../../assets/images/parallex/background.jpg");
    width: 100%;
    height: 100%;
    border-radius: 30px;
}

.scene {
    width: 100%;
    height: 100%;
}

.astronaut1 {
    background-image: url("../../assets/images/parallex/astronaut1.png");
    width: 20%;
    height: 30%;
    background-repeat: no-repeat;
    top: 60% !important;
    left: 7% !important;
}

.astronaut2 {
    background-image: url("../../assets/images/parallex/astronaut2.png");
    width: 20%;
    height: 35%;
    background-repeat: no-repeat;
    top: 60% !important;
    left: 70% !important;
}

.meteor1 {
    background-image: url("../../assets/images/parallex/meteor1.png");
    width: 20%;
    height: 35%;
    background-repeat: no-repeat;
    top: 5% !important;
    left: 70% !important;
}

.meteor2 {
    background-image: url("../../assets/images/parallex/meteor2.png");
    width: 10%;
    height: 10%;
    background-repeat: no-repeat;
    top: 75% !important;
    left: 20% !important;
}

.meteor3 {
    background-image: url("../../assets/images/parallex/meteor3.png");
    width: 15%;
    height: 20%;
    background-repeat: no-repeat;
    top: 5% !important;
    left: 65% !important;
}

.rocket {
    background-image: url("../../assets/images/parallex/rocket.png");
    width: 100%;
    height: 40%;
    background-repeat: no-repeat;
    top: 3% !important;
    left: 5% !important;
}

.UFO {
    background-image: url("../../assets/images/parallex/UFO.png");
    width: 100%;
    height: 25%;
    background-repeat: no-repeat;
    top: 15% !important;
    left: 45% !important;
}

.planet1 {
    background-image: url("../../assets/images/parallex/planet1.png");
    width: 100%;
    height: 35%;
    background-repeat: no-repeat;
    top: 45% !important;
    left: 55% !important;
}

.planet2 {
    background-image: url("../../assets/images/parallex/planet2.png");
    width: 100%;
    height: 25%;
    background-repeat: no-repeat;
    top: 35% !important;
    left: 25% !important;
}

.planet3 {
    background-image: url("../../assets/images/parallex/planet3.png");
    width: 5%;
    height: 10%;
    background-repeat: no-repeat;
    top: 15% !important;
    left: 25% !important;
}

.planet4 {
    background-image: url("../../assets/images/parallex/planet4.png");
    width: 100%;
    height: 25%;
    background-repeat: no-repeat;
    top: 50% !important;
    left: 89% !important;
}

.planet5 {
    background-image: url("../../assets/images/parallex/planet5.png");
    width: 100%;
    height: 15%;
    background-repeat: no-repeat;
    top: 70% !important;
    left: 35% !important;
}

.parallaxTitle {
    font-size: 6rem;
    top: 40% !important;
    left: 15% !important;
    text-shadow: 2.5px 2.5px rebeccapurple;
}
</style>