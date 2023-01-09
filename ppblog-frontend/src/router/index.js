import { createRouter, createWebHistory } from 'vue-router'

import IndexView from '../views/IndexView'

const routes = [
  // 公共页面
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
  },
  {
    path: '/sorry',
    name: 'SorryView',
    component: () => import('../views/SorryView.vue')
  },

  // 管理端页面
  {
    path: '/admin',
    name: 'AdminView',
    component: () => import('../views/AdminView.vue'),
    children: [
      {
        path: '',
        name: 'HomeFragment',
        component: () => import('../components/admin/HomeFragment.vue')
      },
      {
        path: 'ariticle/list',
        name: 'ArticleListFragment',
        component: () => import('../components/admin/ArticleListFragment.vue')
      },
      {
        path: 'article/edit',
        name: 'EditArticleFragment',
        component: () => import('../components/admin/EditArticleFragment.vue')
      },
      {
        path: 'tag',
        name: 'TagFragment',
        component: () => import('../components/admin/TagFragment.vue')
      },
      {
        path: 'news',
        name: 'NewsFragment',
        component: () => import('../components/admin/NewsFragment.vue')
      },
      {
        path: 'link',
        name: 'LinkFragment',
        component: () => import('../components/admin/LinkFragment.vue')
      },
      {
        path: 'background',
        name: 'BackgroundFragment',
        component: () => import('../components/admin/BackgroundFragment.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
