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

	<body class="content">
      <h2 class="text-center">Perfil</h2>
      <div class="profile-container" v-if="!loading">
          <p v-if="usuario == null">No hay usuario</p>
          <div v-else>
            <h1>Nombre: {{ usuario.nombre }}</h1>
            <h2>Apellido: {{ usuario.apellido1 }}</h2>
			<h2>Apellido: {{ usuario.apellido2 }}</h2>
			<h2>Fecha de nacimiento: {{ usuario.fechaNacimiento }}</h2>
            <h3>Correo: {{ usuario.email }}</h3>
            <h4>Contraseña: {{ usuario.contrasena }}</h4>
          </div>
        </div>
        <div v-else>
          <p>Cargando...</p>
        </div>
    </body>
    <footer>
      <p>© 2024 La Vía Lactea de Sabores Todos los derechos reservados</p>
  </footer>
</template>

<script>
export default {
  name: 'PerfilView',
  mounted() {
	this.user();
  },
  data() {
    return {
    usuario: [],
	loading: true
    };
  },
  methods: {
    async user() {
      try {
        const response = await fetch('http://localhost:8081/api/perfil', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          const data = await response.json();
          this.usuario = data;
          this.loading = false;
        } else {
          console.error('Error en la llamada a la API:', response.statusText);
          alert('Ha ocurrido un error.');
        }
      } catch (error) {
        console.error('Error al autenticar al usuario:', error);
        alert('No hay usuario.');
      }
    }
  }
};
</script>



<style scoped>
@import "../assets/css/perfil.css";
</style>
