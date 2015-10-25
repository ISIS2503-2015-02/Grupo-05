/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;


import com.tbc.modelos.Cliente;
import com.tbc.modelos.Prestamo;
import com.tbc.modelos.Reserva;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author ss.salazar10
 */
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService
{
     @PersistenceContext(unitName = "AplicacionMundialPU")
    private EntityManager entityManager; 
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darCliente(@PathParam("id") long id)
    {
        JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("{id}/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darReservasCliente(@PathParam("id") long id)
    {
        JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    
    @POST
    @Path("{id}/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearReserva(@PathParam("id") long id, Reserva reserva)
    {
        JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    
    
    @DELETE
    @Path("{id}/reservas/{id2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarReserva(@PathParam("id") long idCliente, @PathParam("id2") long idReserva)
    {
        JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @PUT
    @Path("{id}/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarReserva(@PathParam("id") long idCliente, Reserva reserva)
    {
        JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response darClientes()
    {
         JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Path("{id}/prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPrestamo(@PathParam("id") long idCliente, Prestamo prestamo)
    {
         JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("{id}/prestamos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darPrestamos(@PathParam("id") long idCliente)
    {
         JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCliente(Cliente cliente)
    {
         JSONObject rta = new JSONObject();
        
         return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
}
