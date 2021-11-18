<template>
  <div class="header-wrapped">
    <div class="row header-content align-items-center">
      <div class="col-auto">
        <h2 class="header-title"><a href="/">Covid-Stats</a></h2>
      </div>
      <div class="col"></div>
      <div class="col-auto">
        <button
          class="btn btn-outline-light"
          data-bs-toggle="modal"
          data-bs-target="#searchModal"
          @click="loadLandKreise"
        >
          <h4>Search <i class="bi-search"></i></h4>
        </button>
      </div>
      <div class="col-auto">
        <h4
          class="nav-link dropdown-toggle"
          href="#"
          id="navbarDropdown"
          role="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          {{ mode.text }}
        </h4>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li>
            <a class="dropdown-item" @click="setMode(1)"> Landkreis </a>
          </li>
          <li>
            <a class="dropdown-item" @click="setMode(2)"> Bundesland </a>
          </li>
        </ul>
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
          <h5 class="modal-title" id="searchModalLabel">
            {{ mode.text }}
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form
            @submit="search"
            onsubmit="return false"
            v-if="mode.text == 'Landkreis'"
          >
            <div class="input-group">
              <input
                autofocus
                type="text"
                class="form-control"
                placeholder="Search..."
                v-model="request.query"
                @input="updateContent"
              />
              <button type="submit" class="btn btn-primary">
                <i class="bi-search"></i>
              </button>
            </div>
          </form>
          <div class="search-content">
            <h4 v-for="(item, index) in request.content" :key="index">
              <a
                :href="'landkreis?id=' + item + '&range=30'"
                v-if="mode.text == 'Landkreis'"
              >
                {{ item }}
              </a>
              <a :href="'bundesland?id=' + item + '&range=30'" v-else>
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
  data: [],
  content: [],
});

mode.text = localStorage.getItem("mode") == "1" ? "Landkreis" : "Bundesland";

function setMode(input) {
  var modeStr = input == 1 ? "Landkreis" : "Bundesland";
  localStorage.setItem("mode", input);
  mode.text = modeStr;
  if (input == 1) {
    request.content = request.data;
  } else {
    request.content = [
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
  }
}

function loadLandKreise() {
  if (request.data.length == 0) {
    fetch(HOST + "/covid-stats-api/landkreise")
      .then((response) => {
        if (response.status == 200) {
          return response.json();
        }
        console.log(response);
      })
      .then((data) => {
        if (data != null) {
          request.data = data;
          var min = data.length < 40 ? data.length : 40;
          for (var i = 0; i < min; i++) {
            request.content.push(data[i]);
          }
        }
      });
  } else {
    request.content = request.data;
  }
}

function updateContent() {
  request.content = [];
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
</script>


<style>
.header-wrapped {
  width: 100vw;
  height: 4rem;
  background-color: #3a3a41;
  border-bottom: 1px solid white;
  position: fixed;
}
.header-content {
  width: 99%;
  height: 100%;
}
.header-title {
  padding-top: 7px;
  padding-left: 7px;
}
.header-dummy {
  height: 4rem;
}
#navbarDropdown {
  padding-top: 14px;
  color: white;
}
h4 {
  margin-top: 2px;
  margin-bottom: 2px;
}
.modal-content {
  background: #4c4c55;
}
.search-content {
  text-align: center;
  padding-left: 2rem;
  padding-top: 1rem;
}
.input-group {
  max-width: 94%;
  margin: auto;
}
a {
  color: unset;
  text-decoration: unset;
}
.header-title a:hover {
  text-decoration: unset;
}
a:hover {
  color: unset;
  text-decoration: underline;
}
</style>