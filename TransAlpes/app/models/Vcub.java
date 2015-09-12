package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@DiscriminatorValue("V")
public class Vcub extends Vehiculo{


    public String color;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    public List<Prestamo> prestamos;
}
