package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.model.UserDTO;
import isep.endoftrackproject._0money_c2c.repositories.AddressRepository;
import isep.endoftrackproject._0money_c2c.services.LocationService;
import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        model.addAttribute("self", true);
        model.addAttribute("title", "Registration");
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            //wrong parameters, the errors are automatically added to the model
            model.addAttribute("user", user);
            model.addAttribute("mode", "create");
            model.addAttribute("self", true);
            model.addAttribute("title", "Registration");
            return "user";
        }
        locationService.completeAddress(user.getAddress());
        try {
            userService.register(user);
            model.addAttribute("message", "Registered account");
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
            return "redirect:/login";
        }
        //redirect to profile
        return "redirect:/user/" + user.getId();
    }

    @PostMapping({"/{id}", "/profile"})
    @PreAuthorize("isAuthenticated()")
    public String editUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult result, Model model, HttpServletRequest request) {
        if (!userService.isCurrentUser(user))
            throw new IllegalArgumentException("Not enough rights to update this user");
        if (result.hasErrors()) {
            //wrong parameters, the errors are automatically added to the model
            model.addAttribute("user", user);
            model.addAttribute("mode", "edit");
            model.addAttribute("self", true);
            model.addAttribute("title", "Profile");
            return "user";
        }
        locationService.completeAddress(user.getAddress());
        try {
            userService.save(user);
        } catch (Exception e) {
            model.addAttribute("mode", "edit");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("user", user);
            model.addAttribute("title", "Profile");
            model.addAttribute("self", true);
            return "user";
        }
        //redirect to profile
        return "redirect:profile";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String viewUser(@RequestParam("mode") Optional<String> requestedMode, Model model) {
//        return viewUser(new UserDTO(userService.getCurrentUser()), requestedMode, model);
        return "forward:/user/" + userService.getCurrentUser().getId();
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") User user, @RequestParam("mode") Optional<String> requestedMode, Model model) {
        UserDTO userDTO = userService.convertToDto(user);
        String mode = requestedMode.orElse("view");
        boolean self = userService.isCurrentUser(userDTO);
        if (mode.equals("edit") && !self) mode = "view";
        model.addAttribute("mode", mode);
        model.addAttribute("self", self);
        String title = (self) ? "Profile" : userDTO.getUsername() + " profile";
        model.addAttribute("title", title);
        model.addAttribute("user", userDTO);
        return "user";
    }
}
