import Vue from 'vue'
import App from './App.vue'
import axios from 'axios';
import router from './router'
Vue.config.productionTip = false

require("./assets/style/style.scss");

const axiosInstance = axios.create({ //configurar para backend
  baseURL: 'http://localhost:4567'
})

Vue.prototype.$http = axiosInstance;


import VueNavigationBar from "../src/index.js";
Vue.component("vue-navigation-bar", VueNavigationBar);

new Vue({
  el: "#app",
  router,
  render: h => h(App)
}).$mount('#app')