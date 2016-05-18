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
        //currentUser = 
        userService.addUser(new UserEntity("t", "xx@xx.com", "t", UserRole.ADMIN));
        userService.addUser(new UserEntity("Admin1", "xx@xx.com", "password", UserRole.ADMIN));
        userService.addUser(new UserEntity("Admin2", "xx@xx.com", "password", UserRole.ADMIN));

        UserEntity user = userService.findUserByName("Admin2");
        if (user != null) {
            System.out.println(user.getUserName());
        }
    }

    public UserEntity getCurrentUser() {
        return this.currentUser;
    }
    
    public String loginAction() {
        UserEntity user = userService.findUserByName(loginUserName);
        if(user==null || loginUserPas==null)
        {
            loggedIn = true;
            return "";
        }
        if(user.getPassword().equals(loginUserPas))
        {
            loggedIn = false;
            currentUser = user;
            return "";
        }
        else
        {
            loggedIn = true;
            return "";
        }
    }
}
