package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
public class Reserva extends  Model{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String estado;

    public Long fecha;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    public Cliente cliente;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    public Vehiculo vehiculo;

    public static Model.Finder<Long,Reserva> find = new Model.Finder<Long, Reserva>(Reserva.class);

}
