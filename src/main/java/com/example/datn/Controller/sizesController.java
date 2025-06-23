package com.example.datn.Controller;

import com.example.datn.Model.categories;
import com.example.datn.Model.sizes;
import com.example.datn.repository.categoriesRepository;
import com.example.datn.repository.sizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/sizes")
public class sizesController {
    @Autowired
    sizesRepository sizesRepo;

@GetMapping("/hienThi")
public String hienThi(Model model) {
    model.addAttribute("listSizes", sizesRepo.findAll());
    return "/page/Sizes";
}

@GetMapping("/delete/{id}")
public String delete(@PathVariable("id") Integer id) {
    sizesRepo.deleteById(id);
    return "redirect:/sizes/hienThi";
}

    @PostMapping("/add")
    public String add(@ModelAttribute sizes size) {
        sizesRepo.save(size);
        return "redirect:/sizes/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute sizes size) {
        sizesRepo.save(size);
        return "redirect:/sizes/hienThi";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("listSizes", sizesRepo.findAll().stream()
                .filter(s -> s.getSize().toLowerCase().contains(keyword.toLowerCase()))
                .toList());
        return "/page/Sizes";
    }
}
