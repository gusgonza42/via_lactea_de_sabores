package abp.project.mesapp.dao;

import abp.project.mesapp.model.Chef;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChefDao {
    public List<Chef> selectAllChefs() throws CheckError {
        List<Chef> chefList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = conn.prepareStatement(Constantes.SELECT_ALL_CHEFS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chef chef = new Chef();
                chef.setId_usuario(rs.getInt("id_usuario"));
                chef.setSalario(rs.getInt("salario"));
                chef.setFechaContratacion(rs.getDate("fecha_contratacion"));
                chef.setDisponible(rs.getBoolean("disponible"));
                chefList.add(chef);
            }
        } catch (SQLException e) {
            throw new CheckError(CheckError.ERROR_LISTAR_PERSONAL);
        }
        return chefList;
    }
}
