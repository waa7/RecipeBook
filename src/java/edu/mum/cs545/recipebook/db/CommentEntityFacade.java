/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.db;

import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
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
public class CommentEntityFacade extends AbstractFacade<CommentEntity> {

    private final static int MAX_SEARCH_RESULT = 1000;
    @PersistenceContext(unitName = "AnnapurnaRecipeBookPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentEntityFacade() {
        super(CommentEntity.class);
    }

    public List<CommentEntity> findCommentsByMenuItem(MenuItemEntity menuItemEntity) {
        return em.createQuery("SELECT c FROM CommentEntity c WHERE c.menuItemEntity = :menuItemEntity").setParameter("menuItemEntity", menuItemEntity).setMaxResults(MAX_SEARCH_RESULT).getResultList();
    }

}
