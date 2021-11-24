<template>
  <div class="header-wrapped">
    <div class="row header-content align-items-center">
      <div class="col-auto">
        <h1 class="header-title"><a href="/">Covid-Stats</a></h1>
      </div>
      <div class="col"></div>
      <div class="col-auto">
        <button
          class="btn btn-outline-light"
          data-bs-toggle="modal"
          data-bs-target="#searchModal"
          @click="loadLandKreise"
        >
          <h4><a class="desktop">Search </a><i class="bi-search"></i></h4>
        </button>
      </div>
    </div>
  </div>
  <div class="header-dummy" />

  <!-- Search-Modal -->
  <div
    class="modal fade"
    id="searchModal"
    tabindex="-1"
    aria-labelledby="searchModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h3 class="modal-title" id="searchModalLabel">
            {{ mode.text }}
          </h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form onsubmit="return false">
            <div class="input-group">
              <button
                class="btn btn-outline-light dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                {{ mode.text }}
              </button>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" @click="setMode(1)"> Landkreis </a>
                </li>
                <li>
                  <a class="dropdown-item" @click="setMode(2)"> Bundesland </a>
                </li>
              </ul>
              <input
                autofocus
                type="text"
                class="form-control"
                placeholder="Search..."
                v-model="request.query"
                @input="updateContent"
              />
              <!--
               <input
                autofocus
                type="text"
                class="form-control"
                placeholder="Disabled!"
                disabled
                v-if="mode.text != 'Bundesland'"
                v-else
                v-model="request.query"
                @input="updateContent"
              />
              -->
              <button type="submit" class="btn btn-primary">
                <i class="bi-search"></i>
              </button>
            </div>
            <div class="range">
              <input
                type="range"
                class="form-range"
                min="10"
                max="120"
                step="5"
                id="customRange"
                v-model="request.range"
              /><label for="customRange" class="form-label"
                >Range in days: {{ request.range }}</label
              >
            </div>
          </form>
          <div class="search-content">
            <h4 v-for="(item, index) in request.content" :key="index">
              <a
                :href="
                  mode.text.toLowerCase() +
                  '?id=' +
                  item +
                  '&range=' +
                  request.range
                "
                @click="saveRange()"
              >
                {{ item }}
              </a>
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import { HOST } from "../main";

var mode = reactive({
  text: String,
});

var request = reactive({
  query: "",
  range: localStorage.getItem("range") ? localStorage.getItem("range") : 30,
  data: [],
  content: [],
});

var bundesLaender = [
  "Bremen",
  "Saarland",
  "Mecklenburg-Vorpommern",
  "Schleswig-Holstein",
  "Hamburg",
  "Sachsen-Anhalt",
  "Brandenburg",
  "Thüringen",
  "Rheinland-Pfalz",
  "Berlin",
  "Niedersachsen",
  "Sachsen",
  "Hessen",
  "Baden-Württemberg",
  "Bayern",
  "Nordrhein-Westfalen",
];

mode.text = localStorage.getItem("mode") == "1" ? "Landkreis" : "Bundesland";

function setMode(input) {
  var modeStr = input == 1 ? "Landkreis" : "Bundesland";
  localStorage.setItem("mode", input);
  mode.text = modeStr;
  if (input == 1) {
    setFirstForty();
  } else {
    request.content = bundesLaender;
    request.query = "";
  }
}

function loadLandKreise() {
  if (request.data.length == 0) {
    fetch(HOST + "covid-stats-api/landkreise")
      .then((response) => {
        if (response.status == 200) {
          return response.json();
        }
        console.log(response);
      })
      .then((data) => {
        if (data != null) {
          request.data = data;
        }
        if (localStorage.getItem("mode") == 1) {
          setFirstForty();
        }
      });
  }
  if (localStorage.getItem("mode") == 1) {
    setFirstForty();
  } else {
    request.content = bundesLaender;
    request.query = "";
  }
}

// FeedBack -> Filter Funktion: von - bis
function updateContent() {
  request.content = [];
  if (mode.text == "Landkreis") {
    updateContentFoLandKreis();
  } else {
    updateContentFoBundesLand();
  }
}

function updateContentFoBundesLand() {
  bundesLaender.forEach((item) => {
    if (item.startsWith(request.query)) {
      request.content.push(item);
    }
  });
  if (request.content.length == 0) {
    bundesLaender.forEach((item) => {
      if (item.includes(request.query)) {
        request.content.push(item);
      }
    });
  }
}

function updateContentFoLandKreis() {
  if (request.query.length == 0) {
    // Set default
    var min = request.data.length < 40 ? request.data.length : 40;
    for (var i = 0; i < min; i++) {
      request.content.push(request.data[i]);
    }
  } else {
    request.data.forEach((item) => {
      if (item.startsWith(request.query)) {
        request.content.push(item);
      }
    });
    if (request.content.length == 0) {
      request.data.forEach((item) => {
        if (item.includes(request.query)) {
          request.content.push(item);
        }
      });
    }
  }
}

function setFirstForty() {
  var min = request.data.length < 40 ? request.data.length : 40;
  request.content = [];
  for (var i = 0; i < min; i++) {
    request.content.push(request.data[i]);
  }
}

function saveRange() {
  localStorage.setItem("range", request.range);
}
</script>


<style scoped>
.header-wrapped {
  width: 100vw;
  height: 4.3rem;
  background-color: var(--background-color-secondary);
  border-bottom: 1px solid white;
  position: fixed;
}
.header-content {
  width: 95%;
  height: 100%;
  margin: auto;
}
.header-title {
  padding-top: 12px;
  padding-left: 7px;
}
.header-title:hover {
  text-decoration: underline;
}
.header-dummy {
  height: 4rem;
}
#navbarDropdown {
  padding-top: 14px;
  color: white;
}
#searchModalLabel {
  margin-left: 2rem;
}
h4 {
  margin-top: 2px;
  margin-bottom: 2px;
}
.modal-content {
  background: var(--background-color-primary);
}
.search-content {
  text-align: center;
  padding-left: 2rem;
}
.search-content h4 {
  margin-bottom: 1rem;
}
.search-content a:hover {
  text-decoration: underline;
}
.input-group {
  max-width: 94%;
  margin: 1rem auto 1rem;
}
.range {
  max-width: 94%;
  margin: 1rem auto 0.4rem;
}
a {
  color: unset;
  text-decoration: unset;
}
.header-title a:hover {
  text-decoration: unset;
}
h4 a:hover {
  text-decoration: unset;
}
a:hover {
  color: unset;
  text-decoration: underline;
}
li a:hover {
  cursor: pointer;
}
@media (max-width: 768px) {
  .desktop {
    display: none;
  }
}
</style>