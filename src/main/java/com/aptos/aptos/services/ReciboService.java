/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.model.Cliente;
import com.aptos.aptos.model.Recibo;
import com.aptos.aptos.repositories.ReciboRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class ReciboService {
    
    @Autowired
    private ReciboRepository reciboRepository;
    
  
    
    public List<Recibo> getAll(){
        return reciboRepository.findAll();
    }
    
    public Recibo getById(Long id){
        return reciboRepository.getReferenceById(id);
    }
    
    public void save(Recibo r){
        reciboRepository.save(r);
    }
    
    public void delete (Long id){
        reciboRepository.deleteById(id);
    }

    public void totalGenerado() {
        Long totalGenerado = reciboRepository.calcularTotalGenerado();
        System.out.println("Cantidad total generada en los recibos: " + totalGenerado);
    }
    
    
}
