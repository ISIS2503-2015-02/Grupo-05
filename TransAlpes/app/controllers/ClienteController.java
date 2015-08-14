package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Cliente;
import models.Prestamo;
import models.Reserva;
import play.libs.Json;
import play.db.ebean.Transactional;
import play.mvc.*;


public class ClienteController extends Controller {

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result crearReserva(Long idCliente)throws Exception
    {
        JsonNode json = request().body().asJson();

        Cliente cliente= Cliente.find.byId(idCliente);
        if(cliente==null)
            throw new Exception("No existe el cliente con el Id: "+ idCliente);

        Reserva reserva= Json.fromJson(json, Reserva.class);
        reserva.cliente_id=cliente.id;

        cliente.agregarReserva(reserva);
        cliente.update();

        return ok("Ud ha reservado el Mobibus con id="+Json.toJson(reserva.mobibus_id));
    }

    @Transactional
    public Result cancelarReserva(Long idCliente, Long idReserva)
    {

        System.out.println("Recibido: idCliente="+idCliente+"\tidReserva="+idReserva);

        //TODO implementar

        return ok("Reserva cancelada");
    }

    @Transactional
    public Result actualizarReserva(Long idCliente, Long idReserva)
    {

        System.out.println("Recibido: idCliente="+idCliente+"\tidReserva="+idReserva);

        //TODO implementar

        return ok("Reserva cancelada");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result alquilarVcub(Long idCliente)throws Exception
    {

        JsonNode json = request().body().asJson();

        Cliente cliente= Cliente.find.byId(idCliente);
        if(cliente==null)
            throw new Exception("No existe el cliente con el Id: "+ idCliente);

        Prestamo prestamo= Json.fromJson(json, Prestamo.class);
        prestamo.cliente_id= cliente.id;

        cliente.agregarPrestamo(prestamo);
        cliente.update();

        return ok("Ud ha reservado el vcub con id="+Json.toJson(prestamo.vcub_id));
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
