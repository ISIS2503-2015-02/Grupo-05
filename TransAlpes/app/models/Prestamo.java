package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Prestamo extends  Model
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public Date fecha;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JsonIgnore
    public Cliente cliente;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JsonIgnore
    public Vehiculo vehiculo;

    public static Model.Finder<Long,Prestamo> find = new Model.Finder<Long, Prestamo>(Prestamo.class);


}
