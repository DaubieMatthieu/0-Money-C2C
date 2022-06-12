package isep.endoftrackproject._0money_c2c.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Routes {
    @GetMapping("/log_in")
    public String log_in() {
        return "login";
    }

    @GetMapping("/messaging")
    @PreAuthorize("isAuthenticated()")
    public String messaging() {
        return "messaging";
    }

    @GetMapping("/product_information")
    public String product_information() {
        return "product_information";
    }
}
