/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository.impl;

import edu.mum.cs545.recipebook.db.MenuItemEntityFacade;
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
    
    public MenuRepositoryImpl(MenuItemEntityFacade menuFacade){
        this.menuFacade = menuFacade;
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
    
}
