import Vue from 'vue'
import Router from 'vue-router'
import Emergency from './views/Emergency.vue'


Vue.use(Router);
var router = new Router({
  routes: [
    {
      path:'/emergencies',
      name:'emergency',
      component: Emergency
    },
  ]
})

export default router;
