package models;

/**
 * Created by la.cortes10 on 12/08/2015.
 */

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public int latitud;

    public int longitud;

    public long hora;

    public String estado;

    public int kilometraje;

    public static Model.Finder<Long,Ubicacion> find = new Model.Finder<Long, Ubicacion>(Ubicacion.class);
}
