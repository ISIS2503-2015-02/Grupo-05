package com.tbc.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public  abstract class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String estado;

    public long fecha;
    
    public String tipo;

    @ManyToOne
    @JsonIgnore
    public Cliente cliente;
    
    @ManyToOne
    @JsonIgnore
    public Vehiculo vehiculo;
    
    
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
 



    public Reserva()
    {
        super();
    }
 
    
}
