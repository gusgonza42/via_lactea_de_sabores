package abp.project.mesapp.dao;

import abp.project.mesapp.data.FileManager;
import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.model.Usuario;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import abp.project.mesapp.util.Main;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.awt.font.TextHitInfo;
import java.sql.*;
import java.util.EnumMap;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

@Component
public class UsuarioDao {

    public boolean login(String email, String contrasena) throws CheckError {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.SELECT_USUARIO);
            ps.setString(1, email);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
            } else {
                throw new CheckError(CheckError.ERROR_USUARIO_NO_EXISTE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CheckError(CheckError.ERROR_LECTURA_DATOS);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CheckError(CheckError.ERROR_LECTURA_DATOS);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CheckError(CheckError.ERROR_BBDD_FAIL);
            }
        }
        return success;
    }

    /* NUEVO CLIENTE*/
    public boolean register(String nombre, String apellido1, String apellido2, Date fechaNacimiento, String email, Date fechaRegistro, String telefono, String contrasena) throws CheckError {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.USER_EXISTS);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.next()) {
                    return success;
                }
            }

            //new user
            ps = conn.prepareStatement(Constantes.INSERT_NEW_USUARI0);
            ps.setString(1, nombre);
            ps.setString(2, apellido1);
            ps.setString(3, apellido2);
            ps.setDate(4, fechaNacimiento);
            ps.setString(5, email);
            ps.setDate(6, fechaRegistro);
            ps.setString(7, telefono);
            ps.setString(8, contrasena);
            int result = ps.executeUpdate();
            if (result > 0) {
                success = true;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return success;
    }

    public Usuario findByEmail(String email) throws CheckError {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.SELECT_PERFIL_MAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido1(rs.getString("apellido1"));
                usuario.setApellido2(rs.getString("apellido2"));
                usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFecha_registro(rs.getDate("fecha_registro"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setContrasena(rs.getString("contrasena"));
            } else {
                throw new CheckError(CheckError.ERROR_USUARIO_NO_EXISTE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CheckError(CheckError.ERROR_LECTURA_DATOS);
            }
        }
        return usuario;
    }}
