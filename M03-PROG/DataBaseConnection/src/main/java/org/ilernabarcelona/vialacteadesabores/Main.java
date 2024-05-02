package org.ilernabarcelona.vialacteadesabores;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnection db = new DataBaseConnection();
        db.init();
        mostrarMenu();

    }
    public static void mostrarMenu(){
        System.out.println("|---GESTOR BASE DE DATOS---|");
        System.out.println("1 HOLA");
        System.out.println("2 HECTOR");
        System.out.println("3 COMPRAME");
        System.out.println("4 UN");
        System.out.println("0 LIBRO");
    }
}