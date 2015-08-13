package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estacion extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @OneToMany
    public List<Prestamo> prestamos;

    @OneToMany
    public List<Vcub> vcubs;

    public int capacidad;

    public long latitud;

    public long longitud;

    public int disponibles;

    public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);

}
