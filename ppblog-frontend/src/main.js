import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import elementIcon from "./plugins/icons";
import VWave from 'v-wave'


import './assets/fonts/index.css'
import './assets/css/animation.css'
import 'animate.css';


import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// highlightjs
import hljs from 'highlight.js';
VMdEditor.use(githubTheme, {
    Hljs: hljs,
  });
  

let app = createApp(App)
app.use(VWave).use(elementIcon).use(router).use(VMdEditor).mount('#app')


// 页面每次跳转回到顶部
router.afterEach((to, from, next) => {
    window.scrollTo(0, 0);
})