/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.model.Recibo;
import com.aptos.aptos.repositories.ReciboRepository;
import com.aptos.aptos.services.ReciboService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
@RequestMapping("/Recibo")
@CrossOrigin(origins = "*")
public class ReciboController {
    
    @Autowired
    private ReciboService reciboService;
    
    @Autowired
    private ReciboRepository reciboRepository;
    
    @GetMapping("/")
    public ResponseEntity<List<Recibo>> queryAll(){
        List<Recibo> prs = reciboService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(prs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Recibo>queryById(@PathVariable Long id){
        Recibo r = reciboService.getById(id);
        return ResponseEntity.ok().body(r);
    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> saveProyecto(@RequestBody Recibo recibo){
        reciboService.save(recibo);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable long id){
       reciboService.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
//      @GetMapping("/recibo/{id}")
//    public String obtenerMensajeVencimiento(@PathVariable Long id) {
//        Recibo recibo = reciboRepository.findById(id).orElse(null);
//        if (recibo != null) {
//            String mensajeVencimiento = recibo.getMensajeVencimiento();
//            return mensajeVencimiento;
//        } else {
//            return "Recibo no encontrado";
//        }
//    }
//    
    @GetMapping("/vencido/{id}")
    public String obtenerPago(@PathVariable Long id){
        Recibo recibo = reciboRepository.findById(id).orElse(null);
        if (recibo !=null) {
            String mensajePago = recibo.reciboPagado();
            return mensajePago;
        }else{
            return "recibo no pago";
        }
    }
    
       @GetMapping("/totalGenerado")
    public Long obtenerTotalGenerado() {
        return reciboRepository.calcularTotalGenerado();
    }
}
