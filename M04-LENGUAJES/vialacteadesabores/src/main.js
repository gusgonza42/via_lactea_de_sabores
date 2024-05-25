  import { createApp } from 'vue';
  import App from './App.vue';
  import router from './router';

  const app = createApp(App);
  /*router.beforeEach((to, from, next) => {
    document.title = to.meta.title || 'La Vía Lactea de Sabores';
    next();
  });*/

  // Aquí puedes añadir cualquier configuración adicional antes de montar la aplicación, como plugins, componentes globales, etc.

  app.use(router); // Instalar Vue Router

  app.mount('#app');
