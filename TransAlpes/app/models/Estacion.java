package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Estacion extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public int capacidad;

    public long latitud;

    public long longitud;

    public int disponibles;

    public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);

}
