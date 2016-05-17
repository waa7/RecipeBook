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

    public String getWelcomeMessage() {

        return "xxxxx";
    }
    @EJB //this annotation causes the container to inject this dependency
    private edu.mum.cs545.recipebook.db.UserEntityFacade userEntityFacade;

    private UserService userService;

    @PostConstruct
    public void initialize() {
        userService = new UserServiceImpl(userEntityFacade);
        currentUser = userService.addUser(new UserEntity("Admin", "xx@xx.com", "password", UserRole.ADMIN));
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
}
