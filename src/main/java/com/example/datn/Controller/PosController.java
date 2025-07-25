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
    private final ordersRepository orderRepository;
    @Autowired
    private final order_itemsRepository orderItemsRepository;
    @Autowired
    private final product_detailsRepository productDetailsRepository;


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
                model.addAttribute("errorMessage", "Tiền khách đưa không đủ!");
                return "/page/ErrosPos";
            }

            // Parse cartData: mỗi dòng có dạng id|name|price|quantity
            List<order_items> items = new ArrayList<>();
            String[] productLines = cartData.split(";");

            for (String line : productLines) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String productDetailId = parts[0];
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    int quantity = Integer.parseInt(parts[3].trim());

                    product_details pd = productDetailsRepository.findById(productDetailId).orElse(null);
                    if (pd == null) continue;

                    order_items item = new order_items();
                    item.setProductDetails(pd);
                    item.setQuantity(quantity);
                    item.setUnitPrice(pd.getProduct().getUnitPrice());
                    if (pd.getProduct().getDiscountPrice() == null){
                        pd.getProduct().setDiscountPrice(pd.getProduct().getUnitPrice());
                    }
                    item.setDiscountPrice(pd.getProduct().getDiscountPrice());
                    item.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));
                    items.add(item);
                }
            }

            orders order = new orders();
            order.setId(UUID.randomUUID().toString());
            order.setTotalPrice(totalPrice);
            order.setStatus("PAID");
            order.setCreatedDate(new Date());
            order.setUpdatedDate(new Date());
            order.setCreatedBy("admin");
            order.setUpdatedBy("admin");

            orderRepository.save(order);

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
