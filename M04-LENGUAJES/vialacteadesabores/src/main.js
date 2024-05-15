import { createApp } from 'vue';
  import App from './App.vue';
  import router from './router';

  const app = createApp(App);

  // Aquí puedes añadir cualquier configuración adicional antes de montar la aplicación, como plugins, componentes globales, etc.

  app.use(router); // Instalar Vue Router

  app.mount('#app');