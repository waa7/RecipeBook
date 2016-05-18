/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository.impl;

import edu.mum.cs545.recipebook.db.CommentEntityFacade;
import edu.mum.cs545.recipebook.db.MenuItemEntityFacade;
import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.repository.MenuRepository;
import java.util.List;

/**
 *
 * @author Endalkachew Asnake
 */
public class MenuRepositoryImpl implements MenuRepository{

    private MenuItemEntityFacade menuFacade;
    private CommentEntityFacade commentFacade;
    
    
    public MenuRepositoryImpl(MenuItemEntityFacade menuFacade, CommentEntityFacade commentFacade){
        this.menuFacade = menuFacade;
        this.commentFacade = commentFacade;
    } 
  
    @Override
    public MenuItemEntity addNewMenu(MenuItemEntity menuItem) { 
        menuFacade.create(menuItem); 
        return menuItem;
    }

    @Override
    public void updateMenuItem(MenuItemEntity menuItem) {
        menuFacade.edit(menuItem);
    }

    @Override
    public MenuItemEntity findItemById(Long id) {
        return menuFacade.find(id);
    }

    @Override
    public List<MenuItemEntity> findItemsByTitle(String title) {  
        return menuFacade.findItemsByTitle(title);
    }

    @Override
    public List<MenuItemEntity> findItemsByUser(UserEntity userEntity) { 
        return menuFacade.findItemsByUser(userEntity);
    }

    @Override
    public List<MenuItemEntity> findItemsByStatus(MenuItemStatus status) {
        return menuFacade.findItemsByStus(status);
    }

    @Override
    public CommentEntity addNewComment(CommentEntity comment) {
         commentFacade.create(comment);
         return comment;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentFacade.remove(commentFacade.find(commentId));
    }

    @Override
    public List<CommentEntity> getComments(MenuItemEntity menuItem) {
        return commentFacade.findCommentsByMenuItem(menuItem);
    } 
}
