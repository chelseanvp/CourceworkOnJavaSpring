package com.example.sweater.controller;

import com.example.sweater.domain.Item;
import com.example.sweater.repos.ItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("{item}")
    public String showItemInfo(Model model, @RequestParam Integer itemId) {
        Item item = itemRepo.findById(itemId);
        item.setVisits(item.getVisits() + 1);
        itemRepo.save(item);
        model.addAttribute("item", item);

        return "ownItemPageView";
    }


    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit/{item}")
    public String editItem(
            Model model,
            @RequestParam Integer itemId
    ){
        Item item = itemRepo.findById(itemId);
        model.addAttribute("item", item);

        return "itemEditView";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/edit/save/{item}")
    public String userItemSave(
            Model model,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam Integer year,
            @RequestParam Integer itemId
    ){
        Item currentItem = itemRepo.findById(itemId);
        currentItem.setText(text);
        currentItem.setTag(tag);
        currentItem.setYear(year);
        itemRepo.save(currentItem);

        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/delete/{item}")
    public String deleteItem(
            Model model,
            @RequestParam Integer itemId
    ){
        Item item = itemRepo.findById(itemId);
        itemRepo.delete(item);

        return "redirect:/user";
    }
}
