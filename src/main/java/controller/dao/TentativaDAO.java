/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entity.Tentativa;
import model.entity.Usuario;

/**
 *
 * @author Mihael Zamin
 */
public class TentativaDAO extends GenericDAO<Tentativa> {

    public TentativaDAO() {
        this(true);
    }

    public TentativaDAO(boolean autoTransaction) {
        super(Tentativa.class, autoTransaction);
    }

    public List<Tentativa> findByUsuario(Usuario u) {
        Map<String, Object> param = new HashMap();
        param.put("usuario", u);
        beginTransaction();
        List<Tentativa> l = this.findAllResult("Tentativa.findByUsuario", param);
        commitAndCloseTransaction();
        return l;
    }
}
