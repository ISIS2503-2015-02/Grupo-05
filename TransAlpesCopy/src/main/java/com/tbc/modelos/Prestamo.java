/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.modelos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author ss.salazar10
 */
@Entity
@DiscriminatorValue("P")
public class Prestamo extends Reserva
{
    public long idSalida, idLlegada;
    
    public Prestamo()
    {
        super();
        idLlegada = -1;
        estado="Activa";
        fecha = System.currentTimeMillis();
        tipo= "Prestamo";
    }

    public long getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(long idSalida) {
        this.idSalida = idSalida;
    }

    public long getIdLlegada() {
        return idLlegada;
    }

    public void setIdLlegada(long idLlegada) {
        this.idLlegada = idLlegada;
    }
    
    
    

    
    
}
