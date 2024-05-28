<template>
	<div class="registro-container">
			<video autoplay muted loop class="video-background">
				<source src="../assets/video/space_back.mp4" type="video/mp4">
				Tu navegador no soporta el elemento de video.
			</video>
			<h2>Registrate</h2>
			<form @submit.prevent="register" class="registro-form">
				<div class="form-group">
					<label for="nombre">Nombre:</label>
					<input type="text" id="nombre" v-model="nombre" required>
				</div>
				<div class="form-group">
					<label for="apellido1">Primer Apellido:</label>
					<input type="text" id="apellido1" v-model="apellido1" required>
				</div>
				<div class="form-group">
					<label for="apellido2">Segundo Apellido:</label>
					<input type="text" id="apellido2" v-model="apellido2" required>
				</div>
				<div class="form-group">
					<label for="fechaNacimiento">Fecha de Nacimiento:</label>
					<input type="date" id="fecha_nacimiento" v-model="fecha_nacimiento" required>
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input type="email" id="email" v-model="email" required>
				</div>
				<div class="form-group">
					<label for="telefono">Teléfono:</label>
					<input type="tel" id="telefono" v-model="telefono" required>
				</div>
				<div class="form-group">
					<label for="contrasena">Contraseña:</label>
					<input type="password" id="contrasena" v-model="contrasena" required>
				</div>
				<button type="submit" class="registro-button">Registrarse</button>
				<button type="button" @click="goToLogin" class="volver-button">Volver al inicio de sesión</button>
			</form>
		</div>
	</template>
	
	<script>
	export default {
		name: 'RegistroView',
		data() {
			return {
				nombre: '',
				apellido1: '',
				apellido2: '',
				fecha_nacimiento: '',
				email: '',
				telefono: '',
				contrasena: ''
			};
		},
		methods: {
			async register() {
				try {
					const fecha_registro = new Date().toISOString().split('T')[0]; // Formato YYYY-MM-DD
	
					console.log('Datos para registro:', {
						nombre: this.nombre,
						apellido1: this.apellido1,
						apellido2: this.apellido2,
						fecha_nacimiento: this.fecha_nacimiento,
						email: this.email,
						fecha_registro: fecha_registro,
						telefono: this.telefono,
						contrasena: this.contrasena
					});
	
					const response = await fetch('http://localhost:8081/api/registro', {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify({
							nombre: this.nombre,
							apellido1: this.apellido1,
							apellido2: this.apellido2,
							fecha_nacimiento: this.fecha_nacimiento,
							email: this.email,
							fecha_registro: fecha_registro,
							telefono: this.telefono,
							contrasena: this.contrasena
						})
					});
	
					if (response.ok) {
						alert('Registro exitoso! ENTRANDO');
						this.$router.push('/home');
					} else {
						console.error('Error en el registro:', response.statusText);
						alert('Registro fallido. Por favor, verifica tus datos.');
					}
				} catch (error) {
					console.error('Error en la solicitud HTTP:', error);
					alert('Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde.');
				}
			},
			goToLogin() {
				this.$router.push('/');
			}
		}
	};
	</script>
  
  <style scoped>
  @import "../assets/css/registro.css";
  
  
  </style>
  