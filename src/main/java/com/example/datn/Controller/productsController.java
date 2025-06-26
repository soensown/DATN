package com.example.datn.Controller;

import com.example.datn.Model.product_details;
import com.example.datn.repository.product_detailsRepository;
import com.example.datn.repository.product_imagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class productsController {
    @Autowired
    product_detailsRepository product_detailsRepo;

    @Autowired
    product_imagesRepository product_imagesRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listProduct_details",product_detailsRepo.findAll());
        model.addAttribute("listProduct_images",product_imagesRepo.findAll());
        return "/page/Products";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        product_detailsRepo.deleteById(id);
        return "redirect:/product_details/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listProduct_details",product_detailsRepo.findById(id).get());
        return "...";//link này mapping fontend file html
    }

    @PostMapping("/add")
    public String add(product_details product_details){
        product_detailsRepo.save(product_details);
        return "redirect:/product_details/hienThi";
    }
}
