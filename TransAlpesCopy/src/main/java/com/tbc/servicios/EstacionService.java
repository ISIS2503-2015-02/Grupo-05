/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;

import com.tbc.modelos.Estacion;
import com.tbc.modelos.Vcub;
import com.tbc.modelos.Vehiculo;
import com.tbc.persistence.PersistenceManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.jetty.util.ajax.JSON;
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
    @RequiresRoles("Estacion")
    public Response darDisponibilidadEstacion(@PathParam("id") long id) {
        JSONObject rta = new JSONObject();
        Estacion estacion = entityManager.find(Estacion.class, id);
        if (estacion == null) {
            rta.put("Error", "La estacion no existe");
        } else {
            rta.put("Disponibilidad", id);
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @POST
    @Path("{id}/vcubs")
    @RequiresRoles("Estacion")
    public Response registrarVcub(@PathParam("id") long id, JSONObject json) {
        JSONObject rta = new JSONObject();
        try {

            Estacion estacion = entityManager.find(Estacion.class, id);
            Integer vcubId = (Integer)json.get("vcub_id");
            System.out.println("Registrando el vcub "+vcubId.longValue()+" en la estacion "+id);
            Vcub vcub = entityManager.find(Vcub.class,vcubId.longValue());

            if (vcub != null) {
                vcub.estacion_id = estacion.id;
                vcub.estado ="En estacion "+estacion.id;
                estacion.registrarVcub(vcub);
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                entityManager.refresh(estacion);
                rta.put("Estacion_id", estacion.getId());
                rta.put("vcubs", JSON.toString(estacion.getVcubs()));
            }
            else rta.put("Error", "no se encontro ese vcub");
        } catch (Exception t) {
            rta.put("Error", t.getMessage());
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresRoles("Estacion")
    public Response crearEstacion(Estacion estacion) {
        JSONObject rta = new JSONObject();
        System.out.println("Agregando estacion...");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(estacion);
            entityManager.getTransaction().commit();
            entityManager.refresh(estacion);
            rta.put("estacion_id", estacion.getId());
        } catch (Exception t) {
            rta.put("Error", t.getMessage());
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresRoles("Estacion")
    public Response darEstaciones() {
        Query q = entityManager.createQuery("select u from Estacion u");
        List<Estacion> rta = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresRoles("Estacion")
    public Response darEstacion(@PathParam("id") long id) {
        Estacion rta = entityManager.find(Estacion.class, id);

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
}
