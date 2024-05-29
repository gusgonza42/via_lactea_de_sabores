<template>
  <header>
    <nav class="navbar navbar-expand-sm navbar-dark fixed-top bg-primary container-fluid p-2">
      <div>
        <a class="navbar-brand" href="#"><img width="50px" height="50px" src="../assets/img/logo_via_lactea.png"
            alt="logo"></a>
      </div>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav me-auto">

          <li class="nav-item">
            <router-link to="/home" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/menu" class="nav-link">Menu</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/sala" class="nav-link">Sala</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/reserva" class="nav-link">Reserva</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about" class="nav-link">About</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/admin" class="nav-link">AdminMode</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/perfil" class="nav-link">Perfil</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link">Logout</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <div class="container mt-5">
      <h2 class="text-center">MENU</h2>
      <div class="row mt-3">
        <div class="col-md-4">
          <h3 class="text-center">Menú Degustación</h3>
          <p class="text-center">Disfruta de una selección de platos exquisitos y sorprendentes.</p>
        </div>
        <div class="col-md-4">
          <h3 class="text-center">Menú a la Carta</h3>
          <p class="text-center">Elige tus platos favoritos y crea tu propia experiencia gastronómica.</p>
        </div>
        <div class="col-md-4">
          <h3 class="text-center">Menú del Día</h3>
          <p class="text-center">Descubre nuestras propuestas diarias con los mejores ingredientes frescos.</p>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  // No necesitas lógica específica en este componente
};
</script>


<style scoped>
@import "../assets/css/menu.css";

</style>
