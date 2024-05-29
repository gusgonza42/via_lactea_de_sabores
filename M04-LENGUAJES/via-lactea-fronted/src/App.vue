<template>
  <div id="app">
    <router-view/>
    <footer>
      <p>© 2024 La Vía Lactea de Sabores Todos los derechos reservados</p>
    </footer>
  </div>
 
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fafafa;
  margin-top: 60px;
}
footer {
  background-color: #1b1b1b;
  padding: 10px;
  color: white;
  position: fixed;
  width: 100%;
  bottom: 0;
}
</style>
