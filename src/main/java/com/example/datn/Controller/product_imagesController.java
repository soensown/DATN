package com.example.datn.Controller;

import com.example.datn.Model.product_images;
import com.example.datn.repository.product_detailsRepository;
import com.example.datn.repository.product_imagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product_images")
public class product_imagesController {

    @Autowired
    private product_detailsRepository product_detailsRepo;

    @Autowired
    private product_imagesRepository product_imagesRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<product_images> pageProductImages = product_imagesRepo.findAll(pageable);

        model.addAttribute("listProduct_images", pageProductImages.getContent());
        model.addAttribute("totalPages", pageProductImages.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("listProduct_details", product_detailsRepo.findAll()); // dùng cho combobox hoặc form add
        return "/page/ProductImages";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute product_images productImage,
                      RedirectAttributes ra) {
        product_imagesRepo.save(productImage);
        ra.addFlashAttribute("successMessage", "Thêm ảnh sản phẩm thành công!");
        return "redirect:/product_images/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute product_images productImage,
                         RedirectAttributes ra) {
        product_imagesRepo.save(productImage);
        ra.addFlashAttribute("successMessage", "Cập nhật ảnh sản phẩm thành công!");
        return "redirect:/product_images/hienThi";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id,
                         RedirectAttributes ra) {
        product_imagesRepo.deleteById(id);
        ra.addFlashAttribute("successMessage", "Xóa ảnh sản phẩm thành công!");
        return "redirect:/product_images/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        product_images img = product_imagesRepo.findById(id).orElse(null);
        if (img != null) {
            model.addAttribute("productImage", img);
            model.addAttribute("listProduct_details", product_detailsRepo.findAll());
            return "/page/EditProductImage"; // Gợi ý: tạo file HTML riêng cho sửa
        }
        return "redirect:/product_images/hienThi";
    }
}
