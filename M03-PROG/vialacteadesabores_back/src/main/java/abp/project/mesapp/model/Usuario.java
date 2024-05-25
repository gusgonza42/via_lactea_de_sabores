package abp.project.mesapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Usuario {
    // Atributos clase USUARIOS
    private int id_usuario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fecha_nacimiento;
    private String email;
    private Date fecha_registro;


    private String telefono;
    private String contrasena;
// CONSTRUCTOR CLASE USUARIO

    public Usuario(int id_usuario, String nombre, String apellido1, String apellido2, Date fecha_nacimiento,
            String email, Date fecha_registro, String telefono,String contrasena) {
        setId_usuario(id_usuario);
        setNombre(nombre);
        setApellido1(apellido1);
        setApellido2(apellido2);
        setFecha_nacimiento(fecha_nacimiento);
        setEmail(email);
        setFecha_registro(fecha_registro);
        setTelefono(telefono);
        setContrasena(contrasena);
    }
    public Usuario(String email, String contrasena){
        setEmail(email);
        setContrasena(contrasena);
    }
    public Usuario(){

    }
    public Usuario(String nombre, String apellido1, String apellido2, Date fecha_nacimiento,
                   String email, Date fecha_registro, String telefono,String contrasena){
        setNombre(nombre);
        setApellido1(apellido1);
        setApellido2(apellido2);
        setFecha_nacimiento(fecha_nacimiento);
        setEmail(email);
        setFecha_registro(fecha_registro);
        setTelefono(telefono);
        setContrasena(contrasena);
    }
    //TODOS LOS QUE HEREDAN DE USUARIO
    public Usuario(int id_usuario) {
        setId_usuario(id_usuario);
    }


    // GETTERS CLASE USUARIO

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public String getTelefono() {
        return telefono;
    }
    // SETTERS CLASE USUARIO

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", email='" + email + '\'' +
                ", fecha_registro=" + fecha_registro +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
