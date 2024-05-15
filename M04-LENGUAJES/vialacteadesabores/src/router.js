import { createMemoryHistory, createRouter } from 'vue-router'

  import LoginView from './components/LoginView.vue';
  import HomeView from './components/HomeView.vue';

  const routes = [
  { path: '/', component: LoginView },
  { path: '/home', component: HomeView },
  ]

  const router = createRouter({
  history: createMemoryHistory(),
  routes,
  })

  export default router