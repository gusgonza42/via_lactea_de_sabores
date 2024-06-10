package abp.project.mesapp.dao;

import abp.project.mesapp.model.Cliente_Mesa;
import abp.project.mesapp.model.Mesa;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MesaDao {
    public int nuevaReserva(int id, int nb_comensales, Date reservaFecha) throws CheckError {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int reservaId = 0;
        CheckError closingResourcesError = null;

        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.SELECT_COUNT_FROM_MESAS);
            ps.setInt(1, nb_comensales);
            ps.setDate(2, reservaFecha);
            rs = ps.executeQuery();
            if (rs.next()) {
                int mesasDisponibles = rs.getInt(1);
                if (mesasDisponibles == 0) {
                    throw new CheckError(CheckError.ERROR_MESA_NO_DISPONIBLE_RESERVA);
                }
            } else
                throw new CheckError(CheckError.ERROR_BBDD_FAIL);

            //cerramos el rs y el ps de la query
            rs.close();
            ps.close();
            //asignamos la mesa libre con la fecha
            ps = conn.prepareStatement(Constantes.ASIGNAR_MESA_LIBRE);
            ps.setInt(1, id);
            ps.setInt(2, nb_comensales);
            ps.setDate(3, reservaFecha);
            rs = ps.executeQuery();

            if (rs.next())
                reservaId = rs.getInt("id");
            else
                throw new CheckError(CheckError.ERROR_ASIGNACION_MESA);

        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        } finally {
            try {
                if (rs != null) rs.close();

            } catch (SQLException e) {
                closingResourcesError = new CheckError(CheckError.ERROR_CERRAR_RECURSOS_BBDD);
            }
            try {
                if (ps != null) ps.close();

            } catch (SQLException e) {
                closingResourcesError = new CheckError(CheckError.ERROR_CERRAR_RECURSOS_BBDD);
            }
            try {
                if (conn != null) conn.close();

            } catch (SQLException e) {
                closingResourcesError = new CheckError(CheckError.ERROR_CERRAR_RECURSOS_BBDD);
            }
        }
        // Si hubo una excepción se lanza el throw personalizado
        if (closingResourcesError != null) {
            throw closingResourcesError;
        }
        return reservaId;
    }

    public static void deleteReserva(int idReserva) throws CheckError{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            ps = conn.prepareStatement(Constantes.DELETE_RESERVA);
            ps.setInt(1, idReserva);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new CheckError(CheckError.DELETE_RESERVA_ERROR);
        }
    }

    public List<Cliente_Mesa> getReservasHistorial(int idCliente) throws CheckError {
        List<Cliente_Mesa> historialReservas = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_RESERVAS_HISTORIAL);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente_Mesa reserva = new Cliente_Mesa();
                reserva.setId_usuario(rs.getInt("id_usuario"));
                reserva.setNum_mesa(rs.getInt("num_mesa"));
                reserva.setFecha_reserva(rs.getDate("fecha_reserva"));
                historialReservas.add(reserva);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch (SQLException e){
            throw new CheckError(CheckError.ERROR_LECTURA_DATOS);
        }
        return historialReservas;

    }


    public List<Mesa> selectAllMesas() throws CheckError{
        List<Mesa> mesasList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_MESAS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mesa mesa = new Mesa();
                mesa.setMesa(rs.getInt("num_mesa"));
                mesa.setId_camarero(rs.getInt("id_camarero"));
                mesa.setFecha_registro(rs.getDate("fecha_registro"));
                mesa.setDisponibilidad(rs.getBoolean("disponibilidad"));
                mesa.setMax_comensales(rs.getInt("max_comensales"));
                mesasList.add(mesa);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_LECTURA_DATOS);}
        return mesasList;
    }
}


