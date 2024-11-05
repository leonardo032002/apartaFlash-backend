/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.model.Cliente;
import com.aptos.aptos.model.Recibo;
import com.aptos.aptos.repositories.ReciboRepository;
import java.util.List;
import java.util.Optional;
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

    public List<Recibo> getAll() {
        return reciboRepository.findAll();
    }

    public Recibo getById(Long id) {
        return reciboRepository.getReferenceById(id);
    }

    public void save(Recibo r) {
        reciboRepository.save(r);
    }

    public void delete(Long id) {
        reciboRepository.deleteById(id);
    }

    public void totalGenerado() {
        Long totalGenerado = reciboRepository.calcularTotalGenerado();
        System.out.println("Cantidad total generada en los recibos: " + totalGenerado);
    }

    public Recibo actualizarRecibo(Long id, Recibo reciboActualizado) {
        Optional<Recibo> reciboOptional = reciboRepository.findById(id);

        if (reciboOptional.isPresent()) {
            Recibo reciboExistente = reciboOptional.get();
            reciboExistente.setFechaGenerado(reciboActualizado.getFechaGenerado());
            reciboExistente.setFechaVencimiento(reciboActualizado.getFechaVencimiento());
            reciboExistente.setPagado(reciboActualizado.getPagado());
            reciboExistente.setPrecio(reciboActualizado.getPrecio());
            reciboExistente.setCliente(reciboActualizado.getCliente());
           

            return reciboRepository.save(reciboExistente);
        } else {
            throw new RuntimeException("Recibo no encontrado con el ID: " + id);
        }
    }

    public List<Recibo> getPendingRecibos() {
        return reciboRepository.findPendingRecibos();
    }
  
  

   

      

}
