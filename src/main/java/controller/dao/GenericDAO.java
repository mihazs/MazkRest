/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public abstract class GenericDAO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    protected static final String PERSISTENCE_UNIT_NAME = "com.mycompany_MazkRest_war_1.0-SNAPSHOTPU";
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    protected EntityManager em;
    protected CriteriaBuilder cb;
    private Class<T> entityClass;
    private boolean autoTransaction;

    public void beginTransaction() {
        em = emf.createEntityManager();

        em.getTransaction().begin();
    }

    @PostConstruct
    public void toSomething() {
        // em set by Container  
        cb = em.getCriteriaBuilder();

    }

    public CriteriaBuilder getCriteriaBuilder() {
        beginTransaction();
        return em.getCriteriaBuilder();

    }

    public void createQuery(CriteriaQuery c) {
        em.createQuery(c);
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

    public void commitAndCloseTransaction() {
        commit();
        closeTransaction();
    }

    public void flush() {
        em.flush();
    }

    public void joinTransaction() {
        em = emf.createEntityManager();
        em.joinTransaction();
    }

    public GenericDAO(Class<T> entityClass) {
        this(entityClass, true);

    }

    public GenericDAO(Class<T> entityClass, boolean autoTransaction) {
        this.entityClass = entityClass;
        this.autoTransaction = autoTransaction;
    }



    protected void delete(Object id, Class<T> classe) {
        if (autoTransaction) {
            beginTransaction();
        }
        T entityToBeRemoved = em.getReference(classe, id);

        em.remove(entityToBeRemoved);
        if (autoTransaction) {
            commitAndCloseTransaction();
        }
    }

    public T save(T entity) {
        if (autoTransaction) {
            beginTransaction();
        }
        T a = em.merge(entity);
        if (autoTransaction) {
            commitAndCloseTransaction();
        }
        return a;
    }

    public T find(int entityID) {
        if (autoTransaction) {
            beginTransaction();
        }
        T aux = em.find(entityClass, entityID);
        if (autoTransaction) {
            commitAndCloseTransaction();
        }
        return aux;
    }

    public T findReferenceOnly(int entityID) {
        if (autoTransaction) {
            beginTransaction();
        }
        T aux = em.getReference(entityClass, entityID);
        if (autoTransaction) {
            commitAndCloseTransaction();
        }
        return aux;
    }

    public void refresh(T entity) {
        if (autoTransaction) {
            beginTransaction();
        }
        em.refresh(entity);
        if (autoTransaction) {
            commitAndCloseTransaction();
        }

    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll() {
        List<T> list = null;
        if (autoTransaction) {
            beginTransaction();
        }
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        
        list = em.createQuery(cq).getResultList();
        if (autoTransaction) {
            commitAndCloseTransaction();
        }
        return list;
    }

    // Using the unchecked because JPA does not have a
    // query.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;

        try {
            Query query = em.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("No result found for named query: " + namedQuery);
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    protected List<T> findAllResult(String namedQuery, Map<String, Object> parameters) {
        List<T> result = null;

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
