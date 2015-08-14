package controllers;


import play.db.ebean.Transactional;
import play.mvc.Result;

import static play.mvc.Results.ok;

/**
 * Created by ai.mojica10 on 13/08/2015.
 */
@SuppressWarnings("Annotation")
public class EstacionController {

@Transactional
public Result darDisponibilidadEstacion(Long id)
{
	System.out.println("Recibido: id= ");

	//TODO implementar

	return ok("usted ha reportado una estacion disponible");
}
@Transactional
public Result registrarVcub(Long id)
{
	//TODO implementar
	return ok("usted ha solicitado regustro vcub");
}


}
