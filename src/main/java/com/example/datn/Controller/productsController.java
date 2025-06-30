package com.example.datn.Controller;

import com.example.datn.Model.product_details;
import com.example.datn.Model.products;
import com.example.datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class productsController {
    @Autowired
    categoriesRepository categoriesRepository;

    @Autowired
    brandsRepository brandsRepository;

    @Autowired
    productsRepository productsRepository;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listCategories",categoriesRepository.findAll());
        model.addAttribute("listBrands",brandsRepository.findAll());
        return "/page/Products";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        productsRepository.deleteById(id);
        return "redirect:/product_details/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listProduct_details",productsRepository.findById(id).get());
        return "...";//link này mapping fontend file html
    }

    @PostMapping("/add")
    public String add(products products){
        productsRepository.save(products);
        return "redirect:/product_details/hienThi";
    }
}
