package com.tbc.modelos;

//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;



@Entity
public class Cliente implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String email;
    
    private String name;

    private String telefono;

    private String tarjetaBancaria;
    
    private String password;
    
    @Transient
    public List<Reserva> reservas;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    
    
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
