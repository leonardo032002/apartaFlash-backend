/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.model.Mantenimiento;
import com.aptos.aptos.repositories.MantenimientoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class MantenimientoService {
    
    @Autowired
    private MantenimientoRepository mantenimientoRepo;
    
    public List<Mantenimiento> getAll(){
        return mantenimientoRepo.findAll();
    }
    
    public Mantenimiento getById(Long id){
        return mantenimientoRepo.getReferenceById(id);
    }
    
    public void save(Mantenimiento m){
        mantenimientoRepo.save(m);
    }
    
    
    public void delete(Long id){
        mantenimientoRepo.deleteById(id);
    }
    
    
}
