/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.controllers;

import com.aptos.aptos.dto.AptoDto;
import com.aptos.aptos.model.Apto;
import com.aptos.aptos.model.Recibo;
import com.aptos.aptos.repositories.AptoRepository;
import com.aptos.aptos.services.AptoService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/Aptos")
@CrossOrigin(origins = "*")
public class AptoController {

    @Autowired
    private AptoService aptoService;

    @Autowired
    private AptoRepository aptoRepository;

    /**
     * Controlador para obetener lista de aptos y mostrarlos
     *
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Apto>> queryAll() {
        List<Apto> prs = aptoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(prs);
    }

    /**
     * Controlador para obetener aptos y mostrarlos por id
     *
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Apto> queryById(@PathVariable Long Id) {
        Apto a = aptoService.getById(Id);
        return ResponseEntity.ok().body(a);
    }

    /**
     * Controlador para guardar aptos
     *
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Boolean> saveProyecto(@RequestBody Apto apto) {
        aptoService.save(apto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Boolean.TRUE);
    }

    /**
     * Controlador para borrar aptos por id
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
        aptoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apto> actualizarApto(@PathVariable long id, @RequestBody Apto aptoActualizado) {
        Apto apto = aptoService.actualizarApto(id, aptoActualizado);
        if (apto != null) {
            return ResponseEntity.ok(apto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/alquileres")
    public ResponseEntity<Map<String, Object>> getAlquileresPorAptoYFecha(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        Apto apto = aptoService.getById(id);

        if (apto == null) {
            return ResponseEntity.notFound().build();
        }

        List<Recibo> recibos = apto.getClientes().stream()
                .flatMap(cliente -> cliente.getRecibos().stream())
                .filter(recibo -> !recibo.getFechaGenerado().before(startDate)
                && !recibo.getFechaGenerado().after(endDate))
                .collect(Collectors.toList());

        int totalAlquileres = recibos.size();
        int totalGenerado = recibos.stream().mapToInt(Recibo::getPrecio).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("nombreApto", apto.getNombre());
        response.put("totalAlquileres", totalAlquileres);
        response.put("totalGenerado", totalGenerado);

        return ResponseEntity.ok(response);
    }

}

/**
 * Controlador No utilizado
 *
 * @return
 */
