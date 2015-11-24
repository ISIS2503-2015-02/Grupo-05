/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;


import com.google.gson.Gson;
import com.tbc.modelos.Cliente;
import com.tbc.modelos.Prestamo;
import com.tbc.modelos.Reserva;
import com.tbc.modelos.Vehiculo;
import com.tbc.persistence.PersistenceManager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import org.eclipse.jetty.util.ajax.JSON;
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
    
      @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darCliente(@PathParam("id") long id)
    {
        Cliente c = entityManager.find(Cliente.class, id);
       // cargarReservas(c);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(c).build();
    }
    
    
    private void cargarReservas(Cliente c)
    {
        Query q = entityManager.createQuery("select u from Reserva u WHERE u.cliente_id = "+c.getId());
        List<Prestamo> prestamos = q.getResultList();
        if(c.reservas == null)
            c.reservas = new ArrayList<Reserva>();
        for(Prestamo p: prestamos)
            c.reservas.add(p);
    }
    
    @GET
    @Path("{id}/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darReservasCliente(@PathParam("id") long id)
    {
      Cliente c = entityManager.find(Cliente.class, id);
        cargarReservas(c);
       return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(c.getReservas()).build();
    }
    
    
    @POST
    @Path("{id}/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearReserva(@PathParam("id") long id, JSONObject json)
    {
        JSONObject rta = new JSONObject();
        Reserva reserva= null;
        if(json.get("tipo").toString().equalsIgnoreCase("prestamo"))
            reserva = new Gson().fromJson(json.toJSONString(), Prestamo.class);

        if(reserva ==null)
        {
            
        }
        else
        {
        try {
            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, id);
      
           //reserva.vehiculo = entityManager.find(Vehiculo.class, reserva.vehiculo.id);
           entityManager.persist(reserva);
            entityManager.getTransaction().commit();
            entityManager.refresh(cliente);

         
        } catch (Exception t) {
            t.printStackTrace();
            rta.put("Error", t.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
          
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        }
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
        Query q = entityManager.createQuery("select u from Cliente u");
        List<Cliente> clientes = q.getResultList();
        for(Cliente c: clientes)
            cargarReservas(c);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(clientes).build();
    }
   
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCliente(Cliente cliente)
    {
         JSONObject rta = new JSONObject();
        int status = 200;
        System.out.println("Agregando cliente: "+ new Gson().toJson(cliente));
         try {
                entityManager.getTransaction().begin();
                entityManager.persist(cliente);
                entityManager.getTransaction().commit();
                entityManager.refresh(cliente);
                rta.put("cliente_id", cliente.getId());
            } catch (Exception e) {
                e.printStackTrace();
                status = 500;
                rta.put("Error", e.getMessage());
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            } finally {
                entityManager.clear();
                entityManager.close();
            }

        
        return Response.status(status).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
}
