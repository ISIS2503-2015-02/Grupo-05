package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by n.castro15 on 12/08/2015.
 */
@Entity
public class Reserva extends  Model{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String estado;

    private Date fecha;

    public static Model.Finder<Long,Reserva> find = new Model.Finder<Long, Reserva>(Reserva.class);

}
