package com.example.datn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.datn.repository.brandsRepository;
import com.example.datn.Model.brands;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class brandsController {
    @Autowired
    brandsRepository brandsRepo;
    //Dường dẫn lưu file
    private final Path uploadDir = Paths.get("src/main/resources/static/uploads");

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<brands> pageBrands = (keyword != null && !keyword.trim().isEmpty()) ? brandsRepo.findByBrandNameContainingIgnoreCase(keyword.trim(), pageable) : brandsRepo.findAll(pageable);
        model.addAttribute("pageBrands", pageBrands);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBrands.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "/page/Brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        brands brand = brandsRepo.findById(id).orElse(null);
        if (brand != null) {
            try {
                deleteLogoFile(brand.getBrandLogo());
                brandsRepo.deleteById(id);
                ra.addFlashAttribute("successMessage", "Đã xoá thương hiệu!");
            } catch (IOException e) {
                ra.addFlashAttribute("errorMessage", "Lỗi xoá file: " + e.getMessage());
            }
        }
        return "redirect:/brands/hienThi";
    }

    @PostMapping("/add")
    public String add(@RequestParam("brandName") String brandName,
                      @RequestParam("logoFile") MultipartFile logoFile, RedirectAttributes ra) {
        try {
            String fileName = saveLogoFile(logoFile);
            brands brand = new brands(null, brandName, fileName);
            brandsRepo.save(brand);
            ra.addFlashAttribute("successMessage", "Thêm thương hiệu thành công!");
        } catch (IOException e) {
            ra.addFlashAttribute("errorMessage", "Lỗi upload file: " + e.getMessage());
        }
        return "redirect:/brands/hienThi";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("brandName") String brandName,
                         @RequestParam(value = "logoFile", required = false) MultipartFile logoFile, RedirectAttributes ra)
    { brands brand = brandsRepo.findById(id).orElse(null);
        if (brand == null) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy thương hiệu!");
            return "redirect:/brands/hienThi"; }
        brand.setBrandName(brandName);
        /* nếu người dùng chọn logo mới */
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                // xoá logo cũ
                deleteLogoFile(brand.getBrandLogo());
                // lưu logo mới
                String fileName = saveLogoFile(logoFile);
                brand.setBrandLogo(fileName);
            } catch (IOException e) {
                ra.addFlashAttribute("errorMessage", "Lỗi upload file: " + e.getMessage());
                return "redirect:/brands/hienThi";
            }
        }
        brandsRepo.save(brand);
        ra.addFlashAttribute("successMessage", "Cập nhật thương hiệu thành công!");
        return "redirect:/brands/hienThi";
    }

    /** Lưu file & trả về tên file */
    private String saveLogoFile(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
        String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName; // chỉ lưu tên file trong DB
    }
    /** Xoá logo khi cần */
    private void deleteLogoFile(String fileName) throws IOException {
        if (fileName != null && !fileName.isBlank()) {
            Files.deleteIfExists(uploadDir.resolve(fileName));
        }
    }
}