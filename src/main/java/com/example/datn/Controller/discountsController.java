package com.example.datn.Controller;

import com.example.datn.Model.categories;
import com.example.datn.Model.discounts;
import com.example.datn.repository.categoriesRepository;
import com.example.datn.repository.discountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discounts")
public class discountsController {
    @Autowired
    discountsRepository discountsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listDiscounts",discountsRepo.findAll());
        return "...";//link này mapping fontend file html
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        discountsRepo.deleteById(id);
        return "redirect:/discounts/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listDiscounts",discountsRepo.findById(id).get());
        return "...";//link này mapping fontend file html
    }

    @PostMapping("/add")
    public String add(discounts discounts){
        discountsRepo.save(discounts);
        return "redirect:/discounts/hienThi";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listDiscounts", discountsRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
