package com.example.datn.Controller;

import com.example.datn.Model.Discounts;
import com.example.datn.repository.discountsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/discounts")
public class discountsController {
    @Autowired
    private discountsRepository discountRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Discounts> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = discountRepo.findByIdContainingOrDescriptionContainingIgnoreCase(keyword, keyword);
        } else {
            list = discountRepo.findAll();
        }
        model.addAttribute("discounts", list);
        model.addAttribute("discount", new Discounts()); // dùng cho form thêm mới
        model.addAttribute("keyword", keyword);
        return "page/discount";
    }

    @PostMapping("/add")
    public String addDiscount(@ModelAttribute("discount") @Valid Discounts discount,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("discounts", discountRepo.findAll());
            return "page/discount";
        }
        discountRepo.save(discount);
        return "redirect:/discounts/hienThi";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiscount(@PathVariable("id") String id) {
        discountRepo.deleteById(id);
        return "redirect:/discounts/hienThi";
    }

    @GetMapping("/edit/{id}")
    public String editDiscount(@PathVariable("id") String id, Model model) {
        Optional<Discounts> optional = discountRepo.findById(id);
        if (optional.isPresent()) {
            model.addAttribute("discount", optional.get());
            model.addAttribute("discounts", discountRepo.findAll());
            return "page/discount";
        } else {
            return "redirect:/discounts/hienThi";
        }
    }


    @PostMapping("/update")
    public String updateDiscount(@Valid @ModelAttribute("discount") Discounts discount,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("discounts", discountRepo.findAll());
            return "page/discount";
        }
        discountRepo.save(discount);
        return "redirect:/discounts/hienThi";
    }

}


