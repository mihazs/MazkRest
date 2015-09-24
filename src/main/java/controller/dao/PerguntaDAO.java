/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.entity.Pergunta;

/**
 *
 * @author Mihael Zamin
 */
public class PerguntaDAO extends GenericDAO<Pergunta>{
  public PerguntaDAO() {
        this(true);
    }
    public PerguntaDAO(boolean autoTransaction)
    {
        super(Pergunta.class, autoTransaction);
    }
  private List<Number> getRandomIds(int quantity)
  {
      beginTransaction();
      Query q;
      q = em.createNativeQuery("SELECT idPergunta FROM pergunta WHERE ativo = 1 ORDER BY RAND()");
      q.setMaxResults(quantity);
      List<Number> l = q.getResultList();
      commitAndCloseTransaction();
      return l;
  }
  public List<Pergunta> getRandom(int quantity)
  {
      List<Number> l = getRandomIds(quantity);
      List<Pergunta> aux = new LinkedList<>();
      for(Number t : l)
      {
          aux.add(this.find(t.intValue()));
      }
      
      
      return aux;
            
  }
  public Pergunta[] getRandomArray(int quantity)
  {
      List<Pergunta> l = getRandom(quantity);
      return l.toArray(new Pergunta[l.size()]);
  }
  
}
