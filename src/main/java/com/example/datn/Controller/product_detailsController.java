package com.example.datn.Controller;

import com.example.datn.Model.categories;
import com.example.datn.Model.product_details;
import com.example.datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product_details")
public class product_detailsController {

    @Autowired
    private product_detailsRepository repo;

    @Autowired
    private productsRepository productRepo;

    @Autowired
    private colorsRepository colorRepo;

    @Autowired
    private sizesRepository sizeRepo;

    @GetMapping("/hienThi")
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<product_details> list = (keyword != null && !keyword.isBlank())
                ? repo.findByDescriptionContainingIgnoreCase(keyword, pageable)
                : repo.findAll(pageable);
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("colors", colorRepo.findAll());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "/page/ProductDetail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("productDetail", new product_details());

        return "/page/ProductDetail";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute product_details detail) {
        repo.save(detail);
        return "redirect:/product-details/list";
    }
}
