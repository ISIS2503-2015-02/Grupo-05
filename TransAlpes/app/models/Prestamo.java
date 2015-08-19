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

    public Date fecha;

    //@Transient
    @JoinColumn(name="cliente_id", nullable = false)
    public Long cliente_id;

    //@Transient
    @JoinColumn(name="vcub_id", nullable = false)
    public Long vcub_id;

    public static Model.Finder<Long,Prestamo> find = new Model.Finder<Long, Prestamo>(Prestamo.class);


}
