/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.service.impl;

import edu.mum.cs545.recipebook.db.MenuItemEntityFacade;
import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.repository.MenuRepository;
import edu.mum.cs545.recipebook.repository.impl.MenuRepositoryImpl;
import java.util.List;
import edu.mum.cs545.recipebook.service.MenuService;

/**
 *
 * @author user
 */
public class MenuServiceImpl implements MenuService{

    private MenuRepository menuRepository;
    
    public MenuServiceImpl(MenuItemEntityFacade menuEntityFacade){
        menuRepository = new MenuRepositoryImpl(menuEntityFacade);
    }
    
    @Override
    public MenuItemEntity addNewMenu(MenuItemEntity menuItem) {
        return menuRepository.addNewMenu(menuItem);
    }

    @Override
    public void updateMenuItem(MenuItemEntity menuItem) {
         menuRepository.updateMenuItem(menuItem);
    }

    @Override
    public MenuItemEntity findItemById(Long id) {
        return menuRepository.findItemById(id);
    }

    @Override
    public List<MenuItemEntity> findItemsByTitle(String title) {
       return menuRepository.findItemsByTitle(title);
    }

    @Override
    public List<MenuItemEntity> findItemsByUser(UserEntity userEntity) {
        return menuRepository.findItemsByUser(userEntity);
    }

    @Override
    public CommentEntity addNewComment(CommentEntity comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteComment(String commentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuItemEntity> getCurrentMenuItems() {
        return menuRepository.findItemsByStatus(MenuItemStatus.CURRENT);
    }
    
}
