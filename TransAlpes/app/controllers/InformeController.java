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
public Result reportarInforme()
{
	System.out.println("Recibido: id= reportarInforme");

	//TODO implementar

	return ok("usted ha reportado un informe");
}
@Transactional
public Result darReporte()
{
	//TODO implementar
 return ok("usted ha solicitado los infomes");
}

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result darInformes()
{
	JsonNode json = request().body().asJson();
	List<Informe> list = Informe.find.all();
	return ok(Json.toJson(list));
}
}
