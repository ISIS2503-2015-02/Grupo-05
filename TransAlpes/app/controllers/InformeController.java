package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Cliente;
import models.Estacion;
import models.Informe;
import models.Vehiculo;
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
	JsonNode json = request().body().asJson();
	Informe inf = Json.fromJson(json, Informe.class);;
	inf.save();
	return ok("usted ha reportado un informe");
}
@Transactional
public Result darReporte()throws Exception
{
	Informe inf = new Informe();
	List<Estacion> listEst = Estacion.find.all();
	List<Cliente> listClien = Cliente.find.all();
	List<Informe> listInf = Informe.find.all();
	List<Vehiculo> listVeh = Vehiculo.find.all();


	inf.contenido = "Este es un reporte general de la empresa. La empresa tiene registrados "
			+ listEst.size() + " Estaciones, " + listClien.size() + "Clientes, " + listInf.size() + " Informes, " + listVeh.size() + " vehiculos. " +
			"Porfavor estar atento al numero necesario y estable para el funcionamiento de la empresa" ;


	inf.numeroGravedad = 1;
	inf.tipo = "General";
	return ok(Json.toJson(inf));

}

@Transactional
public Result darInformes()throws  Exception
{
	System.out.println("Recibido: id= reportarInforme");
	List<Informe>  inf = Informe.find.all();

	if(0 == inf.size())
	{
		throw new Exception("No hay informes registrados");
	}

	return ok(Json.toJson(inf));
}

}
