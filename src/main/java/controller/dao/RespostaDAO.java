/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entity.Resposta;
import model.entity.Tentativa;

/**
 *
 * @author Mihael Zamin
 */
public class RespostaDAO extends GenericDAO<Resposta>{
  public RespostaDAO() {
        this(true);
    }
    public RespostaDAO(boolean autoTransaction)
    {
        super(Resposta.class, autoTransaction);
    }
    public List<Resposta> findByTentativa(Tentativa t)
    {
        Map<String, Object> param = new HashMap();
      param.put("tentativa", t);
      beginTransaction();
      List<Resposta> l = this.findAllResult("Resposta.findByTentativa", param);
      commitAndCloseTransaction();
      return l;
    }
}
