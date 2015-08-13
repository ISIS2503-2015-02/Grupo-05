package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@PrimaryKeyJoinColumn(name="VCUB_ID")
public class Vcub extends Vehiculo{


    private String color;



    @ManyToOne
    public Estacion estacion;

    @OneToMany
    public List<Prestamo> prestamos;

    public static Model.Finder<Long,Vcub> find = new Model.Finder<Long, Vcub>(Vcub.class);

}
