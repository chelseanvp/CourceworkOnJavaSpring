package com.example.sweater.controller;

import com.example.sweater.domain.Item;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("{/id}")
    public String deleteItem(Model model,
                             @PathVariable User author,
                             @AuthenticationPrincipal User user)
    {

        if(author != user){
            return "redirect:/";
        }
        return "user";
    }

}
