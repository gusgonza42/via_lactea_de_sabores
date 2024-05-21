package abp.project.mesapp.controller;
import abp.project.mesapp.dao.UsuarioDao;
import abp.project.mesapp.model.*;
import abp.project.mesapp.service.MesappService;
import abp.project.mesapp.database.DataBaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class MesappController {

    @Autowired
    private MesappService mesappService;
    //INICIO MAIN
    //@GetMapping("/main")
    //public ResponseEntity<?> Welcome() throws SQLException {
        //return ResponseEntity.ok("Hello World!");
        //DataBaseConnection db = new DataBaseConnection();
        //db.init();

        //MenuFunciones menu = new MenuFunciones();
        //menu.menuFunciones(db);
        // DESCONECTAR
        //db.desconectar();
        //return ResponseEntity.ok().build();
    //}
    @Autowired //solo una vez
    private UsuarioDao usuarioDao;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {
        System.out.println("Datos del cliente");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Contraseña: " +user.getContrasena());
        try {
            boolean loggedIn = usuarioDao.login(user.getEmail(), user.getContrasena());
            if (loggedIn) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("CredencialesIncorrectas");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Para fines de depuración
            return ResponseEntity.status(500).body("Hay algo que no pinta bn pa");
        }
    }

    @GetMapping("/mesapp/{id}")
    public ResponseEntity<?> getMesapp(@PathVariable("id") int option) {
        return mesappService.getMesapp(option);
    }

    /*
     * TODO:
     *  1. Duplica este trozo de código
     *  2. Reemplaza "XXX" por el objeto java (Mesa, Camarero, Usuario, ...)
     *  3. Reemplaza "Body body" por el objeto (el mismo que el paso 2) Mesa mesa, Camarero camarero, ...
     *  4. Pon tu clase Mesa, Camarero, Usuario en el package de Service para que se pueda importar
     *  5. Importalo
     *  6. Duplica el método "postXXX" dentro de "MesappService" reemplazando "XXX" igual que el paso 2
     *  7. Ya puedes llamar a la url: "http://localhost:8081/mesapp/XXX (donde "XXX" es el mismo que el paso 2
     * @PostMapping("/mesapp/Usuario")
    public ResponseEntity postXXXX(@PathVariable("id") int option,
                                     @RequestBody Body body) {
        return mesappService.postXXXX(option, body);
    }
     */

    //MAPEO USUARIO
    @PostMapping("/mesapp/Usuario")
    public ResponseEntity postUsuario(@PathVariable("id") int option,
                                     @RequestBody Usuario usuario) {
        return mesappService.postUsuario(option, usuario);
    }
    //MAPEO CHEF
    @PostMapping("/mesapp/Chef")
    public ResponseEntity postChef(@PathVariable("id") int option,
                                  @RequestBody Chef chef) {
        return mesappService.postChef(option, chef);
    }
    //MAPEO CAMARARERO
    @PostMapping("/mesapp/Camarero")
    public ResponseEntity postCamarero(@PathVariable("id") int option,
                                  @RequestBody Camarero camarero) {
        return mesappService.postCamarero(option, camarero);
    }
    //MAPEO MESA
    @PostMapping("/mesapp/Mesa")
    public ResponseEntity postMesa(@PathVariable("id") int option,
                                  @RequestBody Mesa mesa) {
        return mesappService.postMesa(option, mesa);
    }
    //MAPEO CLIENTE
    @PostMapping("/mesapp/Cliente")
    public ResponseEntity postCliente(@PathVariable("id") int option,
                                  @RequestBody Cliente cliente) {
        return mesappService.postCliente(option, cliente);
    }
    //MAPEO COMANDA
    @PostMapping("/mesapp/Comanda")
    public ResponseEntity postComanda(@PathVariable("id") int option,
                                  @RequestBody Comanda comanda) {
        return mesappService.postComanda(option, comanda);
    }
    //MAPEO MENU
    @PostMapping("/mesapp/Menu")
    public ResponseEntity postMenu(@PathVariable("id") int option,
                                  @RequestBody Menu menu) {
        return mesappService.postMenu(option, menu);
    }
    //MAPEO PLATO
    @PostMapping("/mesapp/Plato")
    public ResponseEntity postPlato(@PathVariable("id") int option,
                                  @RequestBody Plato plato) {
        return mesappService.postPlato(option, plato);
    }
    //MAPEO PRODUCTO
    @PostMapping("/mesapp/Produtcot")
    public ResponseEntity postProducto(@PathVariable("id") int option,
                                  @RequestBody Producto producto) {
        return mesappService.postProducto(option, producto);
    }


}
