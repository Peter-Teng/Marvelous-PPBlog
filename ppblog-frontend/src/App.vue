<template>
  <router-view class="wrap" />
</template>

<script>
import Echarts from './plugins/echart'
import { provide } from 'vue'
import $ from 'jquery'

export default {
  name: 'App',
  setup() {
    provide('echarts', Echarts) // provide Echarts
  },
  components: {
  },
  mounted() {
    let width = $("body").width();
    let widthCache = width
    if (width < 750) {
      this.$router.replace("/sorry")
    }
    let fontsize = width / 1536 * 16;//fontsize为当前屏幕的基数字体，相对于设计稿计算得到的。
    $("html").css("font-size", `${fontsize}px`)
    //当加载页面的时候设置生效
    window.onresize = () => {//当页面尺寸改变的时候生效
      return (() => {
        let width = $("body").width();
        let fontsize = width / 1536 * 16;
        $("html").css("font-size", `${fontsize}px`)
      })()
    }

    window.addEventListener("resize", () => {
      let width = $("body").width();
      if (width < 750) {
        this.$router.replace("/sorry")
        widthCache = width
      } else if (widthCache < 750 && width >= 750) {
        this.$router.replace("/")
        widthCache = width
      }
    });
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
  white-space: pre-wrap;
}

router-view {
  width: 100%;
  height: 100%;
}
</style>
