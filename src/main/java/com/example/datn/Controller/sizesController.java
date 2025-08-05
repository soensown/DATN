package com.example.datn.Controller;


import com.example.datn.Model.Sizes;
import com.example.datn.repository.sizesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sizes")
public class sizesController {
    @Autowired
    sizesRepository sizesRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Sizes> pageSizes = (keyword != null && !keyword.trim().isEmpty()) ? sizesRepo.findBySizeContainingIgnoreCase(keyword.trim(), pageable) : sizesRepo.findAll(pageable);
        model.addAttribute("pageSizes",   pageSizes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages",  pageSizes.getTotalPages());
        model.addAttribute("keyword",     keyword);
        return "/page/Sizes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        sizesRepo.deleteById(id);
        return "redirect:/sizes/hienThi";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Sizes size,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "5") int sizePerPage, Model model) {
        if (sizesRepo.existsBySizeIgnoreCase(size.getSize())) {
            Pageable pageable = PageRequest.of(page, sizePerPage, Sort.by("id").descending());
            Page<Sizes> pageSizes = sizesRepo.findAll(pageable);
            model.addAttribute("pageSizes", pageSizes);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", pageSizes.getTotalPages());
            model.addAttribute("errorMessage", "Tên size đã tồn tại!");
            return "/page/Sizes";
        }
        sizesRepo.save(size);
        return "redirect:/sizes/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Sizes size) {
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