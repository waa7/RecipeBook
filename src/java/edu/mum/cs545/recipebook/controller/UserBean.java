/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.controller;

import edu.mum.cs545.recipebook.db.UserEntityFacade;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.domain.UserRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import edu.mum.cs545.recipebook.service.UserService;
import edu.mum.cs545.recipebook.service.impl.UserServiceImpl;
import edu.mum.cs545.recipebook.utils.Utils;

/**
 *
 * @author Endlakachew Asnake
 */
@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    private UserEntity currentUser;
    private boolean loggedIn;

    public boolean isAuthered() {
        return loggedIn;
    }
    
    public boolean isAdmin(){
        if(!loggedIn){
            return false;
        }
        return currentUser.getUserRole().equals(UserRole.ADMIN);
    }
    
    public boolean isLoggedIn(){
        return loggedIn;
    }
     
    public String getWelcomeMessage() {

        return "xxxxx";
    }
    @EJB //this annotation causes the container to inject this dependency
    private edu.mum.cs545.recipebook.db.UserEntityFacade userEntityFacade;

    private UserService userService;
    private String loginUserName;
    private String loginUserPas;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginUserPas() {
        return loginUserPas;
    }

    public void setLoginUserPas(String loginUserPas) {
        this.loginUserPas = loginUserPas;
    } 

    @PostConstruct
    public void initialize() {
        userService = new UserServiceImpl(userEntityFacade); 
        
        currentUser = userService.addUser(new UserEntity("admin", "xx@xx.com",Utils.getHash("password"), UserRole.ADMIN));
        userService.addUser(new UserEntity("Admin1", "xx@xx.com", Utils.getHash("password"), UserRole.ADMIN));
        userService.addUser(new UserEntity("Admin2", "xx@xx.com", Utils.getHash("password"), UserRole.ADMIN));

        currentUser = userService.findUserByName("admin"); 
        loggedIn = true;
    }

    public UserEntity getCurrentUser() {
        return this.currentUser;
    }
    private String error = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
     
    public String loginAction() {
        UserEntity user = userService.findUserByName(loginUserName);
        
        if(user != null && user.getPassword().equals(Utils.getHash(loginUserPas))){
             loggedIn = true;
            error = "";
            currentUser = user;
            return "index?faces-redirect=true";
        }else{
              loggedIn = false;
            error = "Invalid user name or password"; 
            return "";
        } 
    }
    
    public String logOut(){
       // currentUser = null;
        loggedIn = false;
        return "index";
    }
}
