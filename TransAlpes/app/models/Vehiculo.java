package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Vehiculo extends Model
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String estado;

    public String tipo;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    public List<Ubicacion> posiciones;

    public static Model.Finder<Long,Vehiculo> find = new Model.Finder<Long, Vehiculo>(Vehiculo.class);

    public final void agregarPosicion(Ubicacion ubicacion)
    {
        if(posiciones==null)
            posiciones = new ArrayList<>();
        posiciones.add(ubicacion);
    }

}
