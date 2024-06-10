package abp.project.mesapp.dao;

import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.model.Cliente;
import abp.project.mesapp.model.Cliente_Mesa;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends DataBaseConnection {
    public List<Cliente_Mesa> selectAllCliente_Mesas() throws CheckError {
        //Seleccionar todos los clientes_mesas de la base de datos
        List<Cliente_Mesa> cliente_mesasList = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_MESAS_CLIENTES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Cliente_Mesa cliente_mesa = new Cliente_Mesa();
                cliente_mesa.setId_usuario(rs.getInt("id_cliente"));
                cliente_mesa.setNum_mesa(rs.getInt("num_mesa"));
                cliente_mesa.setFecha_reserva(rs.getDate("fecha_reserva"));
                cliente_mesasList.add(cliente_mesa);
            }
            conn.close();
        }catch (Exception e){
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
        return cliente_mesasList;
    }

    public List<Cliente> selectAllClientes() throws  CheckError{
        //Seleccionar todos los clientes de la base de datos
        List<Cliente> clientesList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_CLIENTES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_usuario(rs.getInt("id_usuario"));
                cliente.setComensales(rs.getInt("comensales"));
                cliente.setFecha_ultimaReserva(rs.getDate("fecha_ultimaReserva"));
                clientesList.add(cliente);
            }
            conn.close();
        } catch (Exception e) {
            throw new CheckError(CheckError.ERROR_LISTAR_CLIENTES);
        }
        return clientesList;
    }
}
