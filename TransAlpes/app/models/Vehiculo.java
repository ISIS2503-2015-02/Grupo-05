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


    public String estado;


<<<<<<< HEAD
<<<<<<< HEAD
   //@OneToMany(cascade = CascadeType.ALL)
    public List<Ubicacion> posiciones;

    public static Model.Finder<Long,Vehiculo> find = new Model.Finder<Long, Vehiculo>(Vehiculo.class);
=======
   @OneToMany
    public List<Ubicacion> posiciones;
=======
   @OneToMany
    public List<Ubicacion> posiciones;

>>>>>>> ef08e8cfd3f808c0f79e004a22d0fde468a30457




<<<<<<< HEAD
>>>>>>> origin/master

=======
>>>>>>> ef08e8cfd3f808c0f79e004a22d0fde468a30457
}
