/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.model.Mantenimiento;
import com.aptos.aptos.services.MantenimientoService;
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
@RequestMapping("/Mantenimiento")
@CrossOrigin(origins = "http://localhost:3000")
public class MantenimientoController {
    
    @Autowired
    private MantenimientoService mantenimientoService;
    
    @GetMapping("/")
    public ResponseEntity<List<Mantenimiento>> queryAll(){
        List<Mantenimiento> prs = mantenimientoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(prs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento>quertById(@PathVariable Long Id){
        Mantenimiento m = mantenimientoService.getById(Id);
        return ResponseEntity.ok().body(m);
        
    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> saveMantenimiento(@RequestBody Mantenimiento mantenimiento){
        mantenimientoService.save(mantenimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable Long id){
        mantenimientoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
