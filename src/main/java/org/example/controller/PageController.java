package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/main")
    public String getAllPage() {
        return "main";
    }

}
