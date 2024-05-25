package abp.project.mesapp.service;

import abp.project.mesapp.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MesappService {

    public ResponseEntity getMesapp(int option) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se manda la lista de objetos obtenida
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
     * TODO:
     *  1. Duplica este trozo de código
     *  2. Reemplaza "XXX" por el objeto java (Mesa, Camarero, Usuario, ...) usado en el "MesappController"
     * EJEMPLO
     * public ResponseEntity postXXX(int option, Usuario body) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
     */



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
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postMenu(int option, Menu menu) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postComanda(int option, Comanda comanda) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postCliente(int option, Cliente cliente) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postMesa(int option, Mesa mesa) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postCamarero(int option, Camarero camarero) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postChef(int option, Chef chef) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity postUsuario(int option, Usuario usuario) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
