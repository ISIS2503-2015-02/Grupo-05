/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;

import com.google.gson.Gson;
import com.tbc.modelos.Mobibus;
import com.tbc.modelos.Tranvia;
import com.tbc.modelos.Ubicacion;
import com.tbc.modelos.Vcub;
import com.tbc.modelos.Vehiculo;
import com.tbc.persistence.PersistenceManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONObject;

/**
 *
 * @author ss.salazar10
 */
@Path("/vehiculos")
//@Produces(MediaType.APPLICATION_JSON)
public class VehiculoService {

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

    @POST
    @Path("{id}/posiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarPosicion(@PathParam("id") long id, Ubicacion posicion) {
        JSONObject rta = new JSONObject();

        try {
            entityManager.getTransaction().begin();
            Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
           //posicion.vehiculo = vehiculo;
            vehiculo.agregarPosicion(posicion);
            entityManager.getTransaction().commit();
            entityManager.refresh(vehiculo);
            rta.put("ubicacion_id", vehiculo.darUltimaPosicion().id);
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

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarVehiculo(JSONObject json) {
        JSONObject rta = new JSONObject();
        System.out.println("Se recibio: " + JSON.toString(json));
        Vehiculo vehiculo = null;
        String tipo = json.get("tipo").toString();
        int status = 200;
        if (tipo.equals("Mobibus")) {
            vehiculo = new Gson().fromJson(json.toJSONString(), Mobibus.class);
        } else if (tipo.equals("Vcub")) {
            //vehiculo = new Gson().fromJson(json.toJSONString(), Vcub.class);
            rta.put("Respuesta", "Para agregar un vcub hagalo desde una estacion");
        } else if (tipo.equals("Tranvia")) {
            vehiculo = new Gson().fromJson(json.toJSONString(), Tranvia.class);
        } else {
            rta.put("Respuesta", "El tipo '" + tipo + "' no es un tipo valido");
        }

        if (vehiculo == null && rta.isEmpty()) {
            rta.put("Respuesta", "No se ha podido agregar el vehiculos");
        } else {
            try {
                System.out.println("Agregando: "+ JSON.toString(vehiculo));
                entityManager.getTransaction().begin();
                entityManager.persist(vehiculo);
                entityManager.getTransaction().commit();
                entityManager.refresh(vehiculo);
                rta.put("vehiculo_id", vehiculo.getId());
            } catch (Throwable t) {
                t.printStackTrace();
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                vehiculo = null;
            } finally {
                entityManager.clear();
                entityManager.close();
            }

        }
        return Response.status(status).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response darVehiculos() {
        Query q = entityManager.createQuery("select u from Vehiculo u");
        List<Vehiculo> rta = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response darVehiculo(@PathParam("id") long id) {

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(entityManager.find(Vehiculo.class, id)).build();
    }
}
