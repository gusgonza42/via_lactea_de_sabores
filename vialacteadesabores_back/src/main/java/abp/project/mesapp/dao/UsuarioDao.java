package abp.project.mesapp.dao;

import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Scanner;

@Component
public class UsuarioDao {

    private void verificarUsuario() throws SQLException {
        Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
        PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_USUARIO);
        System.out.println(ps.toString());
        Scanner sc = new Scanner(System.in);
        String usuario;
        String contrasena;
        System.out.println("Ingrese su usuario: ");
        usuario = sc.nextLine();
        System.out.println("Ingrese su contrasena: ");
        contrasena = sc.nextLine();
        //Hacemos la consulta
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Usuario existente");
        } else {
            System.out.println("Usuario no existe");
        }
        System.out.println(ps);
    }


    public boolean login(String email, String contrasena) throws CheckError {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.SELECT_USUARIO);
            ps.setString(1, email);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_USUARIO_NO_EXISTE);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new CheckError(CheckError.ERROR_RESULT);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new CheckError(CheckError.ERROR_CONSULTA);

                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new   CheckError(CheckError.ERROR_BBDD_FAIL);
                }
            }
        }
        return false;
    }

    private void anadirNuevoCliente() throws CheckError {
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.ASIGNAR_MESA_LIBRE);
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id del usuario: ");
            int id_usuario = sc.nextInt();
            if (!usuarioExists(id_usuario)) {//si el user no existe
                throw new CheckError(CheckError.ERROR_USUARIO_NO_EXISTE);
            }
            System.out.println("Introduce el número de comensales: ");
            int num_comensales = sc.nextInt();
            if (mesaDisponible(num_comensales)) { //si la mesa no esta disponible
                throw new CheckError(CheckError.ERROR_MESA_NO_DISPONIBLE);
            }
            ps.setInt(1, id_usuario);
            ps.setInt(2, num_comensales);
            ps.execute();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);// ERROR GENERAL
        }
    }
    public boolean usuarioExists(int id_usuario) throws CheckError {
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_USUARIO);
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }

    public boolean mesaDisponible(int num_comensales) throws CheckError {

        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_MESAS);
            ps.setInt(1, num_comensales);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) <= 0;
            }
            return false;
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
    }
}
