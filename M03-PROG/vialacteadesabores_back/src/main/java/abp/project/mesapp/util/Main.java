package abp.project.mesapp.util;

import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.model.MenuFunciones;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnection db = new DataBaseConnection();
        db.init();
        MenuFunciones menu = new MenuFunciones();
        menu.menuFunciones(db);
        // DESCONECTAR
        db.desconectar();
    }
}

