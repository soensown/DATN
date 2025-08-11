package com.example.datn.Controller;

import com.example.datn.Model.ProductDetails;
import com.example.datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
                           @RequestParam(required = false) String editId,
                           Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDetails> list = (keyword != null && !keyword.isBlank())
                ? repo.findByDescriptionContainingIgnoreCase(keyword, pageable)
                : repo.findAll(pageable);

        ProductDetails detail = (editId != null)
                ? repo.findById(editId).orElse(new ProductDetails())
                : new ProductDetails();

        model.addAttribute("productDetail", detail); // dùng cho form
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("colors", colorRepo.findAll());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "/page/ProductDetail";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("productDetail") ProductDetails detail) {
        // Chỉ thêm mới (không cho phép override nếu ID tồn tại)
        if (repo.existsById(detail.getId())) {
            // Có thể thêm thông báo lỗi hoặc redirect
            return "redirect:/product_details/hienThi?error=duplicate";
        }
        repo.save(detail);
        return "redirect:/product_details/hienThi";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute ProductDetails detail) {
        if (!repo.existsById(detail.getId())) {
            // Không tồn tại ID thì out ra màn hình hiển thị
            return "redirect:/product_details/hienThi?error=notfound";
        }
        repo.save(detail);
        return "redirect:/product_details/hienThi";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        repo.deleteById(id);
        return "redirect:/product_details/hienThi";
    }
}
