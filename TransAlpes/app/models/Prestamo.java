package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;

@Entity
public class Prestamo
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public Date linea;

}
