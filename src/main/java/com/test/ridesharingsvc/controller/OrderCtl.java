package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.Order;
import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.OrderRepo;
import com.test.ridesharingsvc.repository.UsersRepo;
import com.test.ridesharingsvc.services.MapQuestService;
import com.test.ridesharingsvc.utility.Utility;
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

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    MapQuestService mapQuestService;

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
            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Access Denied!"));
        }
        Long userId = usersRepo.findByUserName(Utility.getUserLogin()).orElseThrow(()-> new NotFound("Not Found")).getUserId();
        Order order = new Order();
        order.setUserId(userId);
        order.setLatFrom("");
        order.setLonFrom("");
        order.setLatTo("");
        order.setLonTo("");
        order.setStsOrder("");
        orderRepo.save(order);
        return ResponseEntity.ok(new Response(HttpStatus.OK.value(),""));
    }
}
