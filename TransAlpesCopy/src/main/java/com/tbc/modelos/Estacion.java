package com.tbc.modelos;

import java.util.List;
import javax.persistence.*;

@Entity
public class Estacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int capacidad;

    public long latitud;

    public long longitud;
    
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    public List<Vcub> vcubs;

    public List<Vcub> getVcubs() {
        return vcubs;
    }

    public void setVcubs(List<Vcub> vcubs) {
        this.vcubs = vcubs;
    }

  //  public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);
    public Estacion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public int getDisponibles() {
        return vcubs.size();
    }

    public boolean verificarDisponibilidad() {
        boolean tocaLlenar = false;
        int porcentaje10 = (int) (10 * capacidad) / 100;
        int vacios = capacidad - vcubs.size();
        //verifica que la disponibilidad sea mayor al 10 % de la capacidad
        if (porcentaje10 <= vacios) {
            tocaLlenar = true;
        }
        return tocaLlenar;
    }

    public String hayQueLLenrCon() {
        String llenar = "No hay que llenar la estacion, esta conbuena disponibilidad";
        int disponib = (int) darNumeroLLenar();

        if (verificarDisponibilidad()) {
            llenar = String.format("hay que llenar la estacion con%d", disponib);
        }
        return llenar;
    }

    public int darNumeroLLenar() {
        return this.capacidad - this.vcubs.size();
    }

    public void registrarVcub(Vcub vcub) {
        vcubs.add(vcub);
        System.out.println("Total vcubs: "+vcubs.size() );
    }

    public String darUbicacion() {
        return String.format("La longitud es: %d y La latitud es: %d de la estacion.", this.longitud, this.latitud);
    }

}
