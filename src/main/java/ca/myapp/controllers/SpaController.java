package ca.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping({
            "/",
            "/jobs",
            "/skills/**",
            "/salary",
            "/about",
            "/settings",
    })
    public String index() {
        return "forward:/index.html";
    }
}