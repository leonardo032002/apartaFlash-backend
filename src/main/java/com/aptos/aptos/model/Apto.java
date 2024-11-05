/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Leonardo
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Apto {

    @OneToMany(mappedBy = "apto")
    private List<Mantenimiento> mantenimientos;

    @OneToMany(mappedBy = "apto")
    private List<Cliente> clientes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String edificio;
    private int codigo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    //para usar solo la fecha
    //asociar recibo con cliente y cliente con apto para que no se vea tan abultado
    public boolean isDisponible() {
        for (Cliente cliente : clientes) {
            for (Recibo recibo : cliente.getRecibos()) {
                if (!recibo.isVencido()) {
                    return false; // Si hay un recibo no vencido, el apto no está disponible
                }
            }
        }
        return true; // Si no hay clientes o todos los recibos están vencidos, el apto está disponible
    }
    // 
}
