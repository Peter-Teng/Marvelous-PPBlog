<template>
  <div id="loginBG">
    <bar></bar>
    <LoginForm class="Form" v-if="isLogin" @flip="flipForm"></LoginForm>
    <registerForm class="Form" v-else @flip="flipForm"></registerForm>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import LoginForm from "../components/LoginForm.vue"
import registerForm from "../components/RegisterForm.vue"
import bar from "../components/Navigation"


const isLogin= ref(true)

function flipForm() {
  isLogin.value = !isLogin.value
}
</script>

<style scoped>
#loginBG {
  width: 100%;
  background-image: url("https://pp-blog-1300101944.cos.ap-guangzhou.myqcloud.com/assets%2Fimages%2FloginViewBG.jpg");
  height: calc(100vh);
  background-size: cover;
  background-position: center;
}
.Form{ 
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>