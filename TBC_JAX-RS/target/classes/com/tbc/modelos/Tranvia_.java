package com.tbc.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-25T11:02:12")
@StaticMetamodel(Tranvia.class)
public class Tranvia_ extends Vehiculo_ {

    public static volatile SingularAttribute<Tranvia, Boolean> panico;
    public static volatile SingularAttribute<Tranvia, Long> hora;
    public static volatile SingularAttribute<Tranvia, Integer> temperatura;
    public static volatile SingularAttribute<Tranvia, Character> linea;

}