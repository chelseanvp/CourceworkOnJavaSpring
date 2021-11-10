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
        if(user != null && user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("isAdmin","admin");
        } else if(user != null && user.getRoles().contains(Role.USER)){
            model.addAttribute("user","user");
        }
        Iterable<Item> items = itemRepo.findAll();
        model.addAttribute("items",items);
        return "greeting";
    }

    @PostMapping("/")
    public String search(
            @RequestParam(required = false,defaultValue = "") String tag,
            Model model){
        Iterable<Item> items = itemRepo.findAll();
        if(tag != null && !tag.isEmpty()){
            items = itemRepo.findByTag(tag);
        }else {
            items = itemRepo.findAll();
        }
        model.addAttribute("items",items);
        model.addAttribute("tag",tag);
        return "greeting";
    }


}
