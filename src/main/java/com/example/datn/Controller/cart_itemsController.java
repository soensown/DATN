package com.example.datn.Controller;

import com.example.datn.Model.Cart_items;
import com.example.datn.Model.ProductDetails;
import com.example.datn.Model.Users;
import com.example.datn.repository.cart_itemsRepository;
import com.example.datn.repository.product_detailsRepository;
import com.example.datn.repository.usersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class cart_itemsController {
    @Autowired
    usersRepository usersRepository;
    @Autowired
    cart_itemsRepository cartItemsRepository;
    @Autowired
    product_detailsRepository productDetailsRepo;

    @GetMapping
    public String showCart(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }


        String username = principal.getName();
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
        List<Cart_items> cartItems = cartItemsRepository.findByUser(user);
        BigDecimal total = cartItems.stream()
                .map(item -> item.getProductDetails().getProduct().getUnitPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", total);
        return "/page/cart";
    }


    @PostMapping("/add")
    public String addToCart(@RequestParam("productDetailId") String productDetailId,
                            @RequestParam("quantity") int quantity,
                            Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Users user = usersRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        ProductDetails productDetail = productDetailsRepo.findById(productDetailId).orElse(null);
        if (productDetail == null) {
            return "redirect:/products";
        }

        Cart_items existingItem = cartItemsRepository.findByUserAndProductDetails(user, productDetail);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setUpdated_date(new Date());
            cartItemsRepository.save(existingItem);
        } else {
            Cart_items newItem = new Cart_items();
            newItem.setId(UUID.randomUUID().toString());
            newItem.setUser(user);
            newItem.setProductDetails(productDetail);
            newItem.setQuantity(quantity);
            newItem.setCreated_date(new Date());
            newItem.setCreated_by(user.getUsername());
            newItem.setUpdated_date(new Date());
            newItem.setUpdated_by(user.getUsername());
            cartItemsRepository.save(newItem);
        }

        return "redirect:/cart";
    }


    @PostMapping("/update/{id}")
    @ResponseBody
    public String updateCartItem(@PathVariable("id") String id,
                                 @RequestParam("quantity") int quantity,
                                 Principal principal) {
        if (principal == null) return "not_logged_in";

        String username = principal.getName();
        Users user = usersRepository.findByUsername(username).orElse(null);
        if (user == null) return "not_logged_in";

        Cart_items item = cartItemsRepository.findById(id).orElse(null);
        if (item != null && item.getUser().getId().equals(user.getId())) {
            item.setQuantity(quantity);
            item.setUpdated_date(new Date());
            item.setUpdated_by(user.getUsername());
            cartItemsRepository.save(item);
            return "success";
        }
        return "error";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam("cartItemId") String cartItemId,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {

        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        Optional<Cart_items> optionalItem = cartItemsRepository.findById(cartItemId);
        if (optionalItem.isPresent()) {
            Cart_items item = optionalItem.get();
            if (item.getUser().getId().equals(user.getId())) {
                item.setQuantity(quantity);
                item.setUpdated_date(new Date());
                item.setUpdated_by(user.getUsername());
                cartItemsRepository.save(item);
            }
        }

        return "redirect:/cart";
    }


    @PostMapping("/delete")
    public String deleteCartItem(@RequestParam("cartItemId") String cartItemId,
                                 HttpSession session) {

        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        Optional<Cart_items> optionalItem = cartItemsRepository.findById(cartItemId);
        if (optionalItem.isPresent()) {
            Cart_items item = optionalItem.get();
            if (item.getUser().getId().equals(user.getId())) {
                cartItemsRepository.delete(item);
            }
        }

        return "redirect:/cart";
    }
}
