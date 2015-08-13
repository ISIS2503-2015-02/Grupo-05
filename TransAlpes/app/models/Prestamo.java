package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Prestamo extends  Model
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Cliente cliente;

    @ManyToOne
    public Estacion estacion;

    @ManyToOne
    public Vcub vcub;

    public Date linea;

    public static Model.Finder<Long,Prestamo> find = new Model.Finder<Long, Prestamo>(Prestamo.class);


}
