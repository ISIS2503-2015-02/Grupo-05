package models;

import com.avaje.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by n.castro15 on 12/08/2015.
 */
public class
        Conductor extends  Model{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String tipoTransporte;

   // @OneToOne
   // public Vehiculo vehiculo;

    public static Model.Finder<Long,Conductor> find = new Model.Finder<Long, Conductor>(Conductor.class);

}
