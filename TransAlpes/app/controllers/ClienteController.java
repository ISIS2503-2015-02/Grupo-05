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

	    String id_mob = json.get("vehiculo_id").asText();
	    Long idM =  Long.valueOf(id_mob);
        Mobibus mobibus= (Mobibus) Mobibus.find.byId(idM);
        if(mobibus==null) {
	        String exepcion = String.format("No existe el mobibus con el Id: %d", reserva.vehiculo_id);
	        throw new Exception(exepcion);
        }
	    reserva.vehiculo_id = idM;
        cliente.agregarReserva(reserva);
        cliente.update();

        return ok("Ud ha reservado el Mobibus con id="+Json.toJson(reserva.vehiculo_id));
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
        Cliente cliente = Cliente.find.byId(idCliente);
        if (cliente == null)
            throw new Exception("No existe el cliente con el Id: " + idCliente);

        Reserva reservaActualizado = Json.fromJson(json, Reserva.class);
        Reserva reservaAntiguo = Reserva.find.byId(reservaActualizado.id);
        if (reservaAntiguo == null)
            throw new Exception("No existe el prestamos con el Id:" + reservaActualizado.id);

        reservaAntiguo = reservaActualizado;
        reservaAntiguo.update();
        return ok("Reserva actualizada");
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result prestarVcub(Long idCliente)throws Exception
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
       Cliente cliente = Cliente.find.byId(idCliente);
       if (cliente == null)
           throw new Exception("No existe el cliente con el Id: " + idCliente);

       Prestamo prestamoActualizado = Json.fromJson(json, Prestamo.class);
       Prestamo prestamoAntiguo = Prestamo.find.byId(prestamoActualizado.id);
       if (prestamoAntiguo == null)
           throw new Exception("No existe el prestamos con el Id:" + prestamoActualizado.id);

       prestamoAntiguo = prestamoActualizado;
       prestamoAntiguo.update();

        cliente.actualizarPrestamo(prestamoActualizado);
       return ok("Prestamo actualizada");
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
@Transactional
public Result darReservasCliente(Long id) throws Exception {
    JsonNode json = request().body().asJson();
    Cliente  cliente = Cliente.find.byId(id);

    if(null == cliente){throw  new Exception(String.format("El cliente con el id: %dno existe", id));};

    List<Reserva> reservas = cliente.reservasMobibus;
    return ok(Json.toJson(reservas));
}

    @Transactional
    public Result darPrestamosCliente(Long id) throws Exception {
        JsonNode json = request().body().asJson();
        Cliente  cliente = Cliente.find.byId(id);

        if(null == cliente){throw  new Exception(String.format("El cliente con el id: %dno existe", id));};

        List<Prestamo> prestamos = cliente.prestamosVcubs;
        return ok(Json.toJson(prestamos));
    }
}
