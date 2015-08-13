package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
public class Vcub extends Vehiculo{




    public static Model.Finder<Long,Vcub> find = new Model.Finder<Long, Vcub>(Vcub.class);




}
