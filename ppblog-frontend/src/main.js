import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import elementIcon from "./plugins/icons";
import VWave from 'v-wave'

import './assets/fonts/index.css'
import 'animate.css';


createApp(App).use(VWave).use(elementIcon).use(store).use(router).mount('#app')
