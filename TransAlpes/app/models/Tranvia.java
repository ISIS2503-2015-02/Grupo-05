package models;

import com.avaje.ebean.Model;

import javax.persistence.*;


/**
 * Created by ai.mojica10
 */
@Entity
@DiscriminatorValue("T")
public class Tranvia extends Vehiculo
{

    public char linea;

    public int temperatura;

    public long hora;

    public boolean panico;

}
