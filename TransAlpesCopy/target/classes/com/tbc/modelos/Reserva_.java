package com.tbc.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-18T17:31:29")
@StaticMetamodel(Reserva.class)
public abstract class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Long> fecha;
    public static volatile SingularAttribute<Reserva, String> estado;
    public static volatile SingularAttribute<Reserva, String> tipo;
    public static volatile SingularAttribute<Reserva, Long> vehiculo_id;
    public static volatile SingularAttribute<Reserva, Long> id;
    public static volatile SingularAttribute<Reserva, Long> cliente_id;

}