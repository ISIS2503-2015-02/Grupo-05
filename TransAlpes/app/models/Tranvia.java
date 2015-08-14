package models;

import com.avaje.ebean.Model;

import javax.persistence.*;


/**
 * Created by ai.mojica10
 */
@Entity
@PrimaryKeyJoinColumn(name="TRANVIA_ID")
public class Tranvia extends Vehiculo
{

    public char linea;

    public int temperatura;

    public long hora;

    public boolean panico;

    public static Model.Finder<Long,Tranvia> find = new Model.Finder<Long, Tranvia>(Tranvia.class);



}
