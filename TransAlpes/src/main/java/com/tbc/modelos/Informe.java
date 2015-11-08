package com.tbc.modelos;

import javax.persistence.*;

/**
 * Created by la.cortes10 on 12/08/2015.
 */
@Entity
public class Informe  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    public int numeroGravedad;

    public String tipo;

    public String contenido;

    public Informe()
    {
        
    }
    
   // public static Model.Finder<Long,Informe> find = new Model.Finder<Long, Informe>(Informe.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroGravedad() {
        return numeroGravedad;
    }

    public void setNumeroGravedad(int numeroGravedad) {
        this.numeroGravedad = numeroGravedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
