package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by n.castro15 on 12/08/2015.
 */
public class Conductor extends  Model{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    public String tipoTransporte;



    public static Model.Finder<Long,Conductor> find = new Model.Finder<Long, Conductor>(Conductor.class);

}
