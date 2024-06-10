import { createMemoryHistory, createRouter } from 'vue-router';

import LoginView from './components/LoginView.vue';
import RegistroView from './components/RegistroView.vue';
import HomeView from './components/HomeView.vue';
import MenuView from './components/MenuView.vue';
import SalaView from './components/SalaView.vue';
import ReservaView from './components/ReservaView.vue';
import AboutView from './components/AboutView.vue';
import AdminModeView from './components/AdminModeView.vue';
import PerfilView from './components/PerfilView.vue';
import MongoView from './components/MongoView.vue';

const routes = [
  { path: '/', component: LoginView },
  { path: '/registro', component: RegistroView },
  { path: '/home', component: HomeView },
  { path: '/menu', component: MenuView },
  { path: '/sala', component: SalaView },
  { path: '/reserva', component: ReservaView },
  { path: '/about', component: AboutView },
  { path: '/admin', component: AdminModeView },
  { path: '/perfil', component: PerfilView },
  { path: '/mongo' , component: MongoView }
];

const router = createRouter({
  history: createMemoryHistory(),
  routes
});

export default router;
