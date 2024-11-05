/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.model.Cliente;
import com.aptos.aptos.services.ClienteService;
import java.util.List;
import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Leonardo
 */
@RestController
@RequestMapping("/Clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> queryAll() {
        List<Cliente> prs = clienteService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(prs);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> queryById(@PathVariable Long id) {
        Cliente a = clienteService.getById(id);
        return ResponseEntity.ok().body(a);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> saveCliente(@RequestBody Cliente cliente) {
        clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable long id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/aptos")
    public List<Cliente> getClientesAsociadosAptos() {
        return clienteService.getCLientesAptos();
    }

    @PutMapping("/{clienteId}")

    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente clienteActualizado) {
        try {
            clienteActualizado = clienteService.actualizarCliente(clienteId, clienteActualizado);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Cliente no encontrado
        }
    }

 
    
       @GetMapping("/mejoresClientes")
    public List<Object[]> getMejoresClientes() {
        return clienteService.findClientesOrderByTotalPagadoAsc();
    }
    
        @GetMapping("/buscar")
    public ResponseEntity<List<Object[]>> buscarClienteConAptoYRecibosPorNombre(@RequestParam String nombreCliente) {
        List<Object[]> resultados = clienteService.buscarClienteConAptoYRecibosPorNombre(nombreCliente);
        return ResponseEntity.ok(resultados);
    }
     @GetMapping("/cedula")
    public List<Object[]> buscarPorCedula(@RequestParam Integer cedula) {
        return clienteService.buscarClientePorCedula(cedula);
    }
}
