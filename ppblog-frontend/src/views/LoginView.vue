<template>
  <div id="loginBG" :style="{ 'background-image': 'url(' + background.login + ')' }">
    <bar></bar>
    <LoginForm v-wave class="Form" v-if="isLogin" @flip="flipForm"></LoginForm>
    <registerForm v-wave class="Form" v-else @flip="flipForm"></registerForm>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import LoginForm from "../components/general/LoginForm.vue"
import registerForm from "../components/general/RegisterForm.vue"
import bar from "../components/general/Navigation"
import background from '../utils/background'

const isLogin= ref(true)

function flipForm() {
  isLogin.value = !isLogin.value
}
</script>

<style scoped>
#loginBG {
  width: 100%;
  height: calc(100vh);
  background-size: cover;
  background-position: center;
}
.Form{ 
  width: 50%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>