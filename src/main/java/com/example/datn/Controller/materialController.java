package com.example.datn.Controller;

import com.example.datn.Model.discounts;
import com.example.datn.Model.material;
import com.example.datn.repository.discountsRepository;
import com.example.datn.repository.materialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/material")
public class materialController {
    @Autowired
    materialRepository materialRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listMaterial",materialRepo.findAll());
        return "...";//link này mapping fontend file html
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        materialRepo.deleteById(id);
        return "redirect:/material/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model,@PathVariable Integer id){
        model.addAttribute("listMaterial",materialRepo.findById(id).get());
        return "...";//link này mapping fontend file html
    }

    @PostMapping("/add")
    public String add(material material){
        materialRepo.save(material);
        return "redirect:/material/hienThi";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listMaterial", materialRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
