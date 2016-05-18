/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.controller;

import edu.mum.cs545.recipebook.domain.Category;
import edu.mum.cs545.recipebook.domain.CommentEntity;
import edu.mum.cs545.recipebook.domain.MenuItemEntity;
import edu.mum.cs545.recipebook.domain.MenuItemStatus;
import edu.mum.cs545.recipebook.domain.MenuType;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.domain.UserRole;
import edu.mum.cs545.recipebook.service.impl.MenuServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author user
 */
public class DemoDataLoader {
    
    static List<CommentEntity> listCommentEntity=new ArrayList<CommentEntity>();
    static List<MenuItemEntity> listMenuItemEntity=new ArrayList<MenuItemEntity>();
    static List<UserEntity> listUserEntity=new ArrayList<UserEntity>();
    
    public static List<MenuItemEntity> getDemoMenuEntity(){  
        //call this in MenuBean like:  List<MenuItemEntity> demoEntity = DemoDataLoader.getDemoMenuEntity();
        //addCommentForMenuItem();
        //List<MenuItemEntity> listMenuItemEntity=new ArrayList<MenuItemEntity>();
        //listMenuItemEntity.add(new MenuItemEntity("Rice with nothing", "Nice boiled rice", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("rice", "olive oil", "onion"), "Sample cooling instruction", null, MenuItemStatus.CURRENT));
        
        //new added ID 1,2,3
        listMenuItemEntity.add(new MenuItemEntity("Pancake","Vegetable Pancakes",MenuType.MAIN_COURSE, Category.VEGITERIAN,Arrays.asList("flour","sugar","salt","baking power"),"Sift the flour, sugar, baking powder, and salt into a large bowl. Whisk the water and oil together in a small bowl. Make a well in the center of the dry ingredients, and pour in the wet. Stir just until blended; mixture will be lumpy.",null, MenuItemStatus.CURRENT));
        listMenuItemEntity.add(new MenuItemEntity("noodle","Chinese noodle",MenuType.MAIN_COURSE, Category.VEGITERIAN,Arrays.asList("noodle","salt","onion","pepper"),"",null, MenuItemStatus.CURRENT));
        listMenuItemEntity.add(new MenuItemEntity("Corn, Zucchini, And Tomato Skillet Sauté","Corn, Zucchini, And Tomato Skillet Sauté",MenuType.MAIN_COURSE, Category.VEGITERIAN,Arrays.asList("red onion","garlic","salt"),"",null, MenuItemStatus.CURRENT));
 
        //original ID 4,5,6
        listMenuItemEntity.add(new MenuItemEntity("Pasta", "Nice italian pasta", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("pasta", "olive oil", "onion"), "Sample cooling instruction", null, MenuItemStatus.CURRENT));
        listMenuItemEntity.add(new MenuItemEntity("Rice with nothing", "Nice boiled rice", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("rice", "olive oil", "onion"), "Sample cooling instruction", null, MenuItemStatus.CURRENT));
        listMenuItemEntity.add(new MenuItemEntity("Boiled potatos", "Nice boiled potatoes", MenuType.MAIN_COURSE, Category.VEGAN, Arrays.asList("potato", "olive oil", "onion"), "Sample cooling instruction", null, MenuItemStatus.CURRENT));

        //new added ID 7-23
        listMenuItemEntity.add(new MenuItemEntity("Healthy scone","Healthy scones are a quick bread and these recipes are perfect for when you need a savory breakfast bite as snack.",MenuType.DESSERT,Category.VEGITERIAN,Arrays.asList("flour","salt","pine nut"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Penne","Cheesy Spinach Baked Penne",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("Nonstick cooking spray","Kosher salt","olive oil","garlic"),"Preheat the oven to 400 degrees F. Mist a 2 to 2 1/2-quart rectangular or oval baking dish with nonstick cooking spray.Bring a large pot of salted water to a boil. Add the penne and cook according to package directions for al dente.",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("pie","Veggie Spaghetti Pie",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("spaghetti","carrot","broccolini","cherry tomatoes","pepper"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Penne","Creamy Roasted Red Pepper Penne",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("olive oil","onion","penne","garlic"),"In a large pot of salted boiling water, cook penne until al dente according to package directions. Drain, reserving 1 cup pasta water, and return to pot.",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("pizza","Easy Cheese Pizza",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("onion","pepper","cheese"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Salad Dressing", "Dressing,Bill's Blue Cheese Dressing",MenuType.SALAD_DRESSING,Category.VEGAN,Arrays.asList("honey","sause","mustard"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Salad Dressing","Asian Ginger Dressing",MenuType.SALAD_DRESSING,Category.VEGAN,Arrays.asList("ginger","mustard"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Salad Dressing","Honey Mustard Dressing",MenuType.SALAD_DRESSING,Category.VEGAN,Arrays.asList("honey","mustard"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Salad","Chickpea Spinach Salad",MenuType.SALAD,Category.VEGAN,Arrays.asList("chickpea","spinach"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Milk","Fresh Milk",MenuType.DRINK,Category.VEGITERIAN,Arrays.asList("milk"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Red Tea","Red Tea",MenuType.DRINK,Category.VEGITERIAN,Arrays.asList("red tea"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Orange juice","Orange juice",MenuType.DRINK,Category.VEGITERIAN,Arrays.asList("orange juice"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Vegan Brownies","Vegan Brownies",MenuType.DESSERT,Category.VEGITERIAN,Arrays.asList("flour","sugar", "cocoa powder", "baking powder","salt"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Cookie","Chewy Chocolate Chip Cookies",MenuType.DESSERT,Category.VEGITERIAN,Arrays.asList("flour","soda","salt","chocolate"),"On ungreased cookie sheets, drop dough by rounded tablespoonfuls 2 inches apart. 3 Bake 8 to 10 minutes or until light golden brown.",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Noodle","Sesame Noodles",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("noodle","salt","sesame"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("pasta","No-Cream Pasta Primavera",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("olive oil","balsamic vinegar","lemon"),"",null,MenuItemStatus.CURRENT));
	listMenuItemEntity.add(new MenuItemEntity("Macaroni","Baked Macaroni and Cheese",MenuType.MAIN_COURSE,Category.VEGITERIAN,Arrays.asList("macaroni","milk"),"",null,MenuItemStatus.CURRENT));
	
        return listMenuItemEntity;
    }
    
    public static List<CommentEntity> getDemoComment(MenuItemEntity menuItemEntity){
        
        List<CommentEntity> comments = Arrays.asList(new CommentEntity(menuItemEntity, "User1", LocalDate.now(), "Cool !! In this winter season, I cannot find it else where"), new CommentEntity(menuItemEntity, "User2", LocalDate.now(), "I like Spring and this is the best food in this green season!"));
        return comments;
    /*   List<MenuItemEntity> listMenuItemEntity=getDemoMenuEntity();
       
       List<CommentEntity> OneMenu_listCommentEntity=new ArrayList<CommentEntity>();
       
       for(MenuItemEntity item:listMenuItemEntity){
               for(CommentEntity CE: listCommentEntity){
                   if(item == menuItemEntity){
                       OneMenu_listCommentEntity.add(CE);
               }
           }
        }  
       return OneMenu_listCommentEntity;  */
    }
    public static void addCommentForMenuItem()
    {
        //List<CommentEntity> listCommentEntity=new ArrayList<CommentEntity>();
        List<MenuItemEntity> listMenuItemEntity=getDemoMenuEntity();
        for(int i=0;i<3;i++){
        for(MenuItemEntity item:listMenuItemEntity){
           Random random = new Random();
           int month = random.nextInt(12)+1;
           int year=0;
           if(month>5) year=2016;else year=2015;
           int date=random.nextInt(27)+1;
           String Comment="";
           if(month==1) Comment="Cool !! In this winter season, I cannot find it else where";
           if(month==2) Comment="Wonderful Food!";
           if(month==3) Comment="I like Spring and this is the best food in this green season!";
           if(month==4) Comment="It's tasty, I really like it!";
           if(month==5) Comment="Wow, who cook it? it's so nice";
           if(month==6) Comment="Good food, I like MUM";
           if(month==7) Comment="It's hot outside, I feel cool after taking this cool food:)";
           if(month==8) Comment="Like it, I hope Argiro provides it everyday!";
           if(month==9) Comment="Love it, how nice it is!";
           else Comment="just OK, I hope MUM should think about repalce it with something moretasty";
                            
           listCommentEntity.add(new CommentEntity(item,null,LocalDate.of(year, month, date),Comment));
        }
        }// loop 3
    }
    public static List<UserEntity> getDemoUserEntity(){
        
        //UserEntity(String userName, String email, String password, UserRole userRole) {
        listUserEntity.add(new UserEntity("Jack","Jack@mum.edu","Jack",UserRole.CUSTOMER));
        listUserEntity.add(new UserEntity("Mike","Mike@mum.edu","Mike",UserRole.CUSTOMER));
        listUserEntity.add(new UserEntity("admin","Admin@mum.edu","admin",UserRole.ADMIN));
          
        return listUserEntity;
    }
}
