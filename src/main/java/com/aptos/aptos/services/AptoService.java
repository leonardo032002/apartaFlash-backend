/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.dto.AptoDto;
import com.aptos.aptos.exception.MalformedProjectNameException;
import com.aptos.aptos.model.Apto;
import com.aptos.aptos.repositories.AptoRepository;
import java.util.List;
import java.util.stream.Collectors;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class AptoService {
    
   @Autowired
   private AptoRepository aptoRepository;
   
   public List <Apto> getAll(){
       return aptoRepository.findAll();
   }
   
   public Apto getById(Long id){
       return aptoRepository.getReferenceById(id);
   }
   
   public void save(Apto a){
       if (!a.getNombre().startsWith("apto")) {
           throw new MalformedProjectNameException("El nombre debe comenzar con 'apto'");
       }
       aptoRepository.save(a);
   }
   public void delete (Long id){
       aptoRepository.deleteById(id);
   }
   
      public List<AptoDto> obtenerPedidosNoPagados() {
        List<Apto> listApto = aptoRepository.findAll();
        return listApto.stream().map(apto -> new AptoDto(apto.getId(), apto.getNombre(), apto.getDescripcion())).collect(Collectors.toList());
    }
}
