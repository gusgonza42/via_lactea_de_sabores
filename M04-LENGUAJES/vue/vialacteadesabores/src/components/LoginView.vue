<template>
    <div>
      <h2>Login</h2>
      <form @submit.prevent="login">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
        <button type="submit">Login</button>
      </form>
    </div>
  </template>
 
  <script>
  export default {
    data() {
      return {
        username: '',
        password: ''
      };
    },
    methods: {
      login() {
        // Aquí puedes agregar la lógica para autenticar al usuario
        // Por ejemplo, puedes hacer una llamada a una API
        // Y redirigir al usuario a la página de inicio si la autenticación es exitosa
        if (this.username === 'usuario' && this.password === 'contraseña') {
          // Redirigir al usuario a la página de inicio
          this.$router.push('/home');
        } else {
          alert('Credenciales incorrectas. Por favor, inténtalo de nuevo.');
          // También puedes mostrar un mensaje de error o limpiar los campos del formulario
        }
      }
    }
  };
  </script>
 
  <style scoped>
  /* Estilos específicos del componente Login */
  </style>