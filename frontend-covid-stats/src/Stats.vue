<template>
  <Header />
  <Diagram />
  <SliderOption />
</template>

<script>
import Header from "./components/Header.vue";
import Diagram from "./components/Diagram.vue";
import SliderOption from "./components/SliderOption.vue";

const urlParams = new URLSearchParams(window.location.search);
document.title = urlParams.get("id") + " | Covid-Stats";

export default {
  name: "App",
  components: {
    Diagram,
    Header,
    SliderOption
  },
};
</script>

<style>
</style>
