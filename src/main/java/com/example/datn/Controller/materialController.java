package com.example.datn.Controller;

import com.example.datn.Model.material;
import com.example.datn.repository.materialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/material")
public class materialController {

    @Autowired
    private materialRepository materialRepo;

    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(required = false) String type) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<material> pageMaterial;

        if (type != null && !type.trim().isEmpty()) {
            pageMaterial = materialRepo.findByTypeContainingIgnoreCase(type.trim(), pageable);
        } else {
            pageMaterial = materialRepo.findAll(pageable);
        }

        model.addAttribute("listMaterial", pageMaterial.getContent());
        model.addAttribute("totalPages", pageMaterial.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("type", type);

        return "/page/Material"; // ← thay đúng tên file HTML
    }

    @PostMapping("/add")
    public String add(@ModelAttribute material material,
                      RedirectAttributes ra) {
        materialRepo.save(material);
        ra.addFlashAttribute("successMessage", "Thêm chất liệu thành công!");
        return "redirect:/material/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute material material,
                         RedirectAttributes ra) {
        materialRepo.save(material);
        ra.addFlashAttribute("successMessage", "Cập nhật chất liệu thành công!");
        return "redirect:/material/hienThi";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes ra) {
        materialRepo.deleteById(id);
        ra.addFlashAttribute("successMessage", "Xoá chất liệu thành công!");
        return "redirect:/material/hienThi";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        material m = materialRepo.findById(id).orElse(null);
        model.addAttribute("material", m);
        return "/page/MaterialEdit"; // Hoặc dùng chung form
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listMaterial", materialRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
