package com.example.datn.Controller;

import com.example.datn.Model.product_details;

import com.example.datn.repository.product_detailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ServiceController {
    @Autowired
    private product_detailsRepository productDetailsRepository;
    @GetMapping("/")
    public String home(Model model){
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
    public String showPOS(Model model) {
        List<product_details> productDetails = productDetailsRepository.findAll();
        model.addAttribute("productDetails", productDetails);
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
