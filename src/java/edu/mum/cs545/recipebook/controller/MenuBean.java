/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.controller;

import edu.mum.cs545.recipebook.db.MenuItemEntityFacade;
import edu.mum.cs545.recipebook.domain.Category;
import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
import edu.mum.cs545.recipebook.domain.MenuType;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.domain.UserRole;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import edu.mum.cs545.recipebook.service.MenuService;
import edu.mum.cs545.recipebook.service.impl.MenuServiceImpl;
import java.time.LocalDate;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Endalkachew Asnake
 */
@Named("menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    public String getTestString() {
                return "index";
    }
    private List<MenuItemEntity> menuItems;

    private Long selectedMenuItemId;
    
    private MenuItemEntity selectedMenuItem;

    private MenuItemEntity newMenu;

    private MenuItemEntity newMenuItem;

    private String title;

    private String description;

    private String menuType;

    private String category;

    private List<String> recipes;

    private String cookingInstruction;

    private float averageRating;

    private int numberOfRatings;

    public MenuBean() {
    }

    @EJB
    private edu.mum.cs545.recipebook.db.MenuItemEntityFacade menuFacade;
    @EJB
    private edu.mum.cs545.recipebook.db.CommentEntityFacade commentFacade;

    private MenuService menuService;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userController;

    @PostConstruct
    public void initialize() {
        System.out.println("Initialize menu service");
        menuService = new MenuServiceImpl(menuFacade, commentFacade);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean uController = (UserBean) facesContext.getApplication().createValueBinding("#{userBean}").getValue(facesContext);  
        menuService.addNewMenu(new MenuItemEntity("Pasta", "Nice italian pasta", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("pasta", "olive oil", "onion"), "Sample cooling instruction", uController.getCurrentUser(), MenuItemStatus.CURRENT));
   
        menuService.addNewMenu(new MenuItemEntity("Rice with nothing", "Nice boiled rice", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("rice", "olive oil", "onion"), "Sample cooling instruction", uController.getCurrentUser(), MenuItemStatus.CURRENT));

        menuService.addNewMenu(new MenuItemEntity("Boiled potatos", "Nice boiled potatoes", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("potato", "olive oil", "onion"), "Sample cooling instruction", uController.getCurrentUser(), MenuItemStatus.CURRENT));
 
        menuItems= menuService.getCurrentMenuItems();
        
        List<MenuItemEntity> result1 = menuService.findItemsByTitle("Rice");
        List<MenuItemEntity> result2 = menuService.findItemsByUser(uController.getCurrentUser());
    }

    public UserBean getUserController() {
        return this.userController;
    }

    public void setUserController(UserBean userController) {
        this.userController = userController;
    } 
    
    public List<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItemEntity getSelectedMenuItem() {
        return selectedMenuItem;
    }
 
        public Long getSelectedMenuItemId() {
        return selectedMenuItemId;
    }

    public void setSelectedMenuItemId(Long selectedMenuItemId) {
        this.selectedMenuItemId = selectedMenuItemId;
    }
    
    public void selectMenuItem(){
        for(MenuItemEntity item: menuItems){
            if(item.getId().equals(this.selectedMenuItemId)){
                selectedMenuItem = item;
            }
        }
    }
    
    
    
    private int ratingValue;
     
    private String newComment;
    
    private String newCommentName;

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
    
    public void onRate(RateEvent event){
        menuService.updateAvarageRating(selectedMenuItem, (Integer) event.getRating()); 
    }
    
    public void onCancel(AjaxBehaviorEvent event){
        
    }
     
    public String getNewComment() {
        return newComment;
    }

    public void setNewComment(String newComment) {
        this.newComment = newComment;
    }

    public String getNewCommentName() {
        return newCommentName;
    }

    public void setNewCommentName(String newCommentName) {
        this.newCommentName = newCommentName;
    }
    
    public void addComment(){
        System.out.println("Add comment called");
        if(newCommentName == null || newCommentName.isEmpty()){
            newCommentName = "Anonymous";
        }
        menuService.addNewComment(new CommentEntity(selectedMenuItem, newCommentName , LocalDate.now(), newComment));
        
    }

    public int getAverageRating() {
        return Math.round(selectedMenuItem.getAverageRating());
    } 
}
