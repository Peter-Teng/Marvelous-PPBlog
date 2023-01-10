<template>
  <router-view class="wrap" />
</template>

<script>
import Echarts from './plugins/echart'
import userStore from './storage/user'
import { provide } from 'vue'
import api from './api/index'
import $ from 'jquery'
import background from './storage/background'

export default {
  name: 'App',
  setup() {
    provide('echarts', Echarts) // provide Echarts
    provide('userStore', userStore)
  },
  components: {
  },
  mounted() {
    // 屏幕尺寸检查
    let width = $("body").width()
    let height = $("body").height()
    let widthCache = width
    // 检查展示尺寸，当页面没加载时height可能为0，会导致错误跳转，故排除此种情况
    if (width < 750 || (height != 0 && height < 500)) {
      this.$router.replace("/sorry")
    }
    let fontsize = width / 1536 * 16;//fontsize为当前屏幕的基数字体，相对于设计稿计算得到的。
    $("html").css("font-size", `${fontsize}px`)
    //当加载页面的时候设置生效
    window.onresize = () => {//当页面尺寸改变的时候生效
      return (() => {
        let width = $("body").width()
        let fontsize = width / 1536 * 16;
        $("html").css("font-size", `${fontsize}px`)
      })()
    }

    // 添加窗口尺寸监听
    window.addEventListener("resize", () => {
      let width = $("body").width()
      let height = $("body").height()
      if (width < 750 || (height != 0 && height < 500)) {
        this.$router.replace("/sorry")
        widthCache = width
      } else if (widthCache < 750 && width >= 750 && height >= 500) {
        this.$router.replace("/")
        widthCache = width
      }
    })

    if(!background.loaded) {
    // 加载背景图像
    api.getBackgrounds().then(res => {
      for(let item of res.data.data) {
        if(item.name in background) {
          background[item.name] = item.url
        }
      }
      background.loaded = true;
    })
  }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}


::selection {
  background-color: #FAF8F1;
  color: #3a3a3a;
  text-shadow: none;
}

div {
  font-family: "JetBrainsMono";
}

router-view {
  width: 100%;
  height: 100%;
}
</style>
