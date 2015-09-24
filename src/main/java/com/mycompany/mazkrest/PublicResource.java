/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mazkrest;

import controller.dao.GenericDAO;
import controller.dao.StandardDAO;
import controller.dao.TipoDAO;
import controller.dao.UsuarioDAO;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import model.entity.Tipo;
import model.entity.Usuario;
import model.exceptions.ConflictException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author Mihael Zamin
 */
@Path("public")
public class PublicResource {

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario cadastro(Usuario u) {
        UsuarioDAO dao = new UsuarioDAO();
        StandardDAO sDao = new StandardDAO();
        TipoDAO tDao = new TipoDAO();
        Tipo t = tDao.find(3);
        if(t.getUsuarioList() == null)
            t.setUsuarioList(new ArrayList<Usuario> ());
        t.getUsuarioList().add(u);
        u.setTipo(t);
        if (!dao.existEmail(u.getEmail())) {
            sDao.beginTransaction();
            u = sDao.getEntityManager().merge(u);
            sDao.commit();
            sDao.closeTransaction();
            return u;
        } else {
            throw new ConflictException(this.getUri());
        }

    }

    public URI getUri() {
        return uriInfo.getBaseUriBuilder().path(PublicResource.class).build();
    }
    @GET
    @Path("startIndexing")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean startIndexing() throws InterruptedException
    {
        StandardDAO dao = new  StandardDAO();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(dao.getEntityManager());
        fullTextEntityManager.createIndexer().startAndWait();
        return true;
    }
    @POST
    @Path("checkemail")
    @Produces(MediaType.APPLICATION_JSON)  
    @Consumes(MediaType.APPLICATION_JSON)   
    public boolean isExistentEmail(String email)
    {
        boolean b = new UsuarioDAO().existEmail(email);
        return b;
    }
    
}
