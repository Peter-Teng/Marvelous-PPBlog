import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import elementIcon from "./plugins/icons";
import VWave from 'v-wave'

import './assets/fonts/index.css'
import './assets/css/animation.css'
import 'animate.css';


createApp(App).use(VWave).use(elementIcon).use(store).use(router).mount('#app')
// 页面每次跳转回到顶部
router.afterEach((to, from, next) => {
    window.scrollTo(0, 0);
})