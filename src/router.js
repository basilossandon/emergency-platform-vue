import Vue from 'vue'
import Router from 'vue-router'
import NewTask from './views/NewTask.vue'
import NewEmergency from './views/NewEmergency.vue'
import NewVolunteer from './views/NewVolunteer.vue'
import Emergency from './views/Emergency.vue'
import Task from './views/Task.vue'
import Volunteer from './views/Volunteer.vue'
import home from './views/home.vue'
import Login from './components/login.vue'
import Register from './components/register.vue'


Vue.use(Router);
var router = new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        guest: true
      }
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: {
        guest: true
      }
    },
    {
      path: '/',
      name: 'home',
      component: home,
      meta: {
        guest: true
      }
    },
    {
      path: '/emergencies',
      name: 'emergency',
      component: Emergency,
      meta: {
        guest: true
      }
    },
    {
      path: '/new-task',
      name: 'new-task',
      component: NewTask,
      meta: {
        requiresAuth: true,
        is_admin: true
      }
    },
    {
      path: '/new-emergency',
      name: 'new-emergency',
      component: NewEmergency,
      meta: {
        requiresAuth: true,
        is_admin: true
      }
    },
    {
      path: '/new-volunteer',
      name: 'new-volunteer',
      component: NewVolunteer,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/tasks',
      name: 'task',
      component: Task
    },
    {
      path: '/volunteers',
      name: 'volunteer',
      component: Volunteer,
      meta: {
        requiresAuth: true,
        is_admin: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (localStorage.getItem('jwt') == null) {
      next({
        path: '/login',
        params: { nextUrl: to.fullPath }
      })
    } else {
      let user = JSON.parse(localStorage.getItem('user'))
      if (to.matched.some(record => record.meta.is_admin)) {
        if (user.is_admin == 1) {
          next()
        }
        else {
          next({ name: 'userboard' })
        }
      } else {
        next()
      }
    }
  } else if (to.matched.some(record => record.meta.guest)) {
    if (localStorage.getItem('jwt') == null) {
      next()
    }
    else {
      next({ name: 'userboard' })
    }
  } else {
    next()
  }
})


export default router;
