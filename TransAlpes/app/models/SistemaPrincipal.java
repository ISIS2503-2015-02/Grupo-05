package models;




import com.avaje.ebean.Model;

import java.util.List;
import javax.persistence.*;
/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class SistemaPrincipal {


    public String nombre;

    //@OneToMany
    public List<Informe> informeList;

    //@OneToMany(cascade = CascadeType.ALL)
    public List<Vehiculo> vehiculos;
}
