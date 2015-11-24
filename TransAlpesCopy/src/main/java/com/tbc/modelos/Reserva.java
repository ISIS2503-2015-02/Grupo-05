package com.tbc.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public  abstract class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String estado;

    public long fecha;
    
    public String tipo;
    
    public long vehiculo_id;
    
    public long cliente_id;

    public long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(long cliente_id) {
        this.cliente_id = cliente_id;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }   

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(long vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public Reserva()
    {
        super();
    }
 
    
}
