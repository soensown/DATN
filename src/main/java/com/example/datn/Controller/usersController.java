package com.example.datn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.datn.repository.usersRepository;
import com.example.datn.repository.rolesRepository;
import com.example.datn.Model.users;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/staff")
public class usersController {
    @Autowired
    private usersRepository usersRepo;
    @Autowired
    private rolesRepository rolesRepo;

    @GetMapping("/list")
    public String listStaff(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size,
                            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<users> pageUsers = (keyword != null && !keyword.trim().isEmpty())
                ? usersRepo.searchStaff(keyword.trim(), pageable)
                : usersRepo.findAllStaff(pageable);

        model.addAttribute("pageUsers", pageUsers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageUsers.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", new users());           // cho form thêm
        model.addAttribute("roles", rolesRepo.findAll());  // dropdown chọn role
        return "page/staff";
    }

    /* ---------- THÊM MỚI ---------- */
    @PostMapping("/add")
    public String add(@ModelAttribute users user, RedirectAttributes ra) {
        if (user.getId() != null && !user.getId().isEmpty()) {
            ra.addFlashAttribute("msg", "ID đã tồn tại!");
            return "redirect:/staff/list";
        }
        user.setId(UUID.randomUUID().toString());
        usersRepo.save(user);
        ra.addFlashAttribute("msg", "Thêm nhân viên thành công!");
        return "redirect:/staff/list";
    }

    /* ---------- CHUẨN BỊ FORM SỬA ---------- */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(required = false) String keyword,
                       Model model, RedirectAttributes ra) {

        users user = usersRepo.findById(id).orElse(null);
        if (user == null) {
            ra.addFlashAttribute("msg", "Nhân viên không tồn tại!");
            return "redirect:/staff/list";
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<users> pageUsers = (keyword != null && !keyword.trim().isEmpty())
                ? usersRepo.searchStaff(keyword.trim(), pageable)
                : usersRepo.findAllStaff(pageable);

        model.addAttribute("user", user);
        model.addAttribute("pageUsers", pageUsers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageUsers.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("roles", rolesRepo.findAll());
        return "page/staff";
    }

    /* ---------- CẬP NHẬT ---------- */
    @PostMapping("/update")
    public String update(@ModelAttribute users user, RedirectAttributes ra) {
        if (user.getId() == null || !usersRepo.existsById(user.getId())) {
            ra.addFlashAttribute("msg", "Nhân viên không tồn tại!");
            return "redirect:/staff/list";
        }
        usersRepo.save(user);
        ra.addFlashAttribute("msg", "Cập nhật thành công!");
        return "redirect:/staff/list";
    }

    /* ---------- XOÁ ---------- */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            ra.addFlashAttribute("msg", "Xoá thành công!");
        } else {
            ra.addFlashAttribute("msg", "Nhân viên không tồn tại!");
        }
        return "redirect:/staff/list";
    }
}
