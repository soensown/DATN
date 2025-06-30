package com.example.datn.Controller;

import com.example.datn.Model.categories;
import com.example.datn.repository.categoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class categoriesController {
    @Autowired
    categoriesRepository categoriesRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false)   String keyword) {
        Pageable pageable       = PageRequest.of(page, size, Sort.by("id").descending());
        Page<categories> result = (keyword != null && !keyword.trim().isEmpty()) ? categoriesRepo.findByCategoryNameContainingIgnoreCase(keyword.trim(), pageable) : categoriesRepo.findAll(pageable);
        model.addAttribute("pageCategories", result);
        model.addAttribute("currentPage",    page);
        model.addAttribute("totalPages",     result.getTotalPages());
        model.addAttribute("keyword",        keyword);
        return "/page/Categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoriesRepo.deleteById(id);
        return "redirect:/categories/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute categories cat) {
        categoriesRepo.save(cat);
        return "redirect:/categories/hienThi";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute categories cat) {
        categoriesRepo.save(cat);
        return "redirect:/categories/hienThi";
    }
}