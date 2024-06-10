package abp.project.mesapp.dao;

import abp.project.mesapp.model.Camarero;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamareroDao {
    public List<Camarero> selectAllCamareros() throws CheckError {
        List<Camarero> camarerosList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_CAMAREROS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Camarero camarero = new Camarero();
                camarero.setId_usuario(rs.getInt("id_usuario"));
                camarero.setSalario(rs.getInt("salario"));
                camarero.setFecha_contratacion(rs.getDate("fecha_contratacion"));
                camarero.setDisponibilidad(rs.getBoolean("disponibilidad"));
                camarerosList.add(camarero);
            }

        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_LISTAR_PERSONAL);
        }
        return camarerosList;
    }
}
