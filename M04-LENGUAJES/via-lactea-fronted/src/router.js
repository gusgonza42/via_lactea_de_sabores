import { createMemoryHistory, createRouter } from 'vue-router';

import LoginView from './components/LoginView.vue';
import RegistroView from './components/RegistroView.vue';
import HomeView from './components/HomeView.vue';
import MenuView from './components/MenuView.vue';
import SalaView from './components/SalaView.vue';
import ReservaView from './components/ReservaView.vue';
import AboutView from './components/AboutView.vue';
import AdminModeView from './components/AdminModeView.vue';

const routes = [
  { path: '/', component: LoginView },
  { path: '/registro', component: RegistroView },
  { path: '/home', component: HomeView },
  { path: '/menu', component: MenuView },
  { path: '/sala', component: SalaView },
  { path: '/reserva', component: ReservaView },
  { path: '/about', component: AboutView },
  { path: '/admin', component: AdminModeView }
];

const router = createRouter({
  history: createMemoryHistory(),
  routes
});

export default router;
