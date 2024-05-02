package abp.project.mesapp.service;

import abp.project.mesapp.controller.Body;
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
     */
    public ResponseEntity postXXX(int option, Body body) {
        try {


            //REtornamos la respuesta de OK. Dentro del body() se pueden mandar objetos Java o Mensajes String
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            //Retornamos el error generado por la excepción Personalizada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
