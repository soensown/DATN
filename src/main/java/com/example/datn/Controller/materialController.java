package com.example.datn.Controller;

import com.example.datn.Model.discounts;
import com.example.datn.Model.material;
import com.example.datn.repository.discountsRepository;
import com.example.datn.repository.materialRepository;
import com.example.datn.repository.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materials")
public class materialController {
    @Autowired
    materialRepository materialRepo;
    @Autowired
    productsRepository productsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("materials",materialRepo.findAll());
        model.addAttribute("products",productsRepo.findAll());
        return "/page/ProductMaterials";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        materialRepo.deleteById(id);
        return "redirect:/materials/hienThi";
    }
    @PostMapping("/add")
    public String add(material material){
        materialRepo.save(material);
        return "redirect:/materials/hienThi";
    }
    @PostMapping("/update")
    public String update(material material){
        materialRepo.save(material);
        return "redirect:/materials/hienThi";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listMaterial", materialRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
