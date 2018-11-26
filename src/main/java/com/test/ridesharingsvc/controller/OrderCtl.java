package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.Order;
import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.payload.RegisterResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.OrderLogRepo;
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

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderCtl {

    @Autowired
    HttpServletResponse httpResponse;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderLogRepo orderLogRepo;

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
        Long userId = Utility.getUserId(usersRepo);
        Order result = usersRepo.findByUserId(userId).map(user -> {
            orderReq.setUsers(user);
            return orderRepo.save(orderReq);
        }).orElseThrow(() -> new NotFound(""));
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("Success");
        resp.setData(new RegisterResponse(result.getOrderId()));
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }

    @PutMapping("/order/{order_id}/{status}")
    public ResponseEntity<?> updateStatusOrder(@PathVariable(value = "order_id") Long orderId, @PathVariable(value = "status") String status){
        Long userId = Utility.getUserId(usersRepo);
        if(!usersRepo.existsById(userId)) {
            throw new NotFound("UserId " + userId + " not found");
        }
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new NotFound("Not Found"));
        order.setStsOrder(status);
        Order result = usersRepo.findByUserId(userId).map(user -> {
            order.setUsers(user);
            return orderRepo.save(order);
        }).orElseThrow(() -> new NotFound(""));
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("Success");
        resp.setData(result);
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }
}
