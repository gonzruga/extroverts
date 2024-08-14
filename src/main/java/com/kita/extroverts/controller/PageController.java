package com.kita.extroverts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
