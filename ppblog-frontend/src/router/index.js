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
  },
  {
    path: '/articles/:tagName',
    name: 'articlesListView',
    component: () => import('../views/ArticlesListView.vue')
  },
  {
    path: '/article/:articleId',
    name: 'articleDetailView',
    component: () => import('../views/ArticleDetailView.vue')
  },
  {
    path: '/navigation',
    name: 'NavigationView',
    component: () => import('../views/LinksView.vue')
  },
  {
    path: '/statistic',
    name: 'StatisticView',
    component: () => import('../views/StatisticView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
