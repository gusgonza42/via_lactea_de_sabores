package abp.project.mesapp.dao;

import abp.project.mesapp.model.Comanda;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaDao {
    public List<Comanda> selectAllComandas() throws CheckError{
        List<Comanda> comandasList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_COMANDAS);
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 Comanda comanda = new Comanda();
                 comanda.setId_comanda(rs.getInt("id_comanda"));
                 comanda.setFecha(rs.getDate("fecha"));
                 comanda.setPagado(rs.getBoolean("pagado"));
                 comanda.setNum_mesa(rs.getInt("num_mesa"));
                 comandasList.add(comanda);
             }
        }catch (SQLException e){
            throw new CheckError(CheckError.ERROR_BBDD_FAIL);
        }
        return comandasList;
    }
}
