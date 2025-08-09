package com.example.datn.Controller;

import com.example.datn.Model.Order_items;
import com.example.datn.Model.Orders;
import com.example.datn.Model.ProductDetails;
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

            List<Order_items> items = new ArrayList<>();
            String[] productLines = cartData.split(";");

            if (productLines.length == 0) {
                model.addAttribute("errorMessage", "❌ Giỏ hàng trống!");
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
                    if (pd.getProduct().getDiscountPrice() == null){
                        pd.getProduct().setDiscountPrice(pd.getProduct().getUnitPrice());
                    }
                    item.setDiscountPrice(pd.getProduct().getDiscountPrice());
                    item.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));
                    items.add(item);

                }
            }

            // Tạo đơn hàng
            Orders order = new Orders();
            order.setId(UUID.randomUUID().toString());
            order.setTotalPrice(totalPrice);
            order.setStatus("PAID");
            order.setCreatedDate(new Date());
            order.setUpdatedDate(new Date());
            order.setCreatedBy("admin");
            order.setUpdatedBy("admin");

            orderRepository.save(order);

            // Lưu các sản phẩm trong hóa đơn
            for (Order_items item : items) {
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
