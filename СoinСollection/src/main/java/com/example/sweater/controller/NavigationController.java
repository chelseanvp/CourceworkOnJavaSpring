package com.example.sweater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/faqs")
    public String greeting(
            Model model
    ) {
        return "FAQsPageView";
    }
}
