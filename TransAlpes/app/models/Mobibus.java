package models;

import com.avaje.ebean.Model;

import java.util.List;
import javax.persistence.*;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
@DiscriminatorValue("M")
public class Mobibus extends Vehiculo {

    @OneToMany
    public List<Reserva> reservas;


    //public List<String> listaEspera;

    public int personasPie;

    public int personasDiscapacitadas;
}
