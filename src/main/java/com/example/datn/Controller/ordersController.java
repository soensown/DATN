package com.example.datn.Controller;

import com.example.datn.Model.Order_items;
import com.example.datn.Model.Orders;
import com.example.datn.Model.Product_details;
import com.example.datn.repository.order_itemsRepository;
import com.example.datn.repository.ordersRepository;
import com.example.datn.repository.product_detailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class ordersController {

    @Autowired
    order_itemsRepository orderItemsRepo;

    @Autowired
    product_detailsRepository productDetailsRepo;

    @Autowired
    ordersRepository ordersRepo;


    @GetMapping
    public String showAllOrders(Model model) {
        List<Orders> ordersList = ordersRepo.findByStatusNot("deleted");
        model.addAttribute("orders", ordersList);
        return "/page/Order"; // Giao diện chính
    }

    // ✅ Lấy chi tiết sản phẩm trong đơn hàng (load vào modal qua JS fetch)
    @GetMapping("/details/{orderId}")
    public String getOrderItems(@PathVariable String orderId, Model model) {
        List<Order_items> orderItems = orderItemsRepo.findByOrder_Id(orderId);
        List<Product_details> productDetails = productDetailsRepo.findAll();

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("productDetails", productDetails);

        return "orders/order_modal"; // Không còn dùng fragment
    }

    // ✅ Cập nhật số lượng sản phẩm trong đơn
    @PostMapping("/update")
    public String updateItems(@RequestParam("itemIds") List<Integer> itemIds,
                              @RequestParam("quantities") List<Integer> quantities) {

        for (int i = 0; i < itemIds.size(); i++) {
            Order_items item = orderItemsRepo.findById(itemIds.get(i)).orElseThrow();
            int qty = quantities.get(i);

            item.setQuantity(qty);
            BigDecimal price = (item.getDiscountPrice() != null && item.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0)
                    ? item.getDiscountPrice()
                    : item.getUnitPrice();
            item.setTotalPrice(price.multiply(BigDecimal.valueOf(qty)));

            orderItemsRepo.save(item);
        }

        String orderId = orderItemsRepo.findById(itemIds.get(0)).get().getOrder().getId();
        return "redirect:/orders/details/" + orderId;
    }

    // ✅ Xoá sản phẩm khỏi đơn hàng
    @PostMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        Order_items item = orderItemsRepo.findById(itemId).orElseThrow();
        String orderId = item.getOrder().getId();
        orderItemsRepo.deleteById(itemId);
        return "redirect:/orders/details/" + orderId;
    }

    // ✅ Thêm sản phẩm mới vào đơn hàng
    @PostMapping("/add")
    public String addItem(@RequestParam("orderId") String orderId,
                          @RequestParam("productDetailId") String productDetailId,
                          @RequestParam("quantity") Integer quantity) {

        Orders order = ordersRepo.findById(orderId).orElseThrow();
        Product_details pd = productDetailsRepo.findById(productDetailId).orElseThrow();

        Order_items item = new Order_items();
        item.setOrder(order);
        item.setProductDetails(pd);
        item.setQuantity(quantity);
        item.setUnitPrice(pd.getProduct().getUnitPrice());
        item.setDiscountPrice(pd.getProduct().getDiscountPrice());

        BigDecimal price = (item.getDiscountPrice() != null && item.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0)
                ? item.getDiscountPrice()
                : item.getUnitPrice();
        item.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));

        orderItemsRepo.save(item);
        return "redirect:/orders/details/" + orderId;
    }
}
