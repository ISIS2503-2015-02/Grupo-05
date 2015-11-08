package com.tbc.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 
    
        
    @ManyToOne(cascade = CascadeType.ALL)
     @JsonIgnore
    public Estacion estacion;
    

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
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
