<template>
  <Header />
  <Diagram />
</template>

<script>
import Header from "./components/Header.vue";
import Diagram from "./components/Diagram.vue";

export default {
  name: "App",
  components: {
    Diagram,
    Header
  },
};
</script>

<style>
</style>
