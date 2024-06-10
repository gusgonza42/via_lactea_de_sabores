package abp.project.mesapp.dao;

import abp.project.mesapp.database.DataBaseConnection;
import abp.project.mesapp.model.Plato;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlatoDao {
    public static ArrayList<Plato> getPlatos() throws CheckError {
        ArrayList<Plato> platos = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = connection.prepareStatement(Constantes.VIEW_PLATOS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Plato plato = new Plato();
                plato.setId_plato(rs.getInt("id"));
                plato.setNombre_plato(rs.getString("nombre"));
                plato.setDescripcion(rs.getString("descripcion"));
                platos.add(plato);
            }

        }catch (Exception e){
            throw new CheckError(CheckError.ERROR_GESTIONAR_PLATO);
        }
        return platos;
    }

    public boolean deletePlato(int id) {
        try {
            Connection connection = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = connection.prepareStatement(Constantes.DELETE_PLATO);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Plato> selectAllPlatos() throws CheckError {
        List<Plato> platosList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = connection.prepareStatement(Constantes.SELECT_ALL_PLATOS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plato plato = new Plato();
                plato.setId_plato(rs.getInt("id_plato"));
                plato.setNombre_plato(rs.getString("nombre_plato"));
                plato.setDescripcion(rs.getString("descripcion"));
                plato.setId_chef(rs.getInt("id_chef"));
                platosList.add(plato);
            }
            connection.close();

        } catch (Exception e) {
            throw new CheckError(CheckError.ERROR_GESTIONAR_PLATO);
        }
        return platosList;
    }
}
