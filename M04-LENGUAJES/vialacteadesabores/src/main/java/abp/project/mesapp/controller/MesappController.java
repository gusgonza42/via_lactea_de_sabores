package abp.project.mesapp.controller;

import abp.project.mesapp.service.MesappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MesappController {

    @Autowired
    private MesappService mesappService;

    @GetMapping("/helloWorld")
    public ResponseEntity Welcome() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/mesapp/{id}")
    public ResponseEntity getMesapp(@PathVariable("id") int option) {
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
     */
    @PostMapping("/mesapp/XXX")
    public ResponseEntity postXXX(@PathVariable("id") int option,
                                     @RequestBody Body body) {
        return mesappService.postXXX(option, body);
    }

}
