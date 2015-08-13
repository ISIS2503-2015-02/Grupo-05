package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Mobibus;
import models.Vehiculo;
import play.db.ebean.Transactional;
import play.mvc.*;
import play.libs.Json;


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
        Mobibus mobibus = Json.fromJson(json, Mobibus.class);
        System.out.println("Agregando mobibus"+"\n"+json);
        mobibus.save();

        //TODO implementar

        return ok(Json.toJson(Vehiculo.find.all()));
    }

    public Result darMobibuses()
    {
        System.out.println("Obteniendo vehiculos de tipo ");
        return ok(Json.toJson(Vehiculo.find.all()));
    }
}
