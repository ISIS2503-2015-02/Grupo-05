package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@DiscriminatorValue("V")
public class Vcub extends Vehiculo{


    public String color;

}
