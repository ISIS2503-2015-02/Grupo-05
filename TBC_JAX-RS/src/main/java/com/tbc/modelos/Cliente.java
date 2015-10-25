package com.tbc.modelos;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String telefono;

    public String tarjetaBancaria;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    public List<Reserva> reservas;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
   // public List<Prestamo> prestamosVcubs;

    //public static Model.Finder<Long,Cliente> find = new Model.Finder<Long, Cliente>(Cliente.class);

    public Cliente()
    {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(String tarjetaBancaria) {
        this.tarjetaBancaria = tarjetaBancaria;
    }
}
