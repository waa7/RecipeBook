/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository;

import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
import edu.mum.cs545.recipebook.domain.UserEntity;
import java.util.List;

/**
 *
 * @author Endalkachew Asnake
 */
public interface MenuRepository {

    /**
     * Creates a new menu Item.
     *
     * @param menuItem the menu Item to create.
     * @return instance of menu item created with with auto generated unique id
     * set.
     */
    public MenuItemEntity addNewMenu(MenuItemEntity menuItem);

    /**
     * Updates menu item.
     *
     * @param menuItem the menu item to update.
     */
    public void updateMenuItem(MenuItemEntity menuItem);

    /**
     * Finds a menu item by ID.
     *
     * @param id the ID to use for searching.
     * @return the {@link MenuItemEntity} if found, null otherwise.
     */
    public MenuItemEntity findItemById(Long id);

    /**
     * Finds menu items with similar title.
     *
     * @param title the title string to use for searching.
     * @return list of {@link MenuItemEntity} with matching title.
     */
    public List<MenuItemEntity> findItemsByTitle(String title);

    /**
     * Finds menu items created by the specified user.
     *
     * @param userEntity
     * @return list of {@link MenuItemEntity} with matching user entity.
     */
    public List<MenuItemEntity> findItemsByUser(UserEntity userEntity);

    /**
     * Finds menu items with the specified status.
     *
     * @param status
     * @return list of {@link MenuItemEntity} with matching the status.
     */
    public List<MenuItemEntity> findItemsByStatus(MenuItemStatus status);
}
