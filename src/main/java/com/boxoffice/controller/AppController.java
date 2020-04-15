package com.boxoffice.controller;

import io.swagger.annotations.ApiOperation;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class AppController {


    @GetMapping("/")
    @ApiOperation("This is to check if the app is running")
    public String hello() {
        return "Welcome to BOX-OFFICE!!";
    }




}
