package com.tbc.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
@DiscriminatorValue("V")
public class Vcub extends Vehiculo
{


    public String color;

    
    public long estacion_id;

    public long getEstacion_id() {
        return estacion_id;
    }

    public void setEstacion_id(long estacion_id) {
        this.estacion_id = estacion_id;
    }
    
    
    


    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
   // public List<Prestamo> prestamos;
    
    public Vcub()
    {
        super();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
