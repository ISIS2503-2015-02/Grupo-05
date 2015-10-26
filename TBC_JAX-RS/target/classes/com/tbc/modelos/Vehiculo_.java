package com.tbc.modelos;

import com.tbc.modelos.Ubicacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-25T13:25:42")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-25T19:23:00")
>>>>>>> origin/master
@StaticMetamodel(Vehiculo.class)
public abstract class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, Long> id;
    public static volatile SingularAttribute<Vehiculo, String> estado;
    public static volatile SingularAttribute<Vehiculo, String> tipo;
    public static volatile ListAttribute<Vehiculo, Ubicacion> posiciones;

}