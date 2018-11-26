package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.Order;
import com.test.ridesharingsvc.model.OrdersLog;
import com.test.ridesharingsvc.model.payload.Paginate;
import com.test.ridesharingsvc.model.payload.RegisterResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.OrderLogRepo;
import com.test.ridesharingsvc.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderLogCtl {

    @Autowired
    HttpServletResponse httpResponse;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderLogRepo orderLogRepo;

    @PostMapping("/order_log/{order_id}")
    public ResponseEntity<?> saveOrderLog(@PathVariable("order_id") Long orderId,@Valid @RequestBody OrdersLog log){
        HttpStatus status;
        OrdersLog result = orderRepo.findByOrderId(orderId).map(order -> {
            log.setOrder(order);
            return orderLogRepo.save(log);
        }).orElseThrow(() -> new NotFound(""));
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("Success");
        resp.setData(new RegisterResponse(result.getId()));
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }


    @GetMapping("/order_log/{order_id}")
    public ResponseEntity<?> getAllOrderLog(@PathVariable("order_id") Long orderId,@RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(value = "page", defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page - 1, limit);
        Optional<Order> order = orderRepo.findByOrderId(orderId);
        Page<OrdersLog> result = orderLogRepo.findByOrder(order.get(), pageable);
        long totalData = result.getTotalElements();
        long totalPage = totalData / 10;
        Paginate paginate = new Paginate();
        paginate.setLimit(limit);
        paginate.setPage(page);
        paginate.setTotalData(totalData);
        paginate.setTotalPage(totalPage);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(result.getContent());
        resp.setPaginate(paginate);
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }

//    @DeleteMapping("/order_log/{order_id}")
//    public ResponseEntity<?> deleteOrderLog(@PathVariable (value = "address_id") Long addressId) {
//        Long userId = Utility.getUserId(userRepo);
//        if(!userRepo.existsById(userId)) {
//            throw new NotFound("UserId " + userId + " not found");
//        }
//
//        Address result = addressRepo.findById(addressId).orElseThrow(()-> new NotFound("Not Found"));
//        addressRepo.delete(result);
//        HttpStatus status = HttpStatus.OK;
//        Response resp = new Response();
//        resp.setCode(status.value());
//        resp.setMessage("Deleted User " + result.getAddressId());
//        resp.setData(result);
//        return new ResponseEntity(resp, status);
//    }
}
