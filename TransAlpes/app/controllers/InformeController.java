package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Estacion;
import models.Informe;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by ai.mojica10 on 13/08/2015.
 */
public class InformeController extends Controller
{

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result reportarInforme()
{
	System.out.println("Recibido: id= reportarInforme");
	JsonNode json = request().body().asJson();
	Informe inf = Json.fromJson(json, Informe.class);;
	inf.save();
	return ok("usted ha reportado un informe");
}
@Transactional
public Result darReporte()throws Exception
{
	Informe inf = new Informe();
	inf.contenido = "hola";
	long x = 10;
	inf.id = x;
	return ok(Json.toJson(inf));

}

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result darInformes()throws  Exception
{
	System.out.println("Recibido: id= reportarInforme");
	JsonNode json = request().body().asJson();
	List<Informe>  inf = Informe.find.all();

	if(0 == inf.size())
	{
		throw new Exception("No hay informes registrados");
	}

	return ok(Json.toJson(inf));
}

}
