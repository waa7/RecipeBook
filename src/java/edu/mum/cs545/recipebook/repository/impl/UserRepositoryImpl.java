/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository.impl;

import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.domain.UserRole;
import edu.mum.cs545.recipebook.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author user
 */
public class UserRepositoryImpl implements UserRepository{

    private List<UserEntity> userList;
    
    @EJB
    edu.mum.cs545.recipebook.db.UserEntityFacade userFacade;

    public UserRepositoryImpl() {
        if(userList == null)
            userList = new ArrayList();
    }
    
    private void findAllUser() {
        userList = userFacade.findAll();
        
    }
    
    @Override
    public UserEntity createUser(String userName, String email, String password, UserRole userRole) {
        UserEntity newUser = new UserEntity(userName,email,password,userRole);
        if(userFacade.find(newUser) == null) {
            userFacade.create(newUser);
        }else {
            throw new UnsupportedOperationException("User already exists."); //To change body of generated methods, choose Tools | Templates.
        }
        return newUser;
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        userFacade.edit(userEntity);
    }

    @Override
    public UserEntity findUserById(String userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        findAllUser();
        for(UserEntity user:userList) {
            if(user.getId().equals(userId))
                return user;
        }
        //if not found 
        return null;
    }

    @Override
    public UserEntity findUserByName(String userName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        findAllUser();
        for(UserEntity user:userList) {
            if(user.getUserName().equals(userName))
                return user;
        }
        //if not found 
        return null;
    }
    
}
