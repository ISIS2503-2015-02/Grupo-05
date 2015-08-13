package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class Informe extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public int numeroGravedad;

    public String tipo;

    public String contenido;

    public static Model.Finder<Long,Informe> find = new Model.Finder<Long, Informe>(Informe.class);
}
