package com.example.datn.Controller;

import com.example.datn.Model.Material;
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
                          @RequestParam(required = false) String name) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Material> pageMaterial;

        if (name != null && !name.trim().isEmpty()) {
            pageMaterial = materialRepo.findByMaterialName(name.trim(), pageable);
        } else {
            pageMaterial = materialRepo.findAll(pageable);
        }

        model.addAttribute("listMaterial", pageMaterial.getContent());
        model.addAttribute("totalPages", pageMaterial.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);

        return "/page/Material";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Material material,
                      RedirectAttributes ra) {
        materialRepo.save(material);
        ra.addFlashAttribute("successMessage", "Thêm chất liệu thành công!");
        return "redirect:/material/hienThi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Material material,
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

    @GetMapping("/search")
    public String search(Model model, @RequestParam String type) {
        model.addAttribute("listMaterial", materialRepo.searchByType(type));
        return "/page/discounts"; // forward đến trang Thymeleaf
    }
}
