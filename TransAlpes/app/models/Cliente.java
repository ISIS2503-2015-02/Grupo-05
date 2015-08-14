package models;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Cliente extends Model{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String name;

    public int telefono;

    public String tarjetaBancaria;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reserva> reservas;

    public static Model.Finder<Long,Cliente> find = new Model.Finder<Long, Cliente>(Cliente.class);


}