package com.tbc.modelos;

import javax.persistence.*;


/**
 * Created by ai.mojica10
 */
@Entity
@DiscriminatorValue("T")
public class Tranvia extends Vehiculo
{

    public char linea;

    public int temperatura;

    public long hora;

    public boolean panico;
    
    public Tranvia()
    {
        
    }

    public char getLinea() {
        return linea;
    }

    public void setLinea(char linea) {
        this.linea = linea;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }

    public boolean isPanico() {
        return panico;
    }

    public void setPanico(boolean panico) {
        this.panico = panico;
    }
    
    

}
