<template>
  <form onsubmit="return false">
    <div class="row align-items-center">
      <div class="col-auto">
        <label for="customRange" class="form-label"
          >Range in days: {{ request.range }}</label
        >
      </div>
      <div class="col"></div>
      <div class="col-auto">
        <button class="btn btn-outline-light" @click="update()">Update</button>
      </div>
    </div>
    <input
      type="range"
      class="form-range"
      min="10"
      max="120"
      step="5"
      id="customRange"
      v-model="request.range"
    />
  </form>
</template>

<script setup>
import { reactive } from "vue";

const urlParams = new URLSearchParams(window.location.search);

var request = reactive({
  id: urlParams.get("id"),
  range: urlParams.get("range"),
});

function update()
{
    localStorage.setItem("range", request.range);
    window.location = window.location.pathname + "?id=" + request.id + "&range=" + request.range;
}
</script>

<style scoped>
form {
  max-width: 95vw;
  margin: 3rem auto;
}
.form-label {
  margin-bottom: unset;
}
</style>