/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mazkrest;

import controller.dao.StandardDAO;
import controller.dao.TentativaDAO;
import controller.dao.TipoDAO;
import controller.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import model.entity.Acesso;
import model.entity.Tentativa;
import model.entity.Tipo;
import model.entity.Usuario;

/**
 * REST Web Service
 *
 * @author Mihael Zamin
 */
@Path("/usuario")
@DeclareRoles({"Administrador", "Comum", "Tutor"})
public class UsuarioResource {

    @Context
    private UriInfo context;
    @Resource(name = "jdbc/mysqldb")
    private DataSource dataSource;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }

    /**
     * Retrieves representation of an instance of services.UsuarioResource
     *
     * @return an instance of model.entity.Usuario
     */
    @GET
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Administrador")

    public List<Usuario> listar() {
        //TODO return proper representation object
        return new UsuarioDAO().findAll();
    }

    /**
     * PUT method for updating or creating an instance of UsuarioResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @GET
    @Path("getinfo/{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUserInfo(@PathParam("{id}") int id) {
        Usuario u = new UsuarioDAO().findById(id);
        //u.setTentativaList(new TentativaDAO().findByUsuario(u));
        return u;
    }
    
    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario updateUsuario(Usuario u)
    {
        StandardDAO sDao = new StandardDAO();
        sDao.beginTransaction();
        u = sDao.getEntityManager().merge(u);
        sDao.commit();
        sDao.closeTransaction();
        return u;
    }
    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(@Context SecurityContext sc) {        
        UsuarioDAO udao= new UsuarioDAO();
        Usuario u = udao.findOneByEmail(sc.getUserPrincipal().getName());
       // u.setTentativaList(new TentativaDAO().findByUsuario(u));
        StandardDAO sDao = new StandardDAO();
       /* Acesso a = new Acesso();
        a.setDataDeEntrada(Calendar.getInstance().getTime());
        a.setUsuario(u);
        if(u.getAcessoList() == null)
            u.setAcessoList(new ArrayList<Acesso>());
        u.getAcessoList().add(a);
        sDao.beginTransaction();
        u = sDao.getEntityManager().merge(u);
        sDao.commit();
        sDao.beginTransaction();
        u.getTentativaList();
        u.getAcessoList();
        sDao.commit();
        sDao.closeTransaction();*/
        return u;

    }

    @GET
    @Path("tipo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tipo> getAllTipos() {
        return new TipoDAO().findAll();
    }
    @GET
    @Path("tentativas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tentativa> getAllTentativas(@Context SecurityContext sc) {
        UsuarioDAO udao= new UsuarioDAO();
        Usuario u = udao.findOneByEmail(sc.getUserPrincipal().getName());
        return new TentativaDAO().findByUsuario(u);
        

    }

}
