package com.tbc.modelos;

//import com.avaje.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by n.castro15 on 12/08/2015.
 */
public class
        Conductor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String tipoTransporte;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    
   // @OneToOne
   // public Vehiculo vehiculo;

   // public static Model.Finder<Long,Conductor> find = new Model.Finder<Long, Conductor>(Conductor.class);

    public Conductor() {
    }

}
