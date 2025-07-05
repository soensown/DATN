package com.example.datn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.datn.repository.rolesRepository;
import com.example.datn.Model.roles;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
@RequestMapping("/roles")
public class rolesController {
    @Autowired
    private rolesRepository rolesRepo;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<roles> pageRoles = (keyword != null && !keyword.trim().isEmpty()) ? rolesRepo.findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(keyword.trim(), keyword.trim(), pageable) : rolesRepo.findAll(pageable);
        model.addAttribute("pageRoles", pageRoles);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageRoles.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("role", new roles());   // cho form thêm
        return "page/roles";
    }
    /* ------------ ADD ------------ */
    @PostMapping("/add")
    public String add(@ModelAttribute roles role, RedirectAttributes ra) {
        if (role.getId() == null || role.getId().isEmpty()) {
            role.setId(UUID.randomUUID().toString());
        }
        rolesRepo.save(role);
        ra.addFlashAttribute("msg", "Thêm vai trò thành công!");
        return "redirect:/roles/list";
    }
    /* ------------ EDIT FORM ------------ */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(required = false) String keyword,
                       Model model, RedirectAttributes ra) {
        roles role = rolesRepo.findById(id).orElse(null);
        if (role == null) {
            ra.addFlashAttribute("msg", "Vai trò không tồn tại!");
            return "redirect:/roles/list";
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<roles> pageRoles = (keyword != null && !keyword.trim().isEmpty()) ? rolesRepo.findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(keyword.trim(), keyword.trim(), pageable) : rolesRepo.findAll(pageable);
        model.addAttribute("role", role);
        model.addAttribute("pageRoles", pageRoles);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageRoles.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "page/roles";
    }
    /* ------------ UPDATE ------------ */
    @PostMapping("/update")
    public String update(@ModelAttribute roles role, RedirectAttributes ra) {
        rolesRepo.save(role);
        ra.addFlashAttribute("msg", "Cập nhật vai trò thành công!");
        return "redirect:/roles/list";
    }
    /* ------------ DELETE ------------ */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        if (rolesRepo.existsById(id)) {
            rolesRepo.deleteById(id);
            ra.addFlashAttribute("msg", "Xoá vai trò thành công!");
        } else {
            ra.addFlashAttribute("msg", "Vai trò không tồn tại!");
        }
        return "redirect:/roles/list";
    }
}
