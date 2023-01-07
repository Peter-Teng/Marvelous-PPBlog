<template>
  <router-view />
</template>

<script>
import Echarts from './plugins/echart'
import { provide } from 'vue'

export default {
  name: 'App',
  setup() {
    provide('echarts', Echarts) // provide Echarts
  },
  components: {
  },
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

.explicit {
  background: #F8F8F8;
}

.explicit div {
  color: #0a0a0a;
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
