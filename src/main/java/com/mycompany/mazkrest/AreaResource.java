/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mazkrest;

import controller.dao.AreaDAO;
import controller.dao.StandardDAO;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.entity.Area;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;

import org.hibernate.search.jpa.Search;


/**
 *
 * @author Mihael Zamin
 */
@Path("area")
@DeclareRoles({"Administrador", "Comum", "Tutor"})
@PermitAll
public class AreaResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getAreas() {
        //TODO return proper representation object
        return new AreaDAO().findAll();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Area addArea(Area t) {
        StandardDAO dao = new StandardDAO();
       dao.beginTransaction();
       t = dao.getEntityManager().merge(t);
       dao.commit();
       dao.closeTransaction();
        return t;
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Area updateArea(Area t) {
       StandardDAO dao = new StandardDAO();
       dao.beginTransaction();
       t = dao.getEntityManager().merge(t);
       dao.commit();
       dao.closeTransaction();
        return t;
    }
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteArea(Integer idArea)
    {
        Area t = new Area();
        StandardDAO dao = new StandardDAO();
        try{
        dao.beginTransaction();
        t = dao.getEntityManager().getReference(Area.class, idArea);
        
        dao.commit();
        } catch(EntityNotFoundException e)
        {
            throw new BadRequestException("Área inválida");
        }
        dao.beginTransaction();
        dao.getEntityManager().remove(t);
        dao.commit();
        dao.closeTransaction();
        
    }
    
    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Area> findAreas(String toFind) throws InterruptedException {
        List<Area> retorno = Collections.EMPTY_LIST;
        StandardDAO dao = new  StandardDAO();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(dao.getEntityManager());
        dao.beginTransaction();
        SearchFactory sf = fullTextEntityManager.getSearchFactory();
        org.apache.lucene.search.Query luceneQuery = sf.buildQueryBuilder().forEntity(Area.class).get().keyword().onField("nome").matching(toFind).createQuery();
        javax.persistence.Query q = fullTextEntityManager.createFullTextQuery(luceneQuery, Area.class);
        retorno = q.getResultList();
        dao.commit();
        dao.closeTransaction();
        return retorno;
    }
    
}
