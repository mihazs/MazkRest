/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mazkrest;

import controller.dao.AlternativaDAO;
import controller.dao.PerguntaDAO;
import controller.dao.StandardDAO;
import controller.dao.UsuarioDAO;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import model.entity.Pergunta;
import model.entity.Tentativa;
import model.entity.Usuario;

/**
 *
 * @author Mihael Zamin
 */
@Path("respostas")
@DeclareRoles({"Administrador", "Comum", "Tutor"})
public class RespostasResource {
    @GET
    @Path("/get/random")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public List<Pergunta> getRandom() {
        //TODO return proper representation object
        List<Pergunta> lp =  new PerguntaDAO().getRandom(10);
        for(Pergunta p: lp)
        {
            p.setAlternativaList(new AlternativaDAO().findByPergunta(p));
        }
        return lp;
    }
    @GET
    @Path("/get/tentativa")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Tentativa getTentativa(@Context SecurityContext sc) {
        Usuario u = new UsuarioDAO().findOneByEmail(sc.getUserPrincipal().getName());
        Tentativa t;
        StandardDAO dao = new StandardDAO();
        dao.beginTransaction();
        t = dao.getEntityManager().merge(new Tentativa(u));
        dao.commit();
        dao.closeTransaction();
        return t;
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Tentativa addRespostas(Tentativa t)
    {
        
      /*  for(Resposta r : t.getRespostaList())
        {
            r.setTentativa(t);
            
        }*/
        StandardDAO dao = new StandardDAO();
        dao.beginTransaction();
        t = dao.getEntityManager().merge(t);
        dao.commit();
        dao.closeTransaction();
        //t.setRespostaList(new RespostaDAO().findByTentativa(t));
        return t;
    }
    /*@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public Tentativa addResposta(Resposta resposta)
    {
       
        Tentativa t = resposta.getTentativa();
        
         StandardDAO dao = new StandardDAO();
        dao.beginTransaction();
        t = dao.getEntityManager().find(Tentativa.class, t.getIdTentativa());
        dao.commit();
        resposta.setTentativa(t);
        
        dao.beginTransaction();
        t = dao.getEntityManager().merge(t);
        dao.commit();
        dao.closeTransaction();
        //t.setRespostaList(new RespostaDAO().findByTentativa(t));
        return t;
    }
    */
    
}
