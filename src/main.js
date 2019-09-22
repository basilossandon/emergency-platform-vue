import Vue from 'vue'
import App from './App.vue'
import axios from 'axios';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/es';

Vue.config.productionTip = false
Vue.use(ElementUI, { locale });


require("./assets/style/style.scss");

const axiosInstance = axios.create({ //configurar para backend
  baseURL: 'http://localhost:4567'
})

Vue.prototype.$http = axiosInstance;
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')