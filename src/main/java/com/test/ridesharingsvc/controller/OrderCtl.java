package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.utility.Utility;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderCtl {

    @GetMapping("/order")
    public String getOrder(){
        return Utility.getUserLogin();
    }

    @PostMapping("/order")
    public void postOrder(){

    }
}
