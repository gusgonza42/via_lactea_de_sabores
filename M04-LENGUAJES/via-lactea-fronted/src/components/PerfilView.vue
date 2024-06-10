<template>
  <!-- ... -->
  <div class="content">
    <!-- ... -->
    <div v-if="!loadingReservations">
      <h2>Reservas:</h2>
      <div v-if="reservations.length > 0">
        <div v-for="(reservation, index) in reservations" :key="index">
          <h3>Reserva {{ index + 1 }}</h3>
          <p>Fecha: {{ reservation.fecha }}</p>
          <p>Hora: {{ reservation.hora }}</p>
          <!-- ... otros datos de la reserva ... -->
        </div>
      </div>
      <div v-else>
        <p>No hay reservas disponibles.</p>
      </div>
    </div>
    <div v-else>
      <p>Cargando reservas...</p>
    </div>
  </div>
  <!-- ... -->
</template>

<script>
export default {
  // ...
  data() {
    return {
      reservations: [],
      loadingReservations: true,
    };
  },
  methods: {
    async fetchReservations() {
      try {
        const idCliente = this.data.idCliente; // obtener el id del cliente del objeto de datos
        const response = await fetch(`http://localhost:8081/api/perfil/${idCliente}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          const data = await response.json();
          this.reservations = data;
          this.loadingReservations = false;
        } else {
          console.error('Error en la llamada a la API:', response.statusText);
          alert('Ha ocurrido un error al obtener las reservas.');
        }
      } catch (error) {
        console.error('Error al obtener las reservas:', error);
        alert('No se pudieron obtener las reservas.');
      }
    }
  },
  mounted() {
    this.user();
    this.fetchReservations();
  },
};
</script>

<style scoped>
@import "../assets/css/perfil.css";
</style>