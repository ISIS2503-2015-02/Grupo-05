package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public String id;

    public String name;

    public int telefono;

    public int tarjetaBancaria;

    public static Model.Finder<Long,Cliente> find = new Model.Finder<Long, Cliente>(Cliente.class);

}
