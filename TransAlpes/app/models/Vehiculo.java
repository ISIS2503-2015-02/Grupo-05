package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Vehiculo extends Model
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String tipo;

    public String estado;


   @OneToMany
    public List<Ubicacion> posiciones;





}
