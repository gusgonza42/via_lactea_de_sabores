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
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1 class="text-center">Sala</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Gestión de la sala</h5>
            <p class="card-text">Aquí podrás gestionar la sala del restaurante.</p>
            <router-link to="/sala" class="btn btn-primary">Ver sala</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // Puedes agregar lógica específica del componente aquí si es necesario
};
</script>

<style scoped>
@import "../assets/css/sala.css";

</style>
