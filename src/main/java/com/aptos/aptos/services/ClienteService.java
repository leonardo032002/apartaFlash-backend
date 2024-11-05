/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.services;

import com.aptos.aptos.exception.CedulaExisteException;
import com.aptos.aptos.model.Apto;
import com.aptos.aptos.model.Cliente;
import com.aptos.aptos.repositories.AptoRepository;
import com.aptos.aptos.repositories.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leonardo
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.getReferenceById(id);
    }

    public ResponseEntity<String> save(Cliente c) {
        // Verificar si la cédula ya existe
        boolean cedulaExistente = clienteRepository.existsByCedula(c.getCedula());

        if (cedulaExistente) {
            System.out.println("cedula existe");
            // La cédula ya existe, maneja la situación de acuerdo a tus necesidades
           throw new CedulaExisteException("La cedula existe");

        } 
            // La cédula no existe, puedes guardar el cliente
            clienteRepository.save(c);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente guardado exitosamente.");
        

    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> getCLientesAptos() {
        return clienteRepository.findClientesAsociadosAptos();
    }

    @Autowired
    private AptoRepository aptoRepository; // Asumo que tienes un repositorio para la entidad Apto

    public Cliente actualizarCliente(Long clienteId, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        clienteExistente.setCedula(clienteActualizado.getCedula());

        // Actualizar la relación con el apto (JPA se encargará de manejar la relación)
        clienteExistente.setApto(clienteActualizado.getApto());

        return clienteRepository.save(clienteExistente);
    }

//    public Cliente actualizarCliente(Long clienteId, Cliente clienteActualizado) {
//        Cliente clienteExistente = clienteRepository.findById(clienteId)
//                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));
//
//        // Actualizar los campos del cliente existente con los valores proporcionados en clienteActualizado
//        clienteExistente.setNombre(clienteActualizado.getNombre());
//        clienteExistente.setApellido(clienteActualizado.getApellido());
//        clienteExistente.setCedula(clienteActualizado.getCedula());
//
//        // Actualizar la relación con el apto (si es necesario)
//        Apto aptoExistente = clienteExistente.getApto();
//        Apto aptoActualizado = clienteActualizado.getApto();
//
//        if (aptoActualizado != null) {
//            // Si el cliente actualizado tiene un apto asociado, verifica si es diferente al actual
//            if (aptoExistente == null || !aptoExistente.equals(aptoActualizado)) {
//                // Si es diferente, asocia el nuevo apto al cliente
//                clienteExistente.setApto(aptoActualizado);
//
//                // Si el antiguo apto existía, actualiza su lista de clientes asociados
//                if (aptoExistente != null) {
//                    aptoExistente.getClientes().remove(clienteExistente);
//                    aptoRepository.save(aptoExistente);
//                }
//
//                // Asocia el cliente al nuevo apto y guarda el apto
//                aptoActualizado.getClientes().add(clienteExistente);
//                aptoRepository.save(aptoActualizado);
//            }
//        } else {
//            // Si el cliente actualizado no tiene apto, y el cliente existente tiene un apto,
//            // elimina la asociación y actualiza el apto existente
//            if (aptoExistente != null) {
//                clienteExistente.setApto(null);
//                aptoExistente.getClientes().remove(clienteExistente);
//                aptoRepository.save(aptoExistente);
//            }
//        }
//
//        // Guarda el cliente actualizado en la base de datos
//        return clienteRepository.save(clienteExistente);
//    }
    public Cliente obtenerClienteConMasRecibos() {
        return clienteRepository.obtenerClienteConMasRecibos();
    }
    
    
    public List<Object[]> findClientesOrderByTotalPagadoAsc() {
        return clienteRepository.findClientesOrderByTotalPagadoAsc();
        
    }
    
        public List<Object[]> buscarClienteConAptoYRecibosPorNombre(String nombreCliente) {
        return clienteRepository.buscarClienteConAptoYRecibosPorNombre(nombreCliente);
    }
            public List<Object[]> buscarClientePorCedula(Integer cedula) {
        return clienteRepository.buscarPorCedula(cedula);
    }
}
