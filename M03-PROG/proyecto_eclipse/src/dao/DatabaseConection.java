package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConection {
	public static final String USER = "root";
    public static final String PASS = "root";
    public static final String SCHEMA = "vialacteadesabores";
    public static final String BBDD = "jdbc:mysql://localhost:3306/";
    public static final String SELECT_TEST = "SELECT id, text FROM users";
    public static final String SELECT_TEST_WITH_WHERE = "SELECT id, text FROM users WHERE text = ?";
    public static final String UPDATE_TEST = "UPDATE users SET text = ? WHERE id = ?";
    public static final String INSERT_TEST = "INSERT INTO users (id, text) VALUES (?, ?)";
    public static final String DELETE_TEST = "DELETE FROM users WHERE id = ?";
    static Connection conn;

    public void init
    () throws SQLException {
        //conectar


        conn = DriverManager.getConnection(BBDD + SCHEMA, USER, PASS);
        for (Test t : select("SELECT:")) {
            System.out.println(t);
        }
        //update el id=1 con text hola
        update(new Test(1, "hola"));
        selectWithWhere(0);
        //insert
        insert(new Test("adios"));

        //delete
        delete(1);

        //deconextar
        conn.close();

    }

    //SELECT
    private  ArrayList<Test> select(String s) throws SQLException {
        ArrayList<Test> tests = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(SELECT_TEST);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String text = rs.getString("text");
            int id = rs.getInt("id");
            Test test = new Test(id, text);
            tests.add(test);
            //System.out.println(rs.getString("name"));
        }
        return tests;
    }

    private ArrayList<Test> selectWithWhere(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(SELECT_TEST_WITH_WHERE);
        ArrayList<Test> tests = new ArrayList<>();
        ps.setInt(1, id);
        //ps.setString(1, "HOLA"); //filtro de texto importante en funcion de sets usados
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Test test = new Test(rs.getInt("id"), rs.getString("text"));
            tests.add(test);
            //System.out.println(rs.getString("name"));
        }
        return tests;
    }

    //update
    public  void update(Test test) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_TEST);
        int result = ps.executeUpdate();
        ps.setString(1, test.getText());
        ps.setInt(2, test.getId());
        if (result == 1) {
            System.out.println("Update correcto");
        } else {
            System.out.println("Update incorrecto");
        }
    }

    //INSERT
    public  void insert(Test test) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_TEST);
        ps.setString(2, test.getText());
        System.out.println(ps.toString());
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("Insert correcto");
        } else {
            System.out.println("Insert incorrecto");
        }
    }

    //delete
    public  void delete(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_TEST);
        ps.setInt(1, id);
        int result = ps.executeUpdate();
        if (result == 1) {
            System.out.println("Delete correcto");
        } else {
            System.out.println("Delete incorrecto");
        }
    }

}


