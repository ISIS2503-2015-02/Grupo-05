package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */

public abstract class Vehiculo
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String tipo;

    private String estado;

}
