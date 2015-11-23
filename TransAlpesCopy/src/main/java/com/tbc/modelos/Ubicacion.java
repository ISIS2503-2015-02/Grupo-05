package com.tbc.modelos;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public long latitud;

    public long longitud;

    public long hora;

    public String estado;

    public int kilometraje;

    public Ubicacion()
    {
        
    }
    
    

  //  public static Model.Finder<Long,Ubicacion> find = new Model.Finder<Long, Ubicacion>(Ubicacion.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

}
