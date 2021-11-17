<template>
  <Diagram msg="paf"/>
</template>

<script>
import Diagram from "./components/Diagram.vue";

export default {
  name: "App",
  components: {
    Diagram
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 80rem !important;
  margin: 4rem auto;
}
</style>
