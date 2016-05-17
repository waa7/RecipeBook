/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.db;

import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
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
public class MenuItemEntityFacade extends AbstractFacade<MenuItemEntity> {

    private static final int MAX_SEARCH_RESULT = 1000;
    
    @PersistenceContext(unitName = "AnnapurnaRecipeBookPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuItemEntityFacade() {
        super(MenuItemEntity.class);
    }
    
    public List<MenuItemEntity> findItemsByTitle(String title){
       return em.createQuery("SELECT m FROM MenuItemEntity m WHERE m.title LIKE :title").setParameter("title", "%" + title + "%").setMaxResults(MAX_SEARCH_RESULT).getResultList(); 
    }
    
    public List<MenuItemEntity> findItemsByUser(UserEntity createdBy){
        return em.createQuery("SELECT m FROM MenuItemEntity m WHERE m.createdBy = :createdBy").setParameter("createdBy", createdBy).setMaxResults(MAX_SEARCH_RESULT).getResultList(); 
    }
    
       public List<MenuItemEntity> findItemsByStus(MenuItemStatus status){
        return em.createQuery("SELECT m FROM MenuItemEntity m WHERE m.status = :status").setParameter("status", status).setMaxResults(MAX_SEARCH_RESULT).getResultList(); 
    }
    
}
