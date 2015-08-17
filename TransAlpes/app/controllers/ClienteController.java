package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.libs.Json;
import play.db.ebean.Transactional;
import play.mvc.*;

import java.util.List;


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

        //TODO verificar el mobibus que este disponible!

        Mobibus mobibus= (Mobibus) Mobibus.find.byId(reserva.mobibus_id);
        if(mobibus==null)
            throw new Exception("No existe el mobibus con el Id: "+ reserva.mobibus_id);
        cliente.agregarReserva(reserva);
        cliente.update();

        return ok("Ud ha reservado el Mobibus con id="+Json.toJson(reserva.mobibus_id));
    }

    @Transactional
    public Result cancelarReserva(Long idCliente, Long idReserva)throws Exception
    {
        Cliente cliente= Cliente.find.byId(idCliente);
        if(cliente==null)
            throw new Exception("No existe el cliente con el Id: "+ idCliente);

        Reserva reserva= Reserva.find.byId(idReserva);
        if(reserva==null)
            throw new Exception("No existe la reserva con el Id: "+idReserva);

        reserva.delete();

        cliente.eliminarReserva(idReserva);

        return ok("Reserva cancelada");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result actualizarReserva(Long idCliente)throws Exception
    {
        JsonNode json = request().body().asJson();
        Cliente cliente= Cliente.find.byId(idCliente);
        if(cliente==null)
            throw new Exception("No existe el cliente con el Id: "+ idCliente);

        Reserva reserva= Json.fromJson(json, Reserva.class);

        Reserva reservax= Reserva.find.byId(reserva.id);
        if(reservax==null)
            throw new Exception("No existe la reserva con el Id: "+reserva.id);


        reservax= reserva;
        reservax.update();

        cliente.actualizarReserva(reserva, reserva.id);

        return ok("Reserva actualizada");
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
   public Result devolverVcub(Long idCliente)throws Exception
   {
       JsonNode json = request().body().asJson();
       Cliente cliente= Cliente.find.byId(idCliente);
       if(cliente==null)
           throw new Exception("No existe el cliente con el Id: "+ idCliente);

       Prestamo prestamo= Json.fromJson(json, Prestamo.class);

       Prestamo prestamox= Prestamo.find.byId(prestamo.id);
       if(prestamox==null)
           throw new Exception("No existe la reserva con el Id: "+prestamo.id);


       prestamox= prestamo;
       prestamox.update();

       cliente.actualizarPrestamo(prestamo, prestamo.id);

       return ok("Ud ha devuelto el vcub con id="+json.get("vcub"));
   }

    @Transactional
    public Result darClientes()
    {
        JsonNode json = request().body().asJson();
        List<Cliente> list = Cliente.find.all();
        return ok(Json.toJson(list));
    }

@Transactional
@BodyParser.Of(BodyParser.Json.class)
public Result agregarCliente()
{

	JsonNode json = request().body().asJson();
	Cliente clt = Json.fromJson(json, Cliente.class);
	clt.save();
	return ok();

}
public Result darCliente(Long id)throws Exception
{
	JsonNode json = request().body().asJson();
	Cliente  cliente = Cliente.find.byId(id);

	if(null == cliente){throw  new Exception("El cliente con el id: " + id +"no existe");};

	return ok(Json.toJson(cliente));
}
}
