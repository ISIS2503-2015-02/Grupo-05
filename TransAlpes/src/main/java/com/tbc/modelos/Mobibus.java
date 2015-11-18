package com.tbc.modelos;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
@DiscriminatorValue("M")
public class Mobibus extends Vehiculo implements Serializable
{
    //public List<String> listaEspera;

    public int personasPie;

    public int personasDiscapacitadas;
    
    public Mobibus()
    {
        super();
    }
    
    public int getPersonasPie() {
        return personasPie;
    }

    public void setPersonasPie(int personasPie) {
        this.personasPie = personasPie;
    }

    public int getPersonasDiscapacitadas() {
        return personasDiscapacitadas;
    }

    public void setPersonasDiscapacitadas(int personasDiscapacitadas) {
        this.personasDiscapacitadas = personasDiscapacitadas;
    }
    
    
}
