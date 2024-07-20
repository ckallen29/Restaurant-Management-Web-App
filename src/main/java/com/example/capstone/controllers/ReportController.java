package com.example.capstone.controllers;

import com.example.capstone.repositories.OrderRepository;
import com.example.capstone.repositories.UserRepository;
import com.example.capstone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/reports")
    public String showReportForm() {
        return "reports";
    }

    @PostMapping("/generateOrderReport")
    public String generateOrderReport(@RequestParam("reportType") String reportType,
                                    @RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "orderNumber", required = false) String orderNumber,
                                    HttpServletResponse response, Model model) throws IOException {
        switch (reportType) {
            case "allOrders":
                reportService.generateAllOrdersReport(response);
                break;
            case "ordersByUser":
                if (username != null && userRepository.existsByUsername(username)) {
                    reportService.generateOrdersByUserReport(username, response);
                } else {
                    model.addAttribute("errorMessage", "Username doesn't exist, please try again.");
                    return "reports";
                }
                break;
            case "orderByOrderNumber":
                if (orderNumber != null && orderRepository.existsByOrderNumber(orderNumber)) {
                    reportService.generateOrderByOrderNumberReport(orderNumber, response);
                } else {
                    model.addAttribute("errorMessage", "Order number doesn't exist, please try again.");
                    return "reports";
                }
                break;
            default:
                model.addAttribute("errorMessage", "Invalid report type, please try again.");
                return "reports";
        }
        return null; // return null because response is handled
    }
}
