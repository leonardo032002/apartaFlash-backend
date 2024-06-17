/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.model.Administrador;
import com.aptos.aptos.services.AdministradorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministradorController {
    
    @Autowired
    private AdministradorService administradorService;
    
    @GetMapping("/")
    public ResponseEntity<List<Administrador>> queryAll(){
        List<Administrador> prs = administradorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(prs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Administrador>queryById(@PathVariable Long id){
        Administrador a = administradorService.getById(id);
        return ResponseEntity.ok().body(a);
    }
    
    @PostMapping("/")
    public ResponseEntity<Boolean> saveProyecto(@RequestBody Administrador administrador){
        administradorService.save(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable long id){
        administradorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
