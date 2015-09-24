/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import model.entity.Tipo;

/**
 *
 * @author Mihael Zamin
 */
public class TipoDAO extends GenericDAO<Tipo>{
  public TipoDAO() {
        this(true);
    }
    public TipoDAO(boolean autoTransaction)
    {
        super(Tipo.class, autoTransaction);
    }
}