package abp.project.mesapp.dao;

import abp.project.mesapp.model.Menu;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {
    public List<Menu> selectAllMenus() throws CheckError {
        List<Menu> menuList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = connection.prepareStatement(Constantes.SELECT_ALL_MENUS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId_menu(rs.getInt("id_menu"));
                menu.setNombre(rs.getString("nombre_menu"));
                menu.setDescripcion(rs.getString("descripcion"));
                menu.setPrecio(rs.getDouble("precio"));
                menuList.add(menu);
            }
        } catch (Exception e) {
            throw new CheckError(CheckError.ERROR_LISTAR_MENUS);
        }
        return menuList;

    }
}
