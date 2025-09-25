package com.example.datn.Controller;

import com.example.datn.Model.Colors;
import com.example.datn.repository.colorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/colors")
public class colorsController {

    @Autowired
    private colorsRepository colorsRepo;


    @GetMapping("/hienThi")
    public String hienThi(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Colors> pageColors = colorsRepo.findAll(pageable);

        model.addAttribute("listColors", pageColors.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageColors.getTotalPages());
        model.addAttribute("keyword", ""); // để khởi tạo ô input search

        return "/page/Coler";
    }


    @GetMapping("/search")
    public String search(Model model, @RequestParam String tenORma) {
        if (tenORma == null || tenORma.trim().isEmpty()) {
            model.addAttribute("errorMessage", "Vui lòng nhập từ khóa tìm kiếm.");
            model.addAttribute("listColors", colorsRepo.findAll());
        } else {
            List<Colors> result = colorsRepo.searchByNameOrCode(tenORma.trim());
            if (result.isEmpty()) {
                model.addAttribute("infoMessage", "Không tìm thấy kết quả nào.");
            }
            model.addAttribute("listColors", result);
        }

        model.addAttribute("keyword", tenORma);
        model.addAttribute("currentPage", 0); // reset page nếu dùng template phân trang
        model.addAttribute("totalPages", 1);

        return "/page/Coler";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute Colors color, RedirectAttributes ra) {
        if (color.getColorName() == null || color.getColorName().isBlank() ||
                color.getColorCode() == null || color.getColorCode().isBlank()) {
            ra.addFlashAttribute("errorMessage", "Tên màu và mã màu không được để trống.");
            return "redirect:/colors/hienThi";
        }

        colorsRepo.save(color);
        ra.addFlashAttribute("successMessage", "Thêm màu thành công!");
        return "redirect:/colors/hienThi";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute Colors color, RedirectAttributes ra) {
        if (color.getId() == null || !colorsRepo.existsById(color.getId())) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy màu để cập nhật.");
            return "redirect:/colors/hienThi";
        }

        if (color.getColorName() == null || color.getColorName().isBlank() ||
                color.getColorCode() == null || color.getColorCode().isBlank()) {
            ra.addFlashAttribute("errorMessage", "Tên màu và mã màu không được để trống.");
            return "redirect:/colors/hienThi";
        }

        colorsRepo.save(color);
        ra.addFlashAttribute("successMessage", "Cập nhật thành công!");
        return "redirect:/colors/hienThi";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes ra) {
        if (!colorsRepo.existsById(id)) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy màu cần xoá.");
        } else {
            colorsRepo.deleteById(id);
            ra.addFlashAttribute("successMessage", "Xoá màu thành công!");
        }
        return "redirect:/colors/hienThi";
    }
}
