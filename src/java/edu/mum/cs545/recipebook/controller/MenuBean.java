/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template imageFile, choose Tools | Templates
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.RateEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

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

    private int menuType;

    private int category;

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

    @Inject
    private UserBean userController;

    @PostConstruct
    public void initialize() {
        System.out.println("Initialize menu service");
        menuService = new MenuServiceImpl(menuFacade, commentFacade);

      //  FacesContext facesContext = FacesContext.getCurrentInstance();
       // UserBean uController = (UserBean) facesContext.getApplication().createValueBinding("#{userBean}").getValue(facesContext);
        menuService.addNewMenu(new MenuItemEntity("Pasta", "Nice italian pasta", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("pasta", "olive oil", "onion"), "Sample cooling instruction", userController.getCurrentUser(), MenuItemStatus.CURRENT));

        MenuItemEntity xx = menuService.addNewMenu(new MenuItemEntity("Rice with nothing", "Nice boiled rice", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("rice", "olive oil", "onion"), "Sample cooling instruction", userController.getCurrentUser(), MenuItemStatus.CURRENT));

        menuService.addNewMenu(new MenuItemEntity("Boiled potatos", "Nice boiled potatoes", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("potato", "olive oil", "onion"), "Sample cooling instruction", userController.getCurrentUser(), MenuItemStatus.CURRENT));

        
        List<MenuItemEntity> demoEntity = DemoDataLoader.getDemoMenuEntity();
        for(MenuItemEntity item : demoEntity){
           item =  menuService.addNewMenu(item);
        }
        menuItems = menuService.getCurrentMenuItems();
 
 
        List<MenuItemEntity> result1 = menuService.findItemsByTitle("Rice");
        List<MenuItemEntity> result2 = menuService.findItemsByUser(userController.getCurrentUser());

        ingredientMap = new HashMap<>();
        ingredientMap.put("Oils", Arrays.asList("canola oil", "olive oil", "Sunflower oil", "Corn oil"));
        ingredientMap.put("Grains and Legumes", Arrays.asList("Couscous", "Dried lentils", "Beaan", "Oats", "Barely", "Millet", "Rice", "Berries"));
        ingredientMap.put("Spices", Arrays.asList("salt", "mustard", "Red pepper", "Black pepper", "Bay leaves", "Cloves"));

        sourceIngredientList = Arrays.asList("canola oil", "olive oil", "Sunflower oil", "Corn oil", "Couscous", "Dried lentils", "Beaan", "Oats", "Barely", "Millet", "Rice", "Berries", "salt", "mustard", "Red pepper", "Black pepper", "Bay leaves", "Cloves");
        selectedIngredients = new ArrayList<>();

        ingredients = new DualListModel<>(sourceIngredientList, selectedIngredients);

    }
    
    
    private UploadedFile imageFile;

    private int ratingValue;

    private String newComment;

    private String newCommentName;

    private List<CommentEntity> commentsForSelectedMenuItem;

    private DualListModel<String> ingredients;

    private Map<String, List<String>> ingredientMap;

    private List<String> sourceIngredientList;

    private List<String> selectedIngredients;

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public void onRate(RateEvent event) {
        menuService.updateAvarageRating(selectedMenuItem, (Integer) event.getRating());
    }

    public void onCancel(AjaxBehaviorEvent event) {

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

    public List<CommentEntity> getCommentsForSelectedMenuItem() {
        return commentsForSelectedMenuItem;
    }

    public void setCommentsForSelectedMenuItem(List<CommentEntity> commentsForSelectedMenuItem) {
        this.commentsForSelectedMenuItem = commentsForSelectedMenuItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public String getCookingInstruction() {
        return cookingInstruction;
    }

    public void setCookingInstruction(String cookingInstruction) {
        this.cookingInstruction = cookingInstruction;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
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

    public void selectMenuItem() {
        for (MenuItemEntity item : menuItems) {
            if (item.getId().equals(this.selectedMenuItemId)) {
                selectedMenuItem = item;
                commentsForSelectedMenuItem = menuService.getComments(selectedMenuItem);
            }
        }
    }

    public void addComment() {
        System.out.println("Add comment called");
        if (newCommentName == null || newCommentName.isEmpty()) {
            newCommentName = "Anonymous";
        }
        menuService.addNewComment(new CommentEntity(selectedMenuItem, newCommentName, LocalDate.now(), newComment));
        commentsForSelectedMenuItem = menuService.getComments(selectedMenuItem);
        newComment = "";
        newCommentName = "";
        ratingValue = 0;
    }

    public int getAverageRating() {
        return Math.round(selectedMenuItem.getAverageRating());
    }

    public DualListModel<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(DualListModel<String> ingredients) {
        this.ingredients = ingredients;
    }

    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile file) {
        this.imageFile = file;
    }

    public void saveImageFile(String fileName) {
        if (imageFile == null) {
            return;
        }
        try {

            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            InputStream input = imageFile.getInputstream();
            String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + fileName;
            File file = new File(newFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(file, false);
            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        } catch (IOException ex) {
            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public void addNewMenu(AjaxBehaviorEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean uController = (UserBean) facesContext.getApplication().createValueBinding("#{userBean}").getValue(facesContext);

        UserEntity userEntity = uController.getCurrentUser();
        MenuItemStatus status = MenuItemStatus.CURRENT;
        if (userEntity.getUserRole() != UserRole.ADMIN) {
            status = MenuItemStatus.SUGGESTED;
        }
        MenuItemEntity newMenu = new MenuItemEntity(this.title, this.description, MenuType.values()[this.menuType], Category.values()[this.category],
                this.selectedIngredients, this.cookingInstruction, uController.getCurrentUser(), status);
        newMenu = menuService.addNewMenu(newMenu);

        if (newMenu != null) {
            Long id = newMenu.getId();

            if (imageFile != null) {
                saveImageFile("menuItem_" + String.valueOf(id) + ".jpg");
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Menu Added", "You have successfully added the new menu");
            FacesContext.getCurrentInstance().addMessage(null, message);
            menuItems = menuService.findItemsByUser(userEntity);

            title = "";
            description = "";
            cookingInstruction = "";
            imageFile = null;

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in adding new menu", "An error occured while trying to add the menu");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
