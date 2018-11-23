package com.test.ridesharingsvc.utility;

import com.test.ridesharingsvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;


public class Utility {

    protected Authentication getAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return null;
    }

    public static String getRoleLogin(){
        Collection<?> coll = new Utility().getAuth().getAuthorities();
        return coll.toArray()[0].toString();
    }

    public static String getUserLogin(){
        String userName = new Utility().getAuth().getName();
        return userName;
    }
}
