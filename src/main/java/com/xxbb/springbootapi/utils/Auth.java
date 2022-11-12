package com.xxbb.springbootapi.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("auth")
public class Auth {
    public  boolean hasAuth(String... auth){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return false;
        }
        return authentication.getAuthorities().stream().anyMatch(x->{
            for (String s : auth) {
                if(x.getAuthority().equals(s) || x.getAuthority().equals("ROLE_ADMIN")){
                    return true;
                }
            }
            return false;
        });
    }

}
