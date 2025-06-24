package com.example.datn.Controller;

import com.example.datn.Model.material;
import com.example.datn.Model.productMaterialId;
import com.example.datn.Model.product_materials;
import com.example.datn.repository.materialRepository;
import com.example.datn.repository.product_materialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product_materials")
public class product_materialsController {
    @Autowired
    product_materialsRepository productMaterialRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listProductMaterial",productMaterialRepo.findAll());
        return "...";//link này mapping fontend file html
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        productMaterialRepo.deleteById(id);
        return "redirect:/product_materials/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listProductMaterialRepo",productMaterialRepo.findById(id).get());
        return "...";//link này mapping fontend file html
    }

    @PostMapping("/add")
    public String add(product_materials product_materials){
        productMaterialRepo.save(product_materials);
        return "redirect:/product_materials/hienThi";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listMaterial", productMaterialRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
