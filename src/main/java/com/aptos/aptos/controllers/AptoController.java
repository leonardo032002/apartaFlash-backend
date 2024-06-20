/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.model.Apto;
import com.aptos.aptos.services.AptoService;
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
@RequestMapping("/Aptos")
@CrossOrigin(origins = "*")
public class AptoController {

    @Autowired
    private AptoService aptoService;

    @GetMapping("/")
    public ResponseEntity<List<Apto>> queryAll() {
      List<Apto> prs = aptoService.getAll();
      return ResponseEntity.status(HttpStatus.OK).body(prs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Apto>queryById(@PathVariable Long Id){
      Apto a = aptoService.getById(Id);
      return ResponseEntity.ok().body(a);
    }
    
    
    @PostMapping("/")
    public ResponseEntity<Boolean> saveProyecto(@RequestBody Apto apto){
      aptoService.save(apto);
      return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable Long id){
        aptoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
