/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import static controller.dao.GenericDAO.emf;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Mihael Zamin
 */
public class StandardDAO {
    private static final long serialVersionUID = 1L;
    protected static final String PERSISTENCE_UNIT_NAME = "com.mycompany_MazkRest_war_1.0-SNAPSHOTPU";
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    protected EntityManager em;
    protected CriteriaBuilder cb;
    public void beginTransaction() {
        
        getEntityManager().getTransaction().begin();
    }
    public void commit() {
        em.getTransaction().commit();
    }

    public void rollback() {
        em.getTransaction().rollback();
    }

    public void closeTransaction() {
        em.close();
    }
    public void flush() {
        em.flush();
    }
    public EntityManager getEntityManager()
    {
        if(em == null)
            em = emf.createEntityManager();
        return em;
    }
    
    
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List findAll(Class entityClass) {
        List list = null;
        
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        
        list = em.createQuery(cq).getResultList();
        
        return list;
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected Object findOneResult(String namedQuery, Map<String, Object> parameters) {
        Object result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (Object) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    protected List findAllResult(String namedQuery, Map<String, Object> parameters) {
        List result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}
