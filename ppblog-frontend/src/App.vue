<template>
  <router-view />
</template>

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
