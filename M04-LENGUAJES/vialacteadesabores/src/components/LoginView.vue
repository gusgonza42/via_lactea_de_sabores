<template>
  <div class="section">
    <video autoplay muted loop class="video-background">
      <source src="../assets/video/(68) monkey for 10 hours - YouTube - Google Chrome 2024-05-13 12-07-17.mp4" type="video/mp4">
      Tu navegador no soporta el elemento de video.
    </video>
    <div class="container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <h6 class="mb-0 pb-3"><span>Log In </span><span>Sign Up</span></h6>
            <input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
            <label for="reg-log"></label>
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">Log In</h4>
                      <div class="form-group">
                        <input v-model="username" type="email" class="form-style" placeholder="Email">
                        <i class="input-icon uil uil-at"></i>
                      </div>  
                      <div class="form-group mt-2">
                        <input v-model="password" type="password" class="form-style" placeholder="Password">
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <button @click="login" class="btn mt-4">Login</button>
                      <p class="mb-0 mt-4 text-center"><a href="https://www.web-leb.com/code" class="link">Forgot your password?</a></p>
                    </div>
                  </div>
                </div>
                <!-- Resto del contenido omitido para mayor claridad -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
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
      // Lógica para autenticar al usuario
      if (this.username === 'usuario' && this.password === 'contraseña') {
        // Redirigir al usuario a la página de inicio
        this.$router.push('/home');
      } else {
        alert('Credenciales incorrectas. Por favor, inténtalo de nuevo.');
      }
    }
  }
};
</script>

<style scoped>
@import "../assets/css/login.css";
/* Estilos específicos del componente Login */
</style>
