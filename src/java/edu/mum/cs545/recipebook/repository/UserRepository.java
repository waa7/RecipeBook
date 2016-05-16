/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.recipebook.repository;

import edu.mum.cs545.recipebook.domain.UserEntity;
import edu.mum.cs545.recipebook.domain.UserRole;

/**
 *
 * @author Endalkachew Asnake
 * 
 * An interface for user repository.
 */
public interface UserRepository {
    

    //public UserEntity createUser(UserEntity user);
    public UserEntity createUser(String userName, String email, String password, UserRole userRole);
     /**
     * Creates a new user.
     * 
     * @param user the {@link  User to create}
     * @return the created user with unique identifier set.
     */
    
    
    public void updateUser(UserEntity userEntity);
    /**
     * Updates existing user.
     * 
     * @param userEntity the {@link UserEntity} to update.
     */

    public UserEntity findUserById(String userId);
     /**
     * Find user by user Id.
     * @param userId the user Id.
     * @return the {@link UserEntity} if found, null otherwise.
     */
  
        
    public UserEntity findUserByName(String userName);
    /**
     * Find user by user Name.
     * @param userName the user name.
     * @return the {@link UserEntity} if found, null otherwise.
     */
}
