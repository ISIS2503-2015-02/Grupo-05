package com.tbc.modelos;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    public List<Prestamo> prestamos;
    
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

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    
}
