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
  <div>
    <h2>Crear nueva reserva</h2>
    <form @submit.prevent="crearReserva">
      <label>Número de Mesa:</label>
      <input type="number" v-model="numMesa" required>
      <label>Fecha de Reserva:</label>
      <input type="date" v-model="fechaReserva" required>
      <button type="submit">Crear Reserva</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      numMesa: '',
      fechaReserva: ''
    };
  },
  methods: {
    async crearReserva() {
      try {
        // Recuperar el ID del usuario del localStorage
        const userId = localStorage.getItem('userId');
        if (!userId) {
          console.error('No se encontró el ID del usuario');
          return;
        }

        // Enviar la solicitud de reserva al servidor, incluyendo el ID del usuario
        const response = await fetch('/api/reserva', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            id_usuario: userId,
            num_mesa: this.numMesa,
            fecha_reserva: this.fechaReserva
          })
        });

        const data = await response.json();
        console.log('Reserva creada con ID:', data);

      } catch (error) {
        console.error('Error al crear la reserva:', error);

      }
    }
  }
};

</script>


<style scoped>
@import "../assets/css/reserva.css";

</style>
