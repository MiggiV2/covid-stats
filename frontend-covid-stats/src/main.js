import { createApp, h } from 'vue'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "bootstrap-icons/font/bootstrap-icons.css"

import Stats from './Stats.vue'
import NoPage from './NoPage.vue';
import Start from './Start.vue';

require('@/assets/style.css');

const routes = {
    '/': Start,
    '/landkreis': Stats,
    '/bundesland': Stats,
}

const SimpleRouter = {
    data: () => ({
        currentRoute: window.location.pathname
    }),

    computed: {
        CurrentComponent() {
            return routes[this.currentRoute] || NoPage
        }
    },

    render() {
        return h(this.CurrentComponent)
    }
}

const app = createApp(SimpleRouter);

app.mount('#app')

export var HOST = "http://localhost:8888/";
