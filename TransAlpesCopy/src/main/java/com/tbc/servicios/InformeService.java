/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbc.servicios;

import com.tbc.modelos.Informe;
import com.tbc.persistence.PersistenceManager;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author ss.salazar10
 */
@Path("/informes")
@Produces(MediaType.APPLICATION_JSON)
public class InformeService {

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
    public Response reportarInforme(Informe informe) {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    @Path("general")
    public Response darReporte() {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }

    @GET
    public Response darInformes()  {
        JSONObject rta = new JSONObject();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta).build();
    }
}
