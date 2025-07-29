package com.example.datn.Controller;

import com.example.datn.Model.order_items;
import com.example.datn.Model.orders;
import com.example.datn.Model.product_details;
import com.example.datn.repository.order_itemsRepository;
import com.example.datn.repository.ordersRepository;
import com.example.datn.repository.product_detailsRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PostMapping("/checkout")
    public String handleCheckout(
            @RequestParam("cartData") String cartData,
            @RequestParam("totalPrice") String totalPriceStr,
            @RequestParam("amountPaid") String amountPaidStr,
            HttpSession session,
            Model model
    ) {
        try {
            BigDecimal totalPrice = new BigDecimal(totalPriceStr.trim());
            BigDecimal amountPaid = new BigDecimal(amountPaidStr.trim());

            if (amountPaid.compareTo(totalPrice) < 0) {
                model.addAttribute("errorMessage", "❌ Tiền khách đưa không đủ!");
                return "/page/ErrosPos";
            }

            List<order_items> items = new ArrayList<>();
            String[] productLines = cartData.split(";");

            if (productLines.length == 0) {
                model.addAttribute("errorMessage", "❌ Giỏ hàng trống!");
                return "/page/ErrosPos";
            }

            for (String line : productLines) {
                String[] parts = line.split("\\|");
                if (parts.length != 3) {
                    model.addAttribute("errorMessage", "❌ Dữ liệu sản phẩm không hợp lệ!");
                    return "/page/ErrosPos";
                }

                String productDetailId = parts[0].trim();
                BigDecimal price = new BigDecimal(parts[1].trim());
                int quantity = Integer.parseInt(parts[2].trim());

                product_details pd = productDetailsRepository.findById(productDetailId).orElse(null);
                if (pd == null || pd.getProduct() == null) {
                    model.addAttribute("errorMessage", "❌ Không tìm thấy sản phẩm chi tiết!");
                    return "/page/ErrosPos";
                }

                order_items item = new order_items();
                item.setProductDetails(pd);
                item.setQuantity(quantity);
                item.setUnitPrice(pd.getProduct().getUnitPrice());
                item.setDiscountPrice(pd.getProduct().getDiscountPrice());
                item.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));

                items.add(item);
            }

            // Tạo đơn hàng
            orders order = new orders();
            order.setId(UUID.randomUUID().toString());
            order.setTotalPrice(totalPrice);
            order.setStatus("PAID");
            order.setCreatedDate(new Date());
            order.setUpdatedDate(new Date());
            order.setCreatedBy("admin");
            order.setUpdatedBy("admin");

            orderRepository.save(order);

            // Lưu các sản phẩm trong hóa đơn
            for (order_items item : items) {
                item.setOrder(order);
                orderItemsRepository.save(item);
            }

            session.setAttribute("successMessage", "✅ Thanh toán thành công!");
            return "redirect:/pos";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "❌ Lỗi xử lý thanh toán: " + e.getMessage());
            return "/page/ErrosPos";
        }
    }
}
