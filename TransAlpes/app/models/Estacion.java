package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Estacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public int capacidad;

    public int latitud;

    public int longitud;

    public int disponibles;

    public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);

}
