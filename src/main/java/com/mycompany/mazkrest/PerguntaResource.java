/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mazkrest;

import controller.dao.AlternativaDAO;
import controller.dao.PerguntaDAO;
import controller.dao.StandardDAO;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.entity.Area;
import model.entity.Pergunta;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;


/**
 * REST Web Service
 *
 * @author Mihael Zamin
 */
@Path("pergunta")
@DeclareRoles({"Administrador", "Comum", "Tutor"})
public class PerguntaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PerguntaResource
     */
    public PerguntaResource() {
    }
    @POST
    @Path("getalternativas")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"Administrador" , "Tutor"})
    public List getAlternativas(Pergunta p) {
        //TODO return proper representation object
        return new AlternativaDAO().findByPergunta(p);
    }
    
    @GET
    @Path("listar")
    @Produces("application/json")
    @RolesAllowed({"Administrador" , "Tutor"})
    public List listarPerguntas() {
        //TODO return proper representation object
        return new PerguntaDAO().findAll();
    }

    
    
    @POST
    @Path("inserir")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"Administrador" , "Tutor"})
    public Pergunta addPergunta(Pergunta p) {
        StandardDAO dao = new StandardDAO();
        dao.beginTransaction();
        p = dao.getEntityManager().merge(p);
        dao.commit();
        dao.closeTransaction();
        
        return p;
    }
    @POST
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"Administrador" , "Tutor"})
    public Pergunta updatePergunta(Pergunta p) {
        StandardDAO dao = new StandardDAO();
        dao.beginTransaction();
        p = dao.getEntityManager().merge(p);
        dao.commit();
        dao.closeTransaction();
        return p;
        
    }
      
    @POST
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Pergunta> find(String toFind)
    {
        List<Pergunta> retorno = Collections.EMPTY_LIST;
        StandardDAO dao = new  StandardDAO();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(dao.getEntityManager());
        dao.beginTransaction();
        SearchFactory sf = fullTextEntityManager.getSearchFactory();
        org.apache.lucene.search.Query luceneQuery = sf.buildQueryBuilder().forEntity(Pergunta.class).get().phrase().onField("enunciado").sentence(toFind).createQuery();
        javax.persistence.Query q = fullTextEntityManager.createFullTextQuery(luceneQuery, Pergunta.class);
        retorno = q.getResultList();
        dao.commit();
        dao.closeTransaction();
        return retorno;
    }
}
