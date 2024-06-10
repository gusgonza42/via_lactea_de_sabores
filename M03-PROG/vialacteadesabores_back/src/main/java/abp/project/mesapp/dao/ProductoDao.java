package abp.project.mesapp.dao;

import abp.project.mesapp.model.Producto;
import abp.project.mesapp.util.CheckError;
import abp.project.mesapp.util.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    public List<Producto> selectAllProductos() throws CheckError {
        List<Producto> productosList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(Constantes.BBDD + Constantes.SCHEMA, Constantes.USER, Constantes.PASS);
            PreparedStatement ps = connection.prepareStatement(Constantes.SELECT_ALL_PRODUCTOS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdproducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setProveedor(rs.getString("proveedor"));
                productosList.add(producto);
            }
        } catch (Exception e) {
            throw new CheckError(CheckError.ERROR_CONNECT_BBDD);
        }
        return productosList;
    }
}
