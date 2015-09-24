/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entity.Usuario;
import model.entity.exception.UserNotFoundException;

/**
 *
 * @author Mihael Zamin
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {
        this(true);
    }
    public UsuarioDAO(boolean autoTransaction)
    {
        super(Usuario.class, autoTransaction);
    }

    public Usuario getUsuario(String email, String senha) throws UserNotFoundException {
        if (!email.isEmpty() && !senha.isEmpty()) {
            Usuario u = null;
            CriteriaBuilder cBuilder = getCriteriaBuilder();
            CriteriaQuery<Usuario> crt = cBuilder.createQuery(Usuario.class);
            Root<Usuario> usuario = crt.from(Usuario.class);
            crt.where(cBuilder.and(cBuilder.equal(usuario.get("email"), cBuilder.parameter(Usuario.class, "email")),
                    cBuilder.equal(usuario.get("senha"), cBuilder.parameter(Usuario.class, "senha"))));
            TypedQuery q = em.createQuery(crt);
            q.setParameter("email", email);
            q.setParameter("senha", senha);
            try {
                this.beginTransaction();
                u = (Usuario) q.getSingleResult();
            } catch (NoResultException e) {
                u = null;
                throw new UserNotFoundException();

            }
            em.getTransaction().commit();
            em.close();
            return u;
        } else {
            throw new UserNotFoundException();
        }
    }

    public Usuario findOneByEmail(String email) {
        this.beginTransaction();
        /*  Map<String, Object> m = new HashMap<>();
         m.put("email", email);
         Usuario u = findOneResult("Usuario.findByEmail", m); findAll().get(0);*/
        Usuario u = null;
        CriteriaBuilder cBuilder = getCriteriaBuilder();
        CriteriaQuery<Usuario> crt = cBuilder.createQuery(Usuario.class);
        Root<Usuario> usuario = crt.from(Usuario.class);

        crt.where(cBuilder.equal(usuario.get("email"), cBuilder.parameter(String.class, "email")));
        TypedQuery q = em.createQuery(crt);
        q.setParameter("email", email);
        try {
            this.beginTransaction();
            u = (Usuario) q.getSingleResult();
            this.commitAndCloseTransaction();
            return u;
        } catch (NoResultException e) {
            return null;
        }
       //this.commitAndCloseTransaction();

    }

    public boolean existEmail(String email) {
      
        email = email.replace("\"", "");
        return countEmail(email) > 0;
        
      

    }

    public int countEmail(String email) {
        int u;
        CriteriaBuilder cBuilder = getCriteriaBuilder();
        CriteriaQuery<Long> crt = cBuilder.createQuery(Long.class);
        Root<Usuario> usuario = crt.from(Usuario.class);
        crt.select(cBuilder.count(usuario));
        crt.where(cBuilder.equal(usuario.get("email"), cBuilder.parameter(Usuario.class, "email")));
        TypedQuery q = em.createQuery(crt);
        q.setParameter("email", email);
        try {
            this.beginTransaction();
            u = ((Number) q.getSingleResult()).intValue();
            this.commitAndCloseTransaction();
            return u;
        } catch (NoResultException e) {
            return 0;
        }

    }
    public Usuario findById(int id)
    {
        Map<String, Object> param = new HashMap();
        param.put("idUsuario", id);
        beginTransaction();
        Usuario l = this.findOneResult("Usuario.findByIdUsuario", param);
        commitAndCloseTransaction();
        return l;
    }

}
