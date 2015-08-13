package models;

import com.avaje.ebean.Model;

import java.util.List;
import javax.persistence.*;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
@PrimaryKeyJoinColumn(name="MOBIBUS_ID")
@DiscriminatorValue("Mobibus")
public class Mobibus extends Vehiculo {

    @OneToMany
    public List<Reserva> reservas;

    public List listaEspera;

    public int personasPie;

    public int personasDiscapacitadas;

    public static Model.Finder<Long,Mobibus> find = new Model.Finder<Long, Mobibus>(Mobibus.class);
}
