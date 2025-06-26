package com.example.datn.Controller;

import com.example.datn.Model.brands;
import com.example.datn.repository.brandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class brandsController {
    @Autowired
    brandsRepository brandsRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model) {
        model.addAttribute("listBrands", brandsRepo.findAll());
        return "/page/Brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        brandsRepo.deleteById(id);
        return "redirect:/brands/hienThi";
    }

    @PostMapping("/add")
    public String addBrand(@RequestParam("brandName") String brandName,
                           @RequestParam("logoFile") MultipartFile logoFile,
                           Model model) {

        try {
            // Thư mục lưu logo
            String uploadDir = "src/main/resources/static/uploads/";
            Files.createDirectories(Paths.get(uploadDir));


            String fileName = UUID.randomUUID() + "_" + logoFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.copy(logoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


            brands brand = new brands();
            brand.setBrandName(brandName);
            brand.setBrandLogo("/uploads/" + fileName); //

            brandsRepo.save(brand);

            model.addAttribute("message", "Thêm thương hiệu thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi lưu logo.");
        }

        return "redirect:/brands/hienThi";
    }

    @PostMapping("/update")
    public String updateBrand(@RequestParam("id") Integer id,
                              @RequestParam("brandName") String brandName,
                              @RequestParam(value = "logoFile", required = false) MultipartFile logoFile) {

        brands brand = brandsRepo.findById(id).orElse(null);
        if (brand == null) {
            return "redirect:/brands/hienThi";
        }

        brand.setBrandName(brandName);

        if (logoFile != null && !logoFile.isEmpty()) {
            try {

                String uploadDir = "src/main/resources/static/uploads/";
                Files.createDirectories(Paths.get(uploadDir));
                String fileName = UUID.randomUUID() + "_" + logoFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.copy(logoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


                brand.setBrandLogo("/uploads/" + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        brandsRepo.save(brand);
        return "redirect:/brands/hienThi";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("listBrands", brandsRepo.findAll().stream()
                .filter(b -> b.getBrandName().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getBrandLogo().toLowerCase().contains(keyword.toLowerCase()))
                .toList());
        return "/page/Brands";
    }
}
