package com.example.datn.Controller;

import com.example.datn.Model.Discounts;
import com.example.datn.Model.ProductDetails;

import com.example.datn.Model.Products;
import com.example.datn.Model.Users;
import com.example.datn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.text.NumberFormat;

@Controller
@RequestMapping("/")
public class ServiceController {
    @Autowired
    private product_detailsRepository productDetailsRepository;
    @Autowired
    private productsRepository productsRepository;
    @Autowired
    private discountsRepository discountsRepo;
    @Autowired
    private usersRepository usersRepository;
    @Autowired
    private rolesRepository rolesRepository;
    @GetMapping
    public String home(Model model) {
        List<Products> productList = productsRepository.findTop10ByOrderByCreatedDateDesc();

        Locale vietnamLocale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(vietnamLocale);

        List<Map<String, Object>> formattedProducts = new ArrayList<>();

        for (Products p : productList) {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", p.getId());
            productMap.put("productName", p.getProductName());
            productMap.put("thumbnail", p.getThumbnail());
            productMap.put("isDiscount", p.getIsDiscount());

            productMap.put("unitPriceFormatted", currencyFormatter.format(p.getUnitPrice()));

            if (Boolean.TRUE.equals(p.getIsDiscount())) {
                productMap.put("discountPriceFormatted", currencyFormatter.format(p.getDiscountPrice()));
            }

            formattedProducts.add(productMap);
        }

        model.addAttribute("listProduct", formattedProducts);
        return "/page/home";
    }

    @GetMapping("/DirectSales")
    public String DirectSales(Model model){
        model.addAttribute("newOrders", 57);
        model.addAttribute("onHoldOrders", 5);
        model.addAttribute("outOfStock", 15);
        model.addAttribute("totalOrders", 16247);
        model.addAttribute("newCustomers", 356);
        model.addAttribute("couponUsage", 72);
        model.addAttribute("payingCustomerPercent", 30);
        return "/page/DirectSales";
    }
    @GetMapping("/pos")
    public String showPOS(Model model,
                          @RequestParam(value = "phone", required = false) String phone) {

        if (phone != null && !phone.isEmpty()) {
            Optional<Users> customer = usersRepository.findByPhoneNumberAndRole(
                    phone, rolesRepository.getById("ROLE002")
            );
            if (customer.isPresent()) {
                model.addAttribute("customer", customer.get());
            } else {
                model.addAttribute("notFoundPhone", phone);
            }
        }

        List<ProductDetails> productDetails = productDetailsRepository.findAll();
        model.addAttribute("productDetails", productDetails);

        List<Discounts> discounts = discountsRepo.findAllValidDiscounts();
        model.addAttribute("discounts", discounts);

        List<Users> customers = usersRepository.findByRole_Id("ROLE002");
        model.addAttribute("customers", customers);

        return "/page/pos";
    }


    @GetMapping("/overview")
    public String overview(Model model){
        model.addAttribute("newOrders", 57);
        model.addAttribute("onHoldOrders", 5);
        model.addAttribute("outOfStock", 15);
        model.addAttribute("totalOrders", 16247);
        model.addAttribute("newCustomers", 356);
        model.addAttribute("couponUsage", 72);
        model.addAttribute("payingCustomerPercent", 30);
        return "/page/Overview";
    }
    @GetMapping("/listProduct")
    public String listProduct(Model model){
        model.addAttribute("newOrders", 57);
        model.addAttribute("onHoldOrders", 5);
        model.addAttribute("outOfStock", 15);
        model.addAttribute("totalOrders", 16247);
        model.addAttribute("newCustomers", 356);
        model.addAttribute("couponUsage", 72);
        model.addAttribute("payingCustomerPercent", 30);
        return "/page/ListProduct";
    }
}
