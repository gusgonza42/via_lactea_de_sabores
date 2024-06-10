package abp.project.mesapp.service;

import abp.project.mesapp.controller.Body;
import abp.project.mesapp.controller.MesappController;
import abp.project.mesapp.dao.*;
import abp.project.mesapp.data.FileManager;
import abp.project.mesapp.model.*;
import abp.project.mesapp.util.CheckError;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesappService {


    private final UsuarioDao usuarioDao;

    public MesappService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public ResponseEntity getMesapp(int option) throws CheckError {
        try {
            MesaDao mesaDao = new MesaDao();
            ComandaDao comandaDao = new ComandaDao();
            ClienteDao clienteDao = new ClienteDao();
            CamareroDao camareroDao = new CamareroDao();
            ChefDao chefDao = new ChefDao();
            PlatoDao platoDao = new PlatoDao();
            MenuDao menuDao = new MenuDao();
            ProductoDao productoDao = new ProductoDao();

            switch (option) {
                case 1:
                    List<Mesa> mesas = mesaDao.selectAllMesas();
                    return ResponseEntity.ok().body(mesas);
                case 2:
                    List<Comanda> comandas = comandaDao.selectAllComandas();
                    return ResponseEntity.ok().body(comandas);
                case 3:
                    List<Cliente_Mesa> cliente_mesas = clienteDao.selectAllCliente_Mesas();
                    return ResponseEntity.ok().body(cliente_mesas);
                case 4:
                    List<Cliente> clientes = clienteDao.selectAllClientes();
                    return ResponseEntity.ok().body(clientes);
                case 5:
                    List<Camarero> camareros = camareroDao.selectAllCamareros();
                    return ResponseEntity.ok().body(camareros);
                case 6:
                    List<Chef> chefs = chefDao.selectAllChefs();
                    return ResponseEntity.ok().body(chefs);
                case 7:
                    List<Usuario> usuarios = usuarioDao.selectAllUsuarios();
                    return ResponseEntity.ok().body(usuarios);
                case 8:
                    List<Plato> platos = platoDao.selectAllPlatos();
                    return ResponseEntity.ok().body(platos);
                case 9:
                    List<Menu> menus = menuDao.selectAllMenus();
                    return ResponseEntity.ok().body(menus);
                case 10:
                    List<Producto> productos = productoDao.selectAllProductos();
                    return ResponseEntity.ok().body(productos);
                default:
                    return ResponseEntity.badRequest().body("Opción no válida");
            }
        } catch (CheckError e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity<Usuario> getPerfil(String email) {
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            Usuario usuario = usuarioDao.findByEmail(email);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build(); // Usuario no encontrado
            }
        } catch (CheckError e) {
            return ResponseEntity.badRequest().body(null); // Error al buscar el usuario
        }
    }

    /*
     * TODO:
     *  1. Duplica este trozo de código
     *  2. Reemplaza "XXX" por el objeto java (Mesa, Camarero, Usuario, ...) usado en el "MesappController"
     * EJEMPLO
     * /

     */
    public ResponseEntity postXXXX(int option, Body body) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postProducto(int option, Producto producto) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postPlato(int option, Plato plato) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(plato);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postMenu(int option, Menu menu) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(menu);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postComanda(int option, Comanda comanda) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(comanda);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postCliente(int option, Cliente cliente) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(cliente);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postMesa(int option, Mesa mesa) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(mesa);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postCamarero(int option, Camarero camarero) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(camarero);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postChef(int option, Chef chef) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(chef);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postUsuario(int option, Usuario usuario) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
