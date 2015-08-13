package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
@Entity
public class Tranvia
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public char linea;

    public int temperatura;

    public long hora;

    public boolean panico;

}
