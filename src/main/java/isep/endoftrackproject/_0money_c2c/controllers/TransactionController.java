package isep.endoftrackproject._0money_c2c.controllers;

import isep.endoftrackproject._0money_c2c.model.Item;
import isep.endoftrackproject._0money_c2c.model.Transaction;
import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.ItemRepository;
import isep.endoftrackproject._0money_c2c.repositories.TransactionRepository;
import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/transaction")
@PreAuthorize("isAuthenticated()")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserService userService;

    @GetMapping
    public String transactionForm(@RequestParam("item_ids") List<Item> preSelectedItems, Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("title", "New transaction");
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("transaction", new Transaction(preSelectedItems));
        return "transaction";
    }

    @PostMapping
    public RedirectView createTransaction(@RequestParam(value = "itemsToExchange", required = false) Set<Item> itemsToExchange, @RequestParam(value = "itemsToAcquire", required = false) Set<Item> itemsToAcquire, Model model) {
        if (itemsToAcquire == null) throw new IllegalArgumentException("You have to require at least one item");
        if (itemsToExchange == null) throw new IllegalArgumentException("You have to propose at least one item");
        Set<Item> items = new HashSet<>() {{
            addAll(itemsToAcquire);
            addAll(itemsToExchange);
        }};
        Transaction transaction = transactionRepository.save(new Transaction(items));
        return new RedirectView("/transaction/" + transaction.getId());
    }

    @GetMapping("/{id}")
    public String viewTransaction(@PathVariable("id") Transaction transaction, @RequestParam("mode") Optional<String> requestedMode, Model model) {
        User currentUser = userService.getCurrentUser();
        if (!transaction.getInvolvedUsers().contains(currentUser))
            throw new IllegalArgumentException("Not enough rights to see this transaction");
        model.addAttribute("mode", requestedMode.orElse("view"));
        model.addAttribute("title", transaction.getNameForUser(currentUser));
        model.addAttribute("user", currentUser);
        model.addAttribute("transaction", transaction);
        return "transaction";
    }

    @PostMapping("/{id}")
    public String editTransaction(@PathVariable("id") Transaction transaction, @RequestParam(value = "itemsToExchange", required = false) Set<Item> itemsToExchange, @RequestParam(value = "itemsToAcquire", required = false) Set<Item> itemsToAcquire, Model model) {
        if (itemsToAcquire == null) throw new IllegalArgumentException("You have to require at least one item");
        if (itemsToExchange == null) throw new IllegalArgumentException("You have to propose at least one item");
        User currentUser = userService.getCurrentUser();
        if (!transaction.getInvolvedUsers().contains(currentUser))
            throw new IllegalArgumentException("Not enough rights to see this transaction");
        try {
            Set<Item> items = new HashSet<>() {{
                addAll(itemsToAcquire);
                addAll(itemsToExchange);
            }};
            transaction.setItems(items);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            model.addAttribute("mode", "edit");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("transaction", transaction);
            model.addAttribute("title", transaction.getNameForUser(currentUser));
            return "transaction";
        }
        return "redirect:/transaction/" + transaction.getId();
    }
}
