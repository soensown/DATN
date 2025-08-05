package com.example.datn.Controller;

import com.example.datn.Model.Discounts;
import com.example.datn.repository.discountsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/discounts")
public class discountsController {

    @Autowired
    private discountsRepository discountsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false) String type) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Discounts> pageDiscounts;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // Tìm kiếm theo keyword (ví dụ tìm theo code hoặc description)
            pageDiscounts = discountsRepo.searchByDescription(keyword.trim(), pageable);
            model.addAttribute("keyword", keyword);
        } else if (type != null && !type.trim().isEmpty()) {
            // Tìm theo type nếu có
            pageDiscounts = discountsRepo.findByDiscountType(type.trim(), pageable);
            model.addAttribute("type", type);
        } else {
            // Hiển thị tất cả nếu không có từ khóa hoặc type
            pageDiscounts = discountsRepo.findAll(pageable);
        }

        model.addAttribute("listDiscounts", pageDiscounts.getContent());
        model.addAttribute("totalPages", pageDiscounts.getTotalPages());
        model.addAttribute("currentPage", page);

        return "/page/discounts"; // Tên file HTML
    }

    @PostMapping("/add")
    public String add(@Valid Discounts discount,
                      BindingResult result,
                      RedirectAttributes ra,
                      Model model,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "5") int size) {

        if (result.hasErrors()) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<Discounts> pageDiscounts = discountsRepo.findAll(pageable);
            model.addAttribute("listDiscounts", pageDiscounts.getContent());
            model.addAttribute("totalPages", pageDiscounts.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("errorMessage", "Thêm mã giảm giá thất bại.");
            return "/page/discounts";
        }

        discountsRepo.save(discount);
        ra.addFlashAttribute("successMessage", "Thêm mã giảm giá thành công!");
        return "redirect:/discounts/hienThi";
    }

    @PostMapping("/update")
    public String update(@Valid Discounts discount,
                         BindingResult result,
                         RedirectAttributes ra,
                         Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size) {

        if (result.hasErrors()) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<Discounts> pageDiscounts = discountsRepo.findAll(pageable);
            model.addAttribute("listDiscounts", pageDiscounts.getContent());
            model.addAttribute("totalPages", pageDiscounts.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("errorMessage", "Cập nhật mã giảm giá thất bại.");
            return "/page/discounts";
        }

        discountsRepo.save(discount);
        ra.addFlashAttribute("successMessage", "Cập nhật mã giảm giá thành công!");
        return "redirect:/discounts/hienThi";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, RedirectAttributes ra) {
        Discounts discount = discountsRepo.findById(id).orElse(null);
        if (discount != null) {
            discountsRepo.deleteById(id);
            ra.addFlashAttribute("successMessage", "Đã xoá mã giảm giá!");
        } else {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy mã để xoá!");
        }
        return "redirect:/discounts/hienThi";
    }
}
