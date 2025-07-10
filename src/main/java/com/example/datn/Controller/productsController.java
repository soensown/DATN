package com.example.datn.Controller;

import com.example.datn.Model.products;
import com.example.datn.repository.productsRepository;
import com.example.datn.repository.brandsRepository;
import com.example.datn.repository.categoriesRepository;

import com.example.datn.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class productsController {

    @Autowired
    private productsRepository productsRepo;

    @Autowired
    private brandsRepository brandsRepo;

    @Autowired
    private categoriesRepository categoriesRepo;

    @Autowired
    private usersRepository usersRepo;
    private final Path uploadDir = Paths.get(System.getProperty("user.dir"), "updates");

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<products> pageProducts = (keyword != null && !keyword.trim().isEmpty())
                ? productsRepo.findByProductNameContainingIgnoreCase(keyword.trim(), pageable)
                : productsRepo.findAll(pageable);
        model.addAttribute("listUsers",usersRepo.findAll());
        model.addAttribute("listCategories",categoriesRepo.findAll());
        model.addAttribute("listBrands",brandsRepo.findAll());
        model.addAttribute("pageProducts", pageProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageProducts.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "/page/Products";
    }
    // thử commit lại
    @PostMapping("/add")
    public String add(@RequestParam("product_name") String productName,
                      @RequestParam("description") String description,
                      @RequestParam("category_id") String categoryId,
                      @RequestParam("unit_price") Long unitPrice,
                      @RequestParam(value = "discount_price", required = false) Long discountPrice,
                      @RequestParam(value = "is_discount", required = false) Boolean isDiscount,
                      @RequestParam(value = "is_special", required = false) Boolean isSpecial,
                      @RequestParam("brand_id") Integer brandId,
                      @RequestParam(value = "weight", required = false) String weight,
                      @RequestParam("created_by") String createdBy,
                      @RequestParam("thumbnail_file") MultipartFile thumbnailFile,
                      RedirectAttributes ra) {

        try {
            String fileName = saveThumbnail(thumbnailFile);

            products product = new products();
            product.setId(UUID.randomUUID().toString());
            product.setProductName(productName);
            product.setDescription(description);
            product.setCategories(categoriesRepo.findById(categoryId).orElse(null));
            product.setUnitPrice(unitPrice);
            product.setDiscountPrice(discountPrice);
            product.setIsDiscount(isDiscount != null && isDiscount);
            product.setIsSpecial(isSpecial != null && isSpecial);
            product.setBrands(brandsRepo.findById(brandId).orElse(null));
            product.setWeight(weight != null ? new java.math.BigDecimal(weight) : null);
            product.setCreatedBy(createdBy);
            product.setCreatedDate(new Date());
            product.setThumbnail(fileName);

            productsRepo.save(product);
            ra.addFlashAttribute("successMessage", "Thêm sản phẩm thành công!");
        } catch (IOException e) {
            ra.addFlashAttribute("errorMessage", "Lỗi upload ảnh: " + e.getMessage());
        }

        return "redirect:/products/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute products product,
                         @RequestParam(value = "thumbnail_file", required = false) MultipartFile thumbnailFile,
                         RedirectAttributes ra) {

        products existing = productsRepo.findById(product.getId()).orElse(null);
        if (existing == null) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm!");
            return "redirect:/products/hienThi";
        }

        try {
            // Cập nhật thông tin cơ bản
            existing.setProductName(product.getProductName());
            existing.setDescription(product.getDescription());
            existing.setUnitPrice(product.getUnitPrice());
            existing.setDiscountPrice(product.getDiscountPrice());
            existing.setIsDiscount(product.getIsDiscount() != null && product.getIsDiscount());
            existing.setIsSpecial(product.getIsSpecial() != null && product.getIsSpecial());
            existing.setBrands(product.getBrands());
            existing.setCategories(product.getCategories());
            existing.setWeight(product.getWeight());
            existing.setUpdatedDate(new Date());
            existing.setUpdatedBy("USER001"); // hoặc bạn có thể lấy từ session/người dùng hiện tại


            if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
                deleteThumbnailFile(existing.getThumbnail());
                String fileName = saveThumbnail(thumbnailFile);
                existing.setThumbnail(fileName);
            }

            productsRepo.save(existing);
            ra.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công!");
        } catch (IOException e) {
            ra.addFlashAttribute("errorMessage", "Lỗi khi xử lý ảnh: " + e.getMessage());
        }

        return "redirect:/products/hienThi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        products product = productsRepo.findById(id).orElse(null);
        if (product != null) {
            try {
                deleteThumbnailFile(product.getThumbnail());
                productsRepo.deleteById(id);
                ra.addFlashAttribute("successMessage", "Đã xoá sản phẩm!");
            } catch (IOException e) {
                ra.addFlashAttribute("errorMessage", "Lỗi xoá file: " + e.getMessage());
            }
        }
        return "redirect:/products/hienThi";
    }

    /** Lưu file & trả về đường dẫn */
    private String saveThumbnail(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
        String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return "/updates/" + fileName;
    }

    /** Xoá file khi cần */
    private void deleteThumbnailFile(String filePath) throws IOException {
        if (filePath != null && !filePath.isBlank()) {
            String fileName = Paths.get(filePath).getFileName().toString();
            Files.deleteIfExists(uploadDir.resolve(fileName));
        }
    }

}
