package org.toepfer.foodApp.security;

import org.toepfer.foodApp.db.entity.UserEntity;

public class SecurityUser {

    private String username;

    private String password;

    private boolean admin;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public static SecurityUser fromUserEntity(UserEntity userEntity){
        SecurityUser user = new SecurityUser();
        user.username = userEntity.getUsername();
        user.password = userEntity.getPassword();
        user.admin = userEntity.isAdmin();
        return user;
    }
}
