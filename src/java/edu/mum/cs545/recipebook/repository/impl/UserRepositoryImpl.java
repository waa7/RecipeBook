/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository.impl;

import edu.mum.cs545.recipebook.db.UserEntityFacade;
import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.repository.UserRepository;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author user
 */ 
public class UserRepositoryImpl implements UserRepository{
 
    private UserEntityFacade userEntityFacade;

    public UserRepositoryImpl() {
    }
    
    public UserRepositoryImpl(UserEntityFacade userEntityFacade){ 
        this.userEntityFacade = userEntityFacade;
    }
    
    @Override
    public UserEntity createUser(UserEntity user) {
        userEntityFacade.create(user); 
        return user;
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userEntityFacade.edit(userEntity);
    }

    @Override
    public UserEntity findUserById(String userId) {
        return userEntityFacade.find(userId);
    }

    @Override
    public UserEntity findUserByName(String userName) {
         return userEntityFacade.findByName(userName);
    }
    
}
