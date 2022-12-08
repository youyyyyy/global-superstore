package com.ltp.globalsuperstore.controller;

import com.ltp.globalsuperstore.pojo.Item;
import com.ltp.globalsuperstore.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class StoreController {

    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("item", storeService.getItemById(id));
        return "form";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {

        if (item.getPrice() != null && item.getDiscount() != null && item.getPrice() <= item.getDiscount()) {
            result.rejectValue("price", "", "Price cannot be less than the discount");
        }
        if (result.hasErrors()) return "form";

        redirectAttributes.addFlashAttribute("status", storeService.submitItem(item));
        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", storeService.getItems());
        return "inventory";
    }
}
