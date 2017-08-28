package org.toepfer.foodApp.db.service;

import org.toepfer.foodApp.db.CustomUserNotFoundException;
import org.toepfer.foodApp.db.entity.UserEntity;
import org.toepfer.foodApp.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by toepfer on 8/20/2017.
 */
@Service
public class AuthenticationService {

    @Autowired private UserRepository userRepository;

    //todo: should return custom POJO to hide jpa annotations
    public UserEntity getUser(String username) throws CustomUserNotFoundException {

        UserEntity userEntity = userRepository.findOne(username);
        if(userEntity == null){
            throw new CustomUserNotFoundException();
        }
        return userEntity;
    }

    //todo: should return custom POJO to hide jpa annotations
    public boolean isAdmin(String username) {

        UserEntity userEntity = userRepository.findOne(username);
        if(userEntity == null){
            return false;
        }
        return userEntity.isAdmin();
    }

}
