package com.hro.core.cloudbase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/test")
    public String testReq() {
        String msg = "hello api test...!";

        msg = msg + " change";
        return msg;
    }
}
