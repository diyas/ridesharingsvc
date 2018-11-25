package com.test.ridesharingsvc.utility;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.repository.UsersRepo;
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

    public static Long getUserId(UsersRepo repo){
        return repo.findByUserName(getUserLogin()).orElseThrow(()-> new NotFound("Not Found")).getUserId();
    }

//    public static ResponseEntity<?> isAccess(String role){
//        if (Utility.getRoleLogin() == role){
//            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Access Denied!"));
//        }
//        re
//    }
}
