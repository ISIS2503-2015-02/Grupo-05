package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
public class Estacion extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    public int capacidad;

	public long latitud;

	public long longitud;

	public int disponibles;

    public static Model.Finder<Long,Estacion> find = new Model.Finder<Long, Estacion>(Estacion.class);


public boolean verificarDisponicilidad()
{
	boolean tocaLlenar = false;
	int porcentaje10=(int)(10* capacidad)/100;
	int vacios = capacidad-disponibles;
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
public String darUbicacion()
{
	return String.format("La longitud es: %d y La latitud es: %d de la estacion.", this.longitud, this.latitud);
}

}
