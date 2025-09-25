package com.example.datn.Controller;

import com.example.datn.Model.*;
import com.example.datn.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
@RequestMapping("/pos")
@RequiredArgsConstructor
public class PosController {

    @Autowired
    private ordersRepository orderRepository;

    @Autowired
    private order_itemsRepository orderItemsRepository;

    @Autowired
    private product_detailsRepository productDetailsRepository;

    @Autowired
    private discountsRepository discountsRepository;

    @Autowired
    private usersRepository usersRepository;

    @Autowired
    private rolesRepository rolesRepository;

    @PostMapping("/checkout")
    public String handleCheckout(
            @RequestParam(value = "idDiscount", required = false) String idDiscount,
            @RequestParam("cartData") String cartData,
            @RequestParam("totalPrice") String totalPriceStr,
            @RequestParam("amountPaid") String amountPaidStr,
            @RequestParam(value = "customerId", required = false) String customerId,
            @RequestParam(value = "newFullName", required = false) String newFullName,
            @RequestParam(value = "newPhone", required = false) String newPhone,
            @RequestParam(value = "newAddress", required = false) String newAddress,
            HttpSession session,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            BigDecimal totalPrice = new BigDecimal(totalPriceStr.trim());
            BigDecimal amountPaid = new BigDecimal(amountPaidStr.trim());

            if (amountPaid.compareTo(totalPrice) < 0) {
                model.addAttribute("errorMessage", "Tiền khách đưa không đủ!");
                return "/page/ErrosPos";
            }

            // ===== Parse giỏ hàng =====
            List<Order_items> items = new ArrayList<>();
            String[] productLines = cartData.split(";");
            if (productLines.length == 0) {
                model.addAttribute("errorMessage", "Giỏ hàng trống!");
                return "/page/ErrosPos";
            }

            for (String line : productLines) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String productDetailId = parts[0];
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    int quantity = Integer.parseInt(parts[3].trim());

                    ProductDetails pd = productDetailsRepository.findById(productDetailId).orElse(null);
                    if (pd == null) continue;

                    Order_items item = new Order_items();
                    item.setProductDetails(pd);
                    item.setQuantity(quantity);
                    item.setUnitPrice(pd.getProduct().getUnitPrice());
                    if (pd.getProduct().getDiscountPrice() == null) {
                        pd.getProduct().setDiscountPrice(pd.getProduct().getUnitPrice());
                    }
                    item.setDiscountPrice(pd.getProduct().getDiscountPrice());
                    item.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));
                    items.add(item);
                }
            }

            // ===== Tạo Order =====
            Orders order = new Orders();
            order.setId(UUID.randomUUID().toString());
            order.setTotalPrice(totalPrice);
            order.setStatus("PAID");
            order.setCreatedDate(new Date());
            order.setUpdatedDate(new Date());
            order.setCreatedBy(authentication.getName());
            order.setUpdatedBy(authentication.getName());

            if (idDiscount != null && !idDiscount.isEmpty()) {
                Discounts discount = discountsRepository.findById(idDiscount).orElse(null);
                if (discount != null) {
                    order.setDiscount(discount);
                }
            }

            // ===== Xử lý khách hàng =====
            Users customer = null;
            if (newFullName != null && !newFullName.trim().isEmpty()
                    && newPhone != null && !newPhone.trim().isEmpty()) {

                // Kiểm tra trùng số điện thoại
                Optional<Users> existing = usersRepository.findByPhoneNumberAndRole(
                        newPhone, rolesRepository.getById("ROLE002")
                );

                if (existing.isPresent()) {
                    customer = existing.get();
                } else {
                    customer = new Users();
                    customer.setId(UUID.randomUUID().toString());
                    customer.setFullName(newFullName);
                    customer.setPhoneNumber(newPhone);
                    customer.setAddress(newAddress);
                    customer.setRole(rolesRepository.getById("ROLE002"));

                    // bổ sung các field bắt buộc
                    customer.setUsername(newPhone);
                    customer.setPassword("123"); // TODO: mã hóa BCrypt sau
                    customer.setEmail("");
                    customer.setStatus("Active");
                    customer.setDel(false);
                    customer.setCreatedDate(LocalDateTime.now());
                    customer.setUpdatedDate(LocalDateTime.now());
                    customer.setCreatedBy("POS");
                    customer.setUpdatedBy("POS");

                    usersRepository.save(customer);
                }
            } else {
                // Nếu không nhập khách mới thì dùng khách hàng đã chọn
                if (customerId == null || customerId.trim().isEmpty()) {
                    customerId = "USER003"; // khách vãng lai
                }
                customer = usersRepository.findById(customerId).orElse(null);
            }

            if (customer != null) {
                order.setUser(customer);
            }

            // ===== Lưu Order và Order_items =====
            orderRepository.save(order);
            for (Order_items item : items) {
                item.setOrder(order);
                orderItemsRepository.save(item);
            }

            session.setAttribute("successMessage", "Thanh toán thành công!");
            return "redirect:/pos";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Lỗi xử lý thanh toán: " + e.getMessage());
            return "/page/ErrosPos";
        }
    }
}
