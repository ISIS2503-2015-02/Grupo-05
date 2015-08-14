package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.libs.Json;
import play.db.ebean.Transactional;
import play.mvc.*;

import java.util.List;


/**
 * Created by ss.salazar10 on 12/08/2015.
 */
public class VehiculoController extends Controller {

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result agregarPosicion(Long id) throws Exception {
        JsonNode json = request().body().asJson();
        Vehiculo vehiculo = Vehiculo.find.byId(id);
        if(vehiculo==null)
            throw new Exception("El vehiculo con id "+id+" no existe");

        //Agregar la ubicacion a la tabla de ubicaciones
        Ubicacion ubicacion = Json.fromJson(json, Ubicacion.class);
        ubicacion.vehiculo_id = vehiculo.id;
        System.out.println("Intentando guardar ubicacion: "+Json.toJson(ubicacion));


        //Agregar la ubicacion al vehiculo
        vehiculo.agregarPosicion(ubicacion);

        //Actualizar el vehiculo en la base de datos
        vehiculo.update();


        return ok("Se ha agregado una nueva ubicacion\n"+Json.toJson(vehiculo));
    }

    @Transactional
    public Result darVehiculo(Long id)
    {

       Vehiculo vehiculo = Vehiculo.find.byId(id);
        return ok("Ud ha solicitado el vehiculo: "+Json.toJson(vehiculo));
    }


    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result agregarVehiculo()
    {
        JsonNode json = request().body().asJson();
        Vehiculo vehiculo = null;
        String tipo = json.get("tipo").asText();
        switch (tipo)
        {
            case "Mobibus":
                vehiculo=Json.fromJson(json, Mobibus.class);
                break;
            case "Vcub":
                vehiculo=Json.fromJson(json, Vcub.class);
                break;
            case "Tranvia":
                vehiculo=Json.fromJson(json, Tranvia.class);
                break;
            default: throw new IllegalStateException("El tipo \""+tipo+"\" no es valido");
        }

        vehiculo.save();
        return ok("Ud ha agregado un vehiculo: "+Json.toJson(vehiculo));
    }

    @Transactional
    public Result darVehiculos()
    {
       List<Vehiculo> list = Vehiculo.find.all();
        return ok(Json.toJson(list));
    }
}
