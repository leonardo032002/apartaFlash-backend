/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aptos.aptos.repositories;

import com.aptos.aptos.model.Cliente;
import com.aptos.aptos.model.Recibo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo
 */
@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    @Query("SELECT SUM(r.precio) FROM Recibo r")
    Long calcularTotalGenerado();

    @Query("SELECT r FROM Recibo r WHERE r.pagado = false")
    List<Recibo> findPendingRecibos();

 

}
