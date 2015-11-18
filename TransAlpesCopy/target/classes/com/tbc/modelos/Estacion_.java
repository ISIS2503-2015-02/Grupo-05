package com.tbc.modelos;

import com.tbc.modelos.Vcub;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-17T21:23:48")
@StaticMetamodel(Estacion.class)
public class Estacion_ { 

    public static volatile SingularAttribute<Estacion, Long> latitud;
    public static volatile SingularAttribute<Estacion, Long> longitud;
    public static volatile ListAttribute<Estacion, Vcub> vcubs;
    public static volatile SingularAttribute<Estacion, Long> id;
    public static volatile SingularAttribute<Estacion, Integer> capacidad;

}