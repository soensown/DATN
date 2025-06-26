package com.example.datn.Controller;

import com.example.datn.Model.product_details;
import com.example.datn.Model.products;
import com.example.datn.repository.materialRepository;
import com.example.datn.repository.product_imagesRepository;
import com.example.datn.repository.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product_materials")
public class product_materialsController {
    @Autowired
    productsRepository productsRepository;
    @Autowired
    materialRepository materialRepository;
    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("products",productsRepository.findAll());
        model.addAttribute("materials",materialRepository.findAll());
        return "/page/ProductMaterials";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        productsRepository.deleteById(id);
        return "redirect:/product_details/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listProduct_details",productsRepository.findById(id).get());
        return "...";
    }

    @PostMapping("/add")
    public String add(products products){
        productsRepository.save(products);
        return "redirect:/product_details/hienThi";
    }
}
