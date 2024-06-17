/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aptos.aptos.repositories;

import com.aptos.aptos.model.Apto;
import com.aptos.aptos.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leonardo
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c INNER JOIN c.apto a")
    List<Cliente> findClientesAsociadosAptos();
    
//      @Query("SELECT c, a.id FROM Cliente c INNER JOIN c.apto a")
//    List<Object[]> findClientesAsociadosAptos();
    
    boolean existsByCedula(int cedula);
    
    @Query("SELECT c, COUNT(r.id) AS cantidadRecibos, SUM(r.precio) AS valorTotalPagado " +
       "FROM Cliente c JOIN c.recibos r " +
       "GROUP BY c.id " +
       "ORDER BY cantidadRecibos DESC")
Cliente obtenerClienteConMasRecibos();

    @Query("SELECT c.id, c.nombre, c.cedula, SUM(r.precio) as totalPagado " +
           "FROM Cliente c " +
           "JOIN c.recibos r " +
           "WHERE r.pagado = true " +
           "GROUP BY c.id, c.nombre, c.cedula " +
           "ORDER BY totalPagado ASC")
    List<Object[]> findClientesOrderByTotalPagadoAsc();
    
  @Query("SELECT c.nombre AS nombreCliente, a.nombre AS nombreApartamento, r.fechaGenerado, r.fechaVencimiento, r.precio " +
       "FROM Cliente c " +
       "LEFT JOIN c.apto a " +
       "LEFT JOIN c.recibos r " +
       "WHERE c.nombre = :nombreCliente")
List<Object[]> buscarClienteConAptoYRecibosPorNombre(@Param("nombreCliente") String nombreCliente);




}
