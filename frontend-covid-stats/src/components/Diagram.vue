<template>
  <div>
    <LineChart ref="doughnutRef" :chartData="chartData" :options="options" />
    <p> {{msg}} </p>
  </div>
</template>

<script>
import { Chart, registerables } from "chart.js";
import { computed, defineComponent, ref } from "vue";
import { LineChart } from "vue-chart-3";

// Can't access msg in setup?!

Chart.register(...registerables);

export default defineComponent({
  name: "Home",
  props: {
    msg: String
  },
  components: { LineChart },
  setup() {
    var labelsResponse = ["loading"];
    var dataReponse = [1];

    const data = ref(dataReponse);

    fetch("http://localhost:8888/covid-stats-api")
      .then((respons) => {
        return respons.json();
      })
      .then((respons) => {
        labelsResponse = [];
        dataReponse = [];
        respons.stats.forEach((element) => {
          labelsResponse.push(element.date);
          dataReponse.push(element.inzidenz);
        });
        data.value = dataReponse;
      });

    const chartData = computed(() => ({
      labels: labelsResponse,
      datasets: [
        {
          data: data.value,
          backgroundColor: [
            "#77CEFF",
            "#0079AF",
            "#123E6B",
            "#97B0C4",
            "#A5C8ED",
          ],
        },
      ],
    }));

    const options = ref({
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        },
        title: {
          display: true,
          text: 'Covid-Stats',
        },
      },
    });

    return { chartData, options };
  },
});
</script>
