package com.tbc.modelos;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Vehiculo 
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    public String estado;

    public String tipo;

    @OneToMany(cascade=CascadeType.ALL)
    public List<Ubicacion> posiciones;

   // public static Model.Finder<Long,Vehiculo> find = new Model.Finder<Long, Vehiculo>(Vehiculo.class);

    public final void agregarPosicion(Ubicacion ubicacion)
    {
        if(posiciones==null)
            posiciones = new ArrayList<Ubicacion>();
        posiciones.add(ubicacion);
    }
    
    public Vehiculo()
    {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Ubicacion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Ubicacion> posiciones) {
        this.posiciones = posiciones;
    }
    
    public Ubicacion darUltimaPosicion()
    {
        return posiciones.get(posiciones.size()-1);
    }
    
}
