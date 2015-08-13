package models;

import java.util.List;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class Mobibus {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public List listaEspera;

    public int personasPie;

    public int personasDiscapacitadas;

    public static Finder<Long,Mobibus> find = new Finder<Long, Mobibus>(Mobibus.class);
}
