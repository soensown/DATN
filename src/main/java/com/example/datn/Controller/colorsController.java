package com.example.datn.Controller;

import com.example.datn.Model.categories;
import com.example.datn.Model.colors;
import com.example.datn.repository.categoriesRepository;
import com.example.datn.repository.colorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/colors")
public class colorsController {
    @Autowired
    colorsRepository colorsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model){
        model.addAttribute("listColors",colorsRepo.findAll());
        return "/page/Coler";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam()Integer id){
        colorsRepo.deleteById(id);
        return "redirect:/colors/hienThi";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String tenORma) {
        model.addAttribute("listColors", colorsRepo.searchByNameOrCode(tenORma));
        return "/page/Coler"; // forward đến trang Thymeleaf
    }
    //test git
    @PostMapping("/add")
    public String add(colors colors){
//        colors.setId(colors.getId());
        colorsRepo.save(colors);
        return "redirect:/colors/hienThi";
    }
    @PostMapping("/update")
    public String update(colors colors){
        colorsRepo.save(colors);
        return "redirect:/colors/hienThi";
    }
}
