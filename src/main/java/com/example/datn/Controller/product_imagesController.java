package com.example.datn.Controller;

import com.example.datn.Model.ProductDetails;
import com.example.datn.Model.Product_images;
import com.example.datn.repository.product_detailsRepository;
import com.example.datn.repository.product_imagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product_images")
public class product_imagesController {

    @Autowired
    private final product_imagesRepository imageRepo;

    @Autowired
    private final product_detailsRepository detailRepo;


    private final Path uploadDir = Paths.get(System.getProperty("user.dir"), "updates");

    @GetMapping("/hienThi")
    public String showAll(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("listImages", imageRepo.findAll());
        model.addAttribute("listProduct_details", detailRepo.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", 1);
        return "/page/ProductImages";
    }

    @PostMapping("/add")
    public String addImage(@RequestParam("image_id") String imageId,
                           @RequestParam("product_detail_id") String detailId,
                           @RequestParam("imageFile") MultipartFile file,
                           @RequestParam(value = "is_thumbnail", required = false) String isThumb) {
        try {

            String imageUrl = saveThumbnail(file);

            ProductDetails detail = detailRepo.findById(detailId).orElse(null);
            if (detail == null) return "redirect:/product_images/hienThi?error=detailNotFound";

            Product_images image = new Product_images();
            image.setId(imageId);
            image.setProduct_details(detail);
            image.setImage_url(imageUrl);
            image.set_thumbnail(isThumb != null);

            imageRepo.save(image);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/product_images/hienThi?error=uploadFailed";
        }

        return "redirect:/product_images/hienThi";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") String id) {
        Product_images image = imageRepo.findById(id).orElse(null);
        if (image != null) {
            try {
                deleteThumbnailFile(image.getImage_url());
                imageRepo.deleteById(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/product_images/hienThi";
    }



    private String saveThumbnail(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);

        String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/updates/" + fileName;
    }

    private void deleteThumbnailFile(String filePath) throws IOException {
        if (filePath != null && !filePath.isBlank()) {
            String fileName = Paths.get(filePath).getFileName().toString();
            Files.deleteIfExists(uploadDir.resolve(fileName));
        }
    }
}
