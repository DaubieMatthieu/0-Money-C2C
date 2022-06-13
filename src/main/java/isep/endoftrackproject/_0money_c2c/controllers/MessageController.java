package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Message;
import isep.endoftrackproject._0money_c2c.model.Transaction;
import isep.endoftrackproject._0money_c2c.repositories.MessageRepository;
import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/messaging")
@PreAuthorize("isAuthenticated()")
public class MessageController {
    @Autowired
    UserService userService;
    @Autowired
    MessageRepository messageRepository;

    //This is the home page, it should be accessible from multiple basic urls
    @GetMapping
    public String inbox(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "/messaging";
    }

    @GetMapping("/{id}")
    public String inbox(@PathVariable("id") Transaction transaction, Model model) {
        model.addAttribute("transaction", transaction);
        Message message = new Message();
        message.setSender(userService.getCurrentUser());
        message.setTransaction(transaction);
        model.addAttribute("message", message);
        return inbox(model);
    }

    @PostMapping
    public RedirectView addMessage(@ModelAttribute("message") Message message) {
        messageRepository.save(message);
        return new RedirectView("/messaging/"+message.getTransaction().getId());
    }
}
