package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.AddressRepository;
import isep.endoftrackproject._0money_c2c.services.LocationService;
import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    LocationService locationService;

    @GetMapping
    public String creationForm(Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("title", "Registration");
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        //TODO is going to be called on edit, handle update != creation
        if (result.hasErrors()) {
            //wrong parameters, the errors are automatically added to the model
            model.addAttribute("user", user);
            model.addAttribute("mode", "create");
            model.addAttribute("title", "Registration");
            return "user";
        }
        locationService.completeAddress(user.getAddress());
        try {
            userService.register(user);
        } catch (Exception e) {
            model.addAttribute("user", user);
            model.addAttribute("mode", "create");
            model.addAttribute("title", "Registration");
            model.addAttribute("message", e.getMessage());
            return "user";
        }
        try {
            //try automatic authentication
            request.login(user.getEmail(), user.getPassword());
        } catch (ServletException ignored) {
            //redirect to log in on fail
            return "redirect:login";
        }
        //redirect to profile
        return "redirect:user/" + user.getId();
    }

    @RequestMapping("/{id}")
    public String viewUser(@PathVariable("id") User user, @RequestParam("mode") Optional<String> requestedMode, Model model) {
        String mode = requestedMode.orElse("view");
        boolean self = userService.isCurrentUser(user);
        if (mode.equals("edit") && !self) mode = "view";
        model.addAttribute("mode", mode);
        model.addAttribute("self", self);
        String title = (self) ? "Profile" : user.getUsername() + " profile";
        model.addAttribute("title", title);
        model.addAttribute("user", user);
        return "/user";
    }
}
