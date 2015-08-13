package models;

/**
 * Created by la.cortes10 on 12/08/2015.
 */

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Ubicacion extends  Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public long latitud;

    public long longitud;

    public long hora;

    public String estado;

    public int kilometraje;

    public static Model.Finder<Long,Ubicacion> find = new Model.Finder<Long, Ubicacion>(Ubicacion.class);
}
