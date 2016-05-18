/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.service;

import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.UserEntity;
import java.util.List;

/**
 *
 * @author Endalkachew Asnake
 */
public interface MenuService {

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
     * Gets list of current menu items.
     *
     * @return
     */
    public List<MenuItemEntity> getCurrentMenuItems();

    /**
     * Creates a new comment.
     *
     * @param comment the {@link CommentEntity} to create.
     * @return the created {@link CommentEntity} instance.
     */
    public CommentEntity addNewComment(CommentEntity comment);

    /**
     * Deletes a specific comment.
     *
     * @param commentId the comment to delete
     */
    public void deleteComment(Long commentId);

    /**
     * Update the average rating of the menu.
     *
     * @param menuItemEntity the menu item.
     * @param newRating the new rating value.
     */
    public void updateAvarageRating(MenuItemEntity menuItemEntity, int newRating);
    
    public List<CommentEntity> getComments(MenuItemEntity menuItem);
}
