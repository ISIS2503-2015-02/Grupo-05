package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.ebean.Transactional;
import play.mvc.*;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
public class VehiculoController extends Controller {

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result agregarPosicion(Long id)
    {
        JsonNode json = request().body().asJson();
        System.out.println("Recibido: id="+id+"\n"+json);

        //TODO implementar

        return ok(json);
    }

    @Transactional
    public Result darVehiculo(Long id)
    {

        System.out.println("Recibido: idCliente="+id);

        //TODO implementar

        return ok("Ud ha solicitado el vehiculo: "+id);
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result agregarMobibus()
    {
        JsonNode json = request().body().asJson();
        System.out.println("Agregando mobibus"+"\n"+json);

        //TODO implementar

        return ok("Ud ha agregado un mobibus: "+json);
    }
}
