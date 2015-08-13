package models;

import com.avaje.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by n.castro15 on 12/08/2015.
 */
public class Conductor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String tipoTransporte;

    public static Model.Finder<Long,Conductor> find = new Model.Finder<Long, Conductor>(Conductor.class);

}
