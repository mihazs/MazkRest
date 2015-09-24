/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import model.entity.Area;

/**
 *
 * @author Mihael Zamin
 */
public class AreaDAO extends GenericDAO<Area>{
 public AreaDAO() {
        this(true);
    }
    public AreaDAO(boolean autoTransaction)
    {
        super(Area.class, autoTransaction);
    }
  public void delete(Area t)
  {
      this.delete(t);
  }
}