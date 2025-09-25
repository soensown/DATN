package com.example.datn.Controller;

import com.example.datn.Model.Order_items;
import com.example.datn.Model.Orders;
import com.example.datn.Model.ProductDetails;
import com.example.datn.repository.order_itemsRepository;
import com.example.datn.repository.ordersRepository;
import com.example.datn.repository.product_detailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class ordersController {

    @Autowired
    private order_itemsRepository orderItemsRepo;

    @Autowired
    private product_detailsRepository productDetailsRepo;

    @Autowired
    private ordersRepository ordersRepo;


    @GetMapping
    public String showAllOrders(Model model) {
        List<Orders> ordersList = ordersRepo.findByStatusNot("deleted");
        model.addAttribute("orders", ordersList);
        return "/page/Order";
    }


    @GetMapping("/details/{id}")
    public String viewOrderDetail(@PathVariable("id") String orderId, Model model) {
        Orders order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng: " + orderId));

        List<Order_items> items = orderItemsRepo.findByOrder_Id(orderId);
        List<ProductDetails> allProducts = productDetailsRepo.findAll();

        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("allProducts", allProducts);
        return "page/OrderDetail";
    }


    @PostMapping("/update")
    public String updateItems(@RequestParam("itemIds") List<Integer> itemIds,
                              @RequestParam("quantities") List<Integer> quantities,
                              RedirectAttributes redirectAttributes) {


        if (itemIds == null || itemIds.isEmpty()) {
            throw new IllegalArgumentException("Không có sản phẩm nào để cập nhật!");
        }

        String orderId = null;

        for (int i = 0; i < itemIds.size(); i++) {
            final int index = i;
            Order_items item = orderItemsRepo.findById(itemIds.get(i))
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy OrderItem ID: " + itemIds.get(index)));

            int qty = quantities.get(i);
            if (qty <= 0) {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0!");
            }

            BigDecimal price = (item.getDiscountPrice() != null && item.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0)
                    ? item.getDiscountPrice()
                    : item.getUnitPrice();

            item.setQuantity(qty);
            item.setTotalPrice(price.multiply(BigDecimal.valueOf(qty)));
            orderItemsRepo.save(item);

            if (orderId == null) {
                orderId = item.getOrder().getId();
            }
        }

        if (orderId != null) updateOrderTotal(orderId);
        redirectAttributes.addFlashAttribute("successMessage", " Cập nhật số lượng sản phẩm thành công!");

        return "redirect:/orders/details/" + orderId;
    }


    @PostMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable Integer itemId, RedirectAttributes redirectAttributes) {
        Order_items item = orderItemsRepo.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm trong hóa đơn"));
        String orderId = item.getOrder().getId();

        orderItemsRepo.deleteById(itemId);
        updateOrderTotal(orderId);
        redirectAttributes.addFlashAttribute("successMessage", " Sản phẩm đã được xóa khỏi hóa đơn!");

        return "redirect:/orders/details/" + orderId;
    }


    @PostMapping("/add")
    public String addItem(@RequestParam("orderId") String orderId,
                          @RequestParam("productDetailId") String productDetailId,
                          @RequestParam("quantity") Integer quantity,
                          RedirectAttributes redirectAttributes) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0!");
        }

        Orders order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng: " + orderId));

        ProductDetails pd = productDetailsRepo.findById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm: " + productDetailId));

        Order_items existingItem = orderItemsRepo.findByOrder_Id(orderId).stream()
                .filter(i -> i.getProductDetails().getId().equals(productDetailId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            int newQty = existingItem.getQuantity() + quantity;
            BigDecimal price = (existingItem.getDiscountPrice() != null && existingItem.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0)
                    ? existingItem.getDiscountPrice()
                    : existingItem.getUnitPrice();

            existingItem.setQuantity(newQty);
            existingItem.setTotalPrice(price.multiply(BigDecimal.valueOf(newQty)));
            orderItemsRepo.save(existingItem);
        } else {
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
        }

        updateOrderTotal(orderId);
        redirectAttributes.addFlashAttribute("successMessage", " Thêm sản phẩm vào hóa đơn thành công!");
        return "redirect:/orders/details/" + orderId;
    }


    private void updateOrderTotal(String orderId) {
        Orders order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng khi cập nhật tổng tiền"));

        List<Order_items> items = orderItemsRepo.findByOrder_Id(orderId);
        BigDecimal total = items.stream()
                .map(Order_items::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);
        ordersRepo.save(order);
    }
}
