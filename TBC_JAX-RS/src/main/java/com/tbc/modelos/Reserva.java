package com.tbc.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String estado;

    public Long fecha;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    public Cliente cliente;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    public Vehiculo vehiculo;

    public Reserva()
    {
        super();
    }
    
    
    //public static Model.Finder<Long,Reserva> find = new Model.Finder<Long, Reserva>(Reserva.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
