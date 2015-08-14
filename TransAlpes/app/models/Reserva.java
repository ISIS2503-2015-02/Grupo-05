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
    public long id;

    public String estado;

    public Long fecha;

    //@ManyToOne
    @Transient
    @JoinColumn(name="cliente_id", nullable = false)
    public long cliente_id;

   // @ManyToOne
   @Transient
   @JoinColumn(name="mobibus_id", nullable = false)
    public long mobibus_id;

    public static Model.Finder<Long,Reserva> find = new Model.Finder<Long, Reserva>(Reserva.class);

}
