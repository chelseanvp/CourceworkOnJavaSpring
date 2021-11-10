package com.example.sweater.controller;

import com.example.sweater.domain.Item;
import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/user")
    public String main(
            @AuthenticationPrincipal User user,
            Model model){
        Iterable<Item> items = itemRepo.findByAuthor(user);
        model.addAttribute("items",items);
        return "user";
    }

    @PostMapping("/user")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam Integer year,
            Model model
    ){
        Item message = new Item(text,tag,year,0, user);
        itemRepo.save(message);
        Iterable<Item> items = itemRepo.findByAuthor(user);
        model.addAttribute("items",items);
        return "user";
    }
}
