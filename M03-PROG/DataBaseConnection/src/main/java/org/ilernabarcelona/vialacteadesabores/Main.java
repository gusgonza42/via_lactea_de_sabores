package org.ilernabarcelona.vialacteadesabores;

import org.ilernabarcelona.checkExceptions.CheckError;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, CheckError {
        DataBaseConnection db = new DataBaseConnection();
        db.init();
        MenuFunciones menu = new MenuFunciones();
        menu.menuFunciones(db);
        // DESCONECTAR
        db.desconectar();
    }
}