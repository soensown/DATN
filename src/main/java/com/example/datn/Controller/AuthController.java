package com.example.datn.Controller;

import com.example.datn.Model.Roles;
import com.example.datn.Model.Users;
import com.example.datn.repository.rolesRepository;
import com.example.datn.repository.usersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class AuthController {
    private final usersRepository userRepository;
    private final rolesRepository roleRepository; // repo cho bảng roles
    private final PasswordEncoder passwordEncoder;

    public AuthController(usersRepository userRepository,
                          rolesRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "page/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Users());
        return "page/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") Users user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Roles roleUser = roleRepository.findByRoleCode("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        user.setRole(roleUser);


        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setDel(false);

        userRepository.save(user);

        return "redirect:/login?success";
    }
}
