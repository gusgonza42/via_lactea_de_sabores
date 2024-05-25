package abp.project.mesapp.dao;

import abp.project.mesapp.data.FileManager;
import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import abp.project.mesapp.util.Main;
import org.springframework.stereotype.Component;

import java.awt.font.TextHitInfo;
import java.sql.*;
import java.util.EnumMap;
import java.util.Scanner;

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
        boolean success = false;

        try (Connection conn = DataBaseConnection.getConnection()) {
            // si existe el usuario
            try (PreparedStatement ps = conn.prepareStatement(Constantes.USER_EXISTS)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        throw new CheckError(CheckError.ERROR_USER_EXISTS);
                    }
                }
            }

            // Insert de un usuario nuevo
            try (PreparedStatement ps = conn.prepareStatement(Constantes.INSERT_NEW_USUARI0)) {
                ps.setString(1, nombre);
                ps.setString(2, apellido1);
                ps.setString(3, apellido2);
                ps.setDate(4, fechaNacimiento); // Cambiado a java.sql.Date
                ps.setString(5, email);
                ps.setDate(6, fechaRegistro); // Cambiado a java.sql.Date
                ps.setString(7, telefono);
                ps.setString(8, contrasena);

                int result = ps.executeUpdate();
                if (result > 0) {
                    success = true;
                } else {
                    throw new CheckError(CheckError.ERROR_USER);
                }
            }
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL_INSERT_NEW_USER);
        }
        return success;
    }
}
