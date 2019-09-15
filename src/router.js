import Vue from 'vue'
import Router from 'vue-router'
import NewTask from './views/NewTask.vue'
import NewEmergency from './views/NewEmergency.vue'
import NewVolunteer from './views/NewVolunteer.vue'
import Emergency from './views/Emergency.vue'


Vue.use(Router);
var router = new Router({
  routes: [
    {
      path:'/',
      name:'home',
      component: Emergency
    },
    {
      path:'/emergencies',
      name:'emergency',
      component: Emergency
    },
    {
      path:'/new-task',
      name:'new-task',
      component: NewTask
    },
    {
      path:'/new-emergency',
      name:'new-emergency',
      component: NewEmergency
    },
    {
      path:'/new-volunteer',
      name:'new-volunteer',
      component: NewVolunteer
    }
  ]
})

export default router;