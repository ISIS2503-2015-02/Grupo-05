package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.ebean.Transactional;
import play.mvc.*;

/**
 * Created by ss.salazar10 on 13/08/2015.
 */
public class ClienteController extends Controller {

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result crearReserva(Long id)
    {
        JsonNode json = request().body().asJson();
        System.out.println("Recibido: id="+id+"\n"+json);

        //TODO implementar

        return ok("Reserva creada: "+json);
    }

    @Transactional
    public Result cancelarReserva(Long idCliente, Long idReserva)
    {

        System.out.println("Recibido: idCliente="+idCliente+"\tidReserva="+idReserva);

        //TODO implementar

        return ok("Reserva cancelada");
    }


    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result alquilarVcub(Long idCliente)
    {

        JsonNode json = request().body().asJson();
        System.out.println("Recibido: idCliente="+idCliente+"\n"+json);

        //TODO implementar

        return ok("Ud ha reservado el vcub con id="+json.get("vcub"));
    }

   // devolverVcub(id: Long)
   @Transactional
   @BodyParser.Of(BodyParser.Json.class)
   public Result devolverVcub(Long idCliente)
   {

       JsonNode json = request().body().asJson();
       System.out.println("Recibido: idCliente="+idCliente+"\n"+json);

       //TODO implementar

       return ok("Ud ha devuelto el vcub con id="+json.get("vcub"));
   }



}
