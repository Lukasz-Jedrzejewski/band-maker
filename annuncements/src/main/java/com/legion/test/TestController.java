package com.legion.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity("Hello announcements", HttpStatus.OK);
    }
}
