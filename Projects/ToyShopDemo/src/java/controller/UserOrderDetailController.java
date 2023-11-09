package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.OrderItem;
import model.ShippingAddress;
import service.ProductService;
import service.UserService;

@WebServlet(name = "UserOrderDetailController", urlPatterns = {"/userorderdetail"})
public class UserOrderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductService productService = new ProductService();
        UserService userService = new UserService();
        
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = productService.getOrderById(id);
        ShippingAddress shippingAddress = userService.getShippingAddressInfo(order.getShippingAddressId());
        List<OrderItem> orderItems = productService.getOrderItemsByOrderId(id);
        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("shippingAddress", shippingAddress);
        request.getRequestDispatcher("order-user-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
