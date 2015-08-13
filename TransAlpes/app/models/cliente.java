package models;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public String id;

    public String name;

    public int telefono;

    public int tarjetaBancaria;
}
