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

    public int telefono;

    public String tarjetaBancaria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    public List<Reserva> reservasMobibus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    public List<Prestamo> prestamosVcubs;

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(String tarjetaBancaria) {
        this.tarjetaBancaria = tarjetaBancaria;
    }

    public List<Reserva> getReservasMobibus() {
        return reservasMobibus;
    }

    public void setReservasMobibus(List<Reserva> reservasMobibus) {
        this.reservasMobibus = reservasMobibus;
    }

    public List<Prestamo> getPrestamosVcubs() {
        return prestamosVcubs;
    }

    public void setPrestamosVcubs(List<Prestamo> prestamosVcubs) {
        this.prestamosVcubs = prestamosVcubs;
    }
    
    public final void agregarReserva(Reserva reserva)
    {
        if(reservasMobibus==null)
           reservasMobibus = new ArrayList<Reserva>();
        reservasMobibus.add(reserva);
    }

    public final void agregarPrestamo(Prestamo prestamo)
    {
        if(prestamosVcubs==null)
            prestamosVcubs = new ArrayList<Prestamo>();
        prestamosVcubs.add(prestamo);
    }

    public final void eliminarReserva(Long idReserva)
    {
        for(Reserva r: reservasMobibus)
            if(r.id == idReserva)
            {
                reservasMobibus.remove(r);
                break;
            }
            
       // reservasMobibus = new ArrayList<Prestamo>();
    }

    public final void actualizarReserva(Reserva reserva)
    {
        for(int i=0;i<reservasMobibus.size();i++)
        {
            if(reservasMobibus.get(i).id==reserva.id) {
                reservasMobibus.set(i,reserva);
            }
        }
    }

    public final void actualizarPrestamo(Prestamo prestamo)
    {
        for(int i=0;i<prestamosVcubs.size();i++)
        {
            if(prestamosVcubs.get(i).id==prestamo.id) {
                    prestamosVcubs.set(i,prestamo);
            }
        }
    }

}
