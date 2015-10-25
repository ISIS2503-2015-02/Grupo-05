package com.tbc.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Prestamo 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public long fecha;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JsonIgnore
    public Cliente cliente;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JsonIgnore
    public Vehiculo vehiculo;

    public Prestamo()
    {
        
    }
    
    
  //  public static Model.Finder<Long,Prestamo> find = new Model.Finder<Long, Prestamo>(Prestamo.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


}
