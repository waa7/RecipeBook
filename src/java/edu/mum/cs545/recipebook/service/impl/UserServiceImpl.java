/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.service.impl;

import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.repository.UserRepository;
import edu.mum.cs545.recipebook.repository.impl.UserRepositoryImpl;
import edu.mum.cs545.recipebook.service.UserServiceProvider;

/**
 *
 * @author 984867
 */
public class UserServiceImpl implements UserServiceProvider{
    
    UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }
    
    @Override
    public boolean authenticate(UserEntity user) {
        UserEntity userWithMatchingId = userRepository.findUserByName(user.getUserName());
        if(userWithMatchingId != null)
            return userWithMatchingId.getPassword().equals(user.getPassword());
        return false;
    }

    @Override
    public void addUser(UserEntity user) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return userRepository.createUser(userName, email, password, UserRole.CUSTOMER);
        userRepository.addUser(user);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        userRepository.updateUser(userEntity);
    }

    @Override
    public UserEntity findUserById(String userId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return userRepository.findUserById(userId);
    }

    @Override
    public UserEntity findUserByName(String userName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return userRepository.findUserByName(userName);
    }
}
