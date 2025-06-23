package com.example.datn.Controller;

import com.example.datn.Model.brands;
import com.example.datn.repository.brandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
public class brandsController {
    @Autowired
    brandsRepository brandsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model) {
        model.addAttribute("listBrands", brandsRepo.findAll());
        return "/page/Brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        brandsRepo.deleteById(id);
        return "redirect:/brands/hienThi";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute brands brand) {
        brandsRepo.save(brand);
        return "redirect:/brands/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute brands brand) {
        brandsRepo.save(brand);
        return "redirect:/brands/hienThi";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("listBrands", brandsRepo.findAll().stream()
                .filter(b -> b.getBrandName().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getBrandLogo().toLowerCase().contains(keyword.toLowerCase()))
                .toList());
        return "/page/Brands";
    }
}
