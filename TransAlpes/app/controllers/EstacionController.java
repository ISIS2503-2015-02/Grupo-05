package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Estacion;
import play.db.ebean.Transactional;
import play.mvc.BodyParser;
import play.mvc.Result;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * Created by ai.mojica10 on 13/08/2015.
 */
@SuppressWarnings("Annotation")
public class EstacionController {

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result darDisponibilidadEstacion(Long id) throws Exception
{
	JsonNode json = request().body().asJson();
	//Estacion que se pide
	Estacion estacion = Estacion.find.byId(id);
	System.out.println("Recibido: id= " + id +"esta estacion se pidio");

	if(null == estacion){
		String exception = String.format("%d La estacion con ese id no existe", id);
		throw  new Exception(exception,null);
	}
	//Obtener la disponibilidad de la estacion
	int numeroDisponible = estacion.darNumeroLLenar();

	int disponible = estacion.getDisponibles();

	//TODO implementar

	String mensaje = String.format("la estacion con %d tiene %s cupos disponibles", id, disponible);
	return ok(mensaje);
}
@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result registrarVcub(Long id)throws Exception
{
	JsonNode js = request().body().asJson();
	//estacion buscada por id
	Estacion esta = Estacion.find.byId(id);

	if(null == esta){
		String mensaje = String.format("La estacion buscada con id: %d no existe", id);
		throw new Exception(mensaje);}

	//aniade uno al numero disponible de Vcubs en la estacion
	esta.registrarVcub();
	//actualiza la estacion
	esta.update();
	//TODO implementar
	return ok("usted ha solicitado regustro vcub");
}
@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result crearEstacion(Long id)
{

	JsonNode json = request().body().asJson();
	return ok();

}

}
