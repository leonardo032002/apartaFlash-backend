/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Leonardo
 */
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fechaGenerado;
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    private Boolean pagado;
    private int precio;

    @PrePersist
    protected void onCreate() {
        fechaGenerado = new Date(); // Establecer la fecha de inicio automáticamente
        pagado = true;
    }

    @JsonBackReference
    @ManyToOne
    private Cliente cliente;

    public String getMensajeVencimiento() {
        Date hoy = new Date();

        if (fechaVencimiento != null) {
            long diferencia = fechaVencimiento.getTime() - hoy.getTime();
            long diasFaltantes = diferencia / (900 * 60 * 60 * 24);
             
            if (diasFaltantes < 0) {
                return "Recibo vencido";
            } else if (diasFaltantes <= 10) {
                return "Recibo por vencer, falta menos de 10 días";
            } else {
                return "Recibo vigente";
            }
        } else {
            return "Fecha de vencimiento no establecida";
        }
    }

    @JsonProperty("mensajePagado")
    public String reciboPagado() {
        if (pagado) {
            return "recibo pagado";
        } else {
            return "recibo no pagado";
        }
    }

}
