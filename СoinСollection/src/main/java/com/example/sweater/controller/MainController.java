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
public class MainController {
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/")
    public String greeting(Model model,
                           @AuthenticationPrincipal User user
    ) {
        if(user != null && user.getRoles().contains(Role.USER)){
            model.addAttribute("user","user");
        }
        Iterable<Item> messages = itemRepo.findAll();
        model.addAttribute("messages",messages);
        return "greeting";
    }

    @PostMapping("/")
    public String search(
            @RequestParam(required = false,defaultValue = "") String tag,
            Model model){
        Iterable<Item> messages = itemRepo.findAll();
        if(tag != null && !tag.isEmpty()){
            messages = itemRepo.findByTag(tag);
        }else {
            messages = itemRepo.findAll();
        }
        model.addAttribute("messages",messages);
        model.addAttribute("tag",tag);
        return "greeting";
    }

    @GetMapping("/user")
    public String main(
            @AuthenticationPrincipal User user,
            Model model){
        if(user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("isAdmin","admin");
        }
        return "user";
    }

    @PostMapping("/user")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam Integer year
    ){
        Item message = new Item(text,tag,year,user);
        itemRepo.save(message);
        return "user";
    }
}
