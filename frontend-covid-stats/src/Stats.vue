<template>
  <Header />
  <Diagram />
</template>

<script>
import Header from "./components/Header.vue";
import Diagram from "./components/Diagram.vue";

const urlParams = new URLSearchParams(window.location.search);
document.title = urlParams.get("id") + " | Covid-Stats";

export default {
  name: "App",
  components: {
    Diagram,
    Header,
  },
};
</script>

<style>
</style>
