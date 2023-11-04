
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;

@WebServlet(name="OrderItemQuantity", urlPatterns={"/orderitemquantity"})
public class OrderItemQuantity extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductService productService = new ProductService();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String button = request.getParameter("button");
        if (button.equals("minus") && quantity > 1) {
            productService.updateOrderItemQuantity(orderId, productId, quantity - 1);
        } else if (button.equals("plus")){
            productService.updateOrderItemQuantity(orderId, productId, quantity + 1);
        }
        response.sendRedirect("order");
    }
}
