/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;

import com.tbc.modelos.Vcub;
import com.tbc.persistence.PersistenceManager;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
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
@Path("/estaciones")
@Produces(MediaType.APPLICATION_JSON)
public class EstacionService {

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
    @Path("{id}/disponibilidad")
    public Response darDisponibilidadEstacion(@PathParam("id") long id)  {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @PUT
    @Path("{id}/vcub")
    public Response registrarVcub(@PathParam("id")long id, Vcub vcub)  {
         JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearEstacion(@PathParam("id")long id) {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response darEstaciones() 
    {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darEstacion(@PathParam("id")long id)  {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
}
