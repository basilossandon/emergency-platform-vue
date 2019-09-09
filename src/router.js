import Vue from 'vue'
import Router from 'vue-router'
import NewDog from './views/NewDog.vue'
import Emergency from './views/Emergency.vue'


Vue.use(Router);
var router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Emergency
    },
    {
      path:'/new-dog',
      name:'new-dog',
      component: NewDog
    },
    {
      path:'/emergencies',
      name:'emergency',
      component: Emergency
    },
  ]
})

export default router;
