package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Estacion extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int capacidad;

    private long latitud;

    private long longitud;

    private int disponibles;

    public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);


public Long getId() {
	return this.id;
}

public void setId(Long id) {
	this.id = id;
}

public int getCapacidad() {
	return this.capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

public long getLatitud() {
	return this.latitud;
}

public void setLatitud(long latitud) {
	this.latitud = latitud;
}

public long getLongitud() {
	return this.longitud;
}

public void setLongitud(long longitud) {
	this.longitud = longitud;
}

public int getDisponibles() {
	return this.disponibles;
}

public void setDisponibles(int disponibles) {
	this.disponibles = disponibles;
}

public boolean verificarDisponicilidad()
{
	boolean tocaLlenar = false;
	int porcentaje10=(int)(10* this.getCapacidad())/100;
	int vacios = getCapacidad()-getDisponibles();
	//verifica que la disponibilidad sea mayor al 10 % de la capacidad
	if(porcentaje10<= vacios){	tocaLlenar = true;	}
	return tocaLlenar;
}
public String hayQueLLenrCon()
{
	String llenar =  "No hay que llenar la estacion, esta conbuena disponibilidad";
	int disponib =  (int)darNumeroLLenar();

	if(verificarDisponicilidad()) {
		llenar = String.format("hay que llenar la estacion con%d", disponib);
	}
	return llenar;
}
public int darNumeroLLenar()
{
	return this.capacidad-this.disponibles;
}
public void registrarVcub()
{
	this.disponibles++;
}

}
