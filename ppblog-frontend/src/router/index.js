import { createRouter, createWebHistory } from 'vue-router'

import IndexView from '../views/IndexView'

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  {
    path: '/login',
    name: 'loginView',
    component: () => import('../views/LoginView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
