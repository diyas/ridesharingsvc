package com.test.ridesharingsvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexCtl {
    @GetMapping
    public String getIndex(){
        return "Hello";
    }
}
