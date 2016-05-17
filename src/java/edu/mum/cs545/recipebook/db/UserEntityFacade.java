/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.db;

import edu.mum.cs545.recipebook.domain.UserEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class UserEntityFacade extends AbstractFacade<UserEntity> {

    @PersistenceContext(unitName = "AnnapurnaRecipeBookPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserEntityFacade() { 
        super(UserEntity.class); 
    }

    public UserEntity findByName(String name) {
        List<UserEntity> list = em.createQuery("SELECT u FROM UserEntity u WHERE u.userName= :userName").setParameter("userName", name).setMaxResults(1).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
