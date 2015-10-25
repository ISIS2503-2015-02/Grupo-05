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
    public int idSalida, idLlegada;
    
    public Prestamo()
    {
        super();
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public int getIdLlegada() {
        return idLlegada;
    }

    public void setIdLlegada(int idLlegada) {
        this.idLlegada = idLlegada;
    }
    
    
    

    
    
}
