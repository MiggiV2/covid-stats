<template>
  <div class="diagram">
    <LineChart ref="doughnutRef" :chartData="chartData" :options="options" />
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";
import { computed, defineComponent, ref } from "vue";
import { LineChart } from "vue-chart-3";

Chart.register(...registerables);

export default defineComponent({
  name: "Home",
  components: { LineChart },
  setup() {
    var labelsResponse = ["loading"];
    var dataReponse = [1];
    const urlParams = new URLSearchParams(window.location.search);
    const data = ref(dataReponse);
    const path =
      window.location.pathname == "/landkreis"
        ? "get-landkreis-stats"
        : "get-bundesland-stats";

    loadData();

    const chartData = computed(() => ({
      labels: labelsResponse,
      datasets: [
        {
          label: "Inzidenz",
          data: data.value,
          backgroundColor: ["#FFFFFF"],
          borderColor: ["#E0E0E0"],
          borderWidth: 3,
        },
      ],
    }));

    const options = ref({
      responsive: true,
      plugins: {
        legend: {
          position: "hidden",
        },
        title: {
          display: true,
          text: urlParams.get("id") + " | Covid-Stats",
          color: "white",
          font: {
            size: 24,
          },
        },
      },
      scales: {
        x: {
          display: true,
          ticks: {
            color: "#E0E0E0"
          },
          grid: {
            drawBorder: true,
            color: "#919191"
          },
        },
        y: {
          display: true,
          ticks: {
            color: "#E0E0E0"
          },
          grid: {
            drawBorder: true,
            color: "#919191"
          },
        },
      },
    });

    return { chartData, options };

    function loadData() {
      fetch(
        "http://localhost:8888/covid-stats-api/" +
          path +
          "?id=" +
          urlParams.get("id") +
          "&range=" +
          urlParams.get("range")
      )
        .then((respons) => {
          return respons.json();
        })
        .then((respons) => {
          labelsResponse = [];
          dataReponse = [];
          respons.forEach((element) => {
            labelsResponse.push(element.date.substring(5));
            dataReponse.push(element.cases7_bl_per_100k);
          });
          data.value = dataReponse;
        });
    }
  },
});
</script>

<style>
.diagram {
  max-width: 95vw;
  margin: 2rem auto;
}
</style>