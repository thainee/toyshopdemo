package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderItem;
import model.Product;
import model.User;
import service.ProductService;
import java.util.Date;
import java.util.Calendar;
import model.ShippingAddress;
import service.UserService;

@WebServlet(name = "OrderController", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            PrintWriter out = response.getWriter();
            out.println("Có vẻ bạn chưa đăng nhập, vui lòng đăng đăng nhập để xem giỏ hàng nhé.");
        } else {
            List<Order> orders;
            List<OrderItem> orderItems = new ArrayList<>();
            List<ShippingAddress> shippingAddresses = userService.getAllShippingAddressesByUserId(user.getId());
            Order currentOrder = null;
            orders = productService.getOrdersByUserId(user.getId());
            for (Order order : orders) {
                if (order.getOrderStatus().equalsIgnoreCase("Opening")) {
                    orderItems = productService.getOrderItemsByOrderId(order.getId());
                    currentOrder = order;
                    break;
                }
            }
            Date date = new Date();
            request.setAttribute("now", date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 5);
            Date dateIn5Days = calendar.getTime();
            calendar.add(Calendar.DATE, 5);
            Date dateIn10Days = calendar.getTime();
            session.setAttribute("currentOrder", currentOrder);
            session.setAttribute("shippingAddresses", shippingAddresses);
            session.setAttribute("orderItems", orderItems);
            request.setAttribute("deliveryTime1", dateIn5Days);
            request.setAttribute("deliveryTime2", dateIn10Days);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductService productService = new ProductService();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        productService.addNewOrderItem(orderId, productId, quantity);
        response.sendRedirect("home");
    }

}
