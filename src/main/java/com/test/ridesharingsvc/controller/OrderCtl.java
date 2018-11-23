package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.AppException;
import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.Order;
import com.test.ridesharingsvc.model.Role;
import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.OrderRepo;
import com.test.ridesharingsvc.utility.Utility;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderCtl {

    @Autowired
    OrderRepo orderRepo;

    @GetMapping("/order")
    public String getOrder(@RequestParam(value = "lat") String lat, @RequestParam(value = "lon") String lon){
        List<Order> lstOrder =  orderRepo.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<?> coll = authentication.getAuthorities();
        return coll.toArray()[0].toString();
    }

    @PostMapping("/order")
    public ResponseEntity<?> postOrder(@RequestBody Order orderReq){
        if (Utility.getRoleLogin() == RoleName.DRIVER.toString()){
            return new ResponseEntity(new Response(HttpStatus.BAD_REQUEST.value(), "Access Denied!"), HttpStatus.BAD_REQUEST);
        }
        Order order = new Order();
        order.setUserId(Utility.getUserLogin());
        order.setLatFrom("");
        order.setLonFrom("");
        order.setLatTo("");
        order.setLonTo("");
        order.setStsOrder("");
        orderRepo.save(order);
        return ResponseEntity.ok(new Response(200,""));
    }
}
