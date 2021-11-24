<template>
  <Header />
  <div class="box no-page">
    <h1>404 - Page not found</h1>
    <hr />
    <a class="btn btn-outline-light">Main page</a>    
    <h4>¯\_(ツ)_/¯</h4>
    <h4>Nothing to see!</h4>
  </div>
</template>

<script setup>
import Header from "./components/Header.vue";

document.title = "Sorry | Covid-Stats";
</script>

<style scoped>
.no-page {
  max-width: 34rem;
}
.no-page a {
  margin-bottom: 0.6rem;
}
.no-page h4 {
  margin-bottom: 1rem;
}
</style>