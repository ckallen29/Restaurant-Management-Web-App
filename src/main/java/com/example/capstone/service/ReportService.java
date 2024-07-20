package com.example.capstone.service;

import com.example.capstone.domain.Order;
import com.example.capstone.domain.User;
import com.example.capstone.repositories.OrderRepository;
import com.example.capstone.repositories.UserRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public void generateAllOrdersReport(HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAllByOrderByUserUsernameAsc();
        generatePdf(response, orders);
    }

    public void generateOrdersByUserReport(String username, HttpServletResponse response) throws IOException {
        User user = userRepository.findByUsername(username);
        List<Order> orders = orderRepository.findByUserOrderByDateCreatedAsc(user);
        generatePdf(response, orders);
    }

    public void generateOrderByOrderNumberReport(String orderNumber, HttpServletResponse response) throws IOException {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        generatePdf(response, List.of(order));
    }

    private void generatePdf(HttpServletResponse response, List<Order> orders) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=order_report.pdf");

        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        if (orders.isEmpty()) {
            document.add(new Paragraph("No orders available to generate the report."));
        } else {
            document.add(new Paragraph("Order Report"));

            Table table = new Table(new float[]{4, 4, 4});
            table.addCell("Order Number");
            table.addCell("User");
            table.addCell("Date Created");

            for (Order order : orders) {
                table.addCell(order.getOrderNumber());
                table.addCell(order.getUser().getUsername());
                table.addCell(order.getDateCreated().toString());
            }

            document.add(table);
        }

        document.close();
    }
}
