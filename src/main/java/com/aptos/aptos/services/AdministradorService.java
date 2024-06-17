/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.model.Administrador;
import com.aptos.aptos.repositories.AdministradorRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> getAll() {
        return administradorRepository.findAll();

    }
    
    public Administrador getById(Long id){
        return administradorRepository.getReferenceById(id);
    }
    
    public void save (Administrador a){
        administradorRepository.save(a);
    }
    
    public void delete (Long id){
        administradorRepository.deleteById(id);
    }


}
