package models;

import javax.persistence.*;
import javax.util.dd
@Entity
public class Estacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public int capacidad;

    public int latitud;

    public int longitud;

    public int disponibles;
}
