package com.trelloiii.services;


import com.trelloiii.dao.UsersDao;
import com.trelloiii.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service(value = "userService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UsersDao usersDao;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Users user= usersDao.findByLogin(s);
        if(user==null)
            throw new UsernameNotFoundException("user not found");

        List<SimpleGrantedAuthority> authorities;
        int role=user.getRole();
        if(role==1){
            authorities=Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authorities=Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(user.getLogin(),user.getPassword(),authorities);
    }
}
