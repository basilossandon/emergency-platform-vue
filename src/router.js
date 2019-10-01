import Vue from 'vue'
import Router from 'vue-router'
import NewTask from './views/NewTask.vue'
import NewEmergency from './views/NewEmergency.vue'
import NewVolunteer from './views/NewVolunteer.vue'
import Emergency from './views/Emergency.vue'
import Task from './views/Task.vue'
import Volunteer from './views/Volunteer.vue'
import home from './views/home.vue'
import Import from './views/Import.vue'
Vue.use(Router);
var router = new Router({
  routes: [
    {
      path:'/',
      name:'home',
      component: home
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
    },
    {
      path:'/tasks',
      name:'task',
      component: Task
    },
    {
      path:'/volunteers',
      name:'volunteer',
      component: Volunteer
    },
    {
      path:'/import',
      name:'import',
      component: Import
    },
  ]
})

export default router;
