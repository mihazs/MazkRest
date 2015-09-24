/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entity.Alternativa;
import model.entity.Pergunta;

/**
 *
 * @author Mihael Zamin
 */
public class AlternativaDAO extends GenericDAO<Alternativa>{
    public AlternativaDAO() {
        this(true);
    }
    public AlternativaDAO(boolean autoTransaction)
    {
        super(Alternativa.class, autoTransaction);
    }
  public List<Alternativa> findByPergunta(Pergunta p)
  {
      Map<String, Object> param = new HashMap();
      param.put("pergunta", p);
      beginTransaction();
      List<Alternativa> l = this.findAllResult("Alternativa.findByPergunta", param);
      commitAndCloseTransaction();
      return l;
  }
}