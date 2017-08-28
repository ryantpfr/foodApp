package org.toepfer.foodApp.security;

import org.toepfer.foodApp.db.CustomUserNotFoundException;
import org.toepfer.foodApp.db.entity.UserEntity;
import org.toepfer.foodApp.db.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by toepfer on 8/24/2017.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity;
        try {
            userEntity = authenticationService.getUser(username);
        } catch (CustomUserNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }


        List<Authorities> authorities = new ArrayList<>();
        authorities.add(Authorities.ROLE_USER);
        if(userEntity.isAdmin()){
            authorities.add(Authorities.ROLE_ADMIN);
        }

        User user = new User(userEntity.getUsername(),userEntity.getPassword(),getGrantedAuthorities(authorities));

        return user;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Authorities> authorities){
        return authorities.stream().map( a -> new SimpleGrantedAuthority(a.name())).collect(Collectors.toList());
    }

    private enum Authorities{
        ROLE_ADMIN,
        ROLE_USER
    }
}
