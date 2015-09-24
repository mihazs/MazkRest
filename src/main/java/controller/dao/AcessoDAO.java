/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.dao;

import java.util.UUID;
import model.entity.Acesso;

/**
 *
 * @author Mihael Zamin
 */
public class AcessoDAO extends GenericDAO<Acesso>{

    public AcessoDAO() {
        this(true);
    }
    public AcessoDAO(boolean autoTransaction)
    {
        super(Acesso.class, autoTransaction);
    }
    
   /* public Usuario getUserByAccess(String token)
    {
        
    }*/
        
    
}
