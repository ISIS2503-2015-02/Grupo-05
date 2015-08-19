package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Cliente;
import models.Estacion;
import models.Mobibus;
import models.Vehiculo;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * Created by ai.mojica10 on 13/08/2015.
 */
@SuppressWarnings("Annotation")
public class EstacionController {

@Transactional
public Result darDisponibilidadEstacion(Long id) throws Exception
{
	//Estacion que se pide
	Estacion estacion = Estacion.find.byId(id);
	if(null == estacion){
		String exception = String.format("%d La estacion con ese id no existe", id);
		throw  new Exception(exception,null);
	}
	//Obtener la disponibilidad de la estacion
	int numeroDisponible = estacion.darNumeroLLenar();

	int disponible = estacion.disponibles;

	//TODO implementar

	String mensaje = String.format("la estacion con %d tiene %s cupos disponibles", id, disponible);
	return ok(mensaje);
}

@Transactional
public Result registrarVcub(Long id)throws Exception
{
	//estacion buscada por id
	Estacion esta = Estacion.find.byId(id);

	if(null == esta){
		String mensaje = String.format("La estacion buscada con id: %d no existe", id);
		throw new Exception(mensaje);}

	//aniade uno al numero disponible de Vcubs en la estacion
	esta.registrarVcub();
	//actualiza la estacion
	//esta.update();
	//TODO implementar
	return ok("usted ha solicitado regustro vcub en la eatcion");
}
	@Transactional
	 @BodyParser.Of(BodyParser.Json.class)
	 public Result crearEstacion()
{

	JsonNode json = request().body().asJson();
	Estacion esta = Json.fromJson(json, Estacion.class);;
	esta.save();
	return ok("Se ha agregado una estacion: ");

}
@Transactional
public Result darEstaciones()
{
	JsonNode json = request().body().asJson();
	List<Estacion> list = Estacion.find.all();
	return ok(Json.toJson(list));
}

@Transactional
public Result darEstacion(Long id)throws  Exception
{
	Estacion  est = Estacion.find.byId(id);
	if(null == est){throw  new Exception("La estacion  con el id: " + id +"no existe");};

	return ok(Json.toJson(est));
}
}
