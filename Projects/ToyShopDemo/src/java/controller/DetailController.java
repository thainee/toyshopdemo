package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import model.Category;
import model.Order;
import model.OrderItem;
import model.Product;
import model.ShippingAddress;
import service.ProductService;
import service.UserService;

@WebServlet(name = "DetailController", urlPatterns = {"/detail"})
public class DetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        String table = request.getParameter("table");
        int id = Integer.parseInt(request.getParameter("id"));

        switch (table) {
            case "products":
                Product product = productService.getProductById(id);
                request.setAttribute("product", product);
                break;
            case "categories":
                Category category = productService.getCategoryById(id);
                request.setAttribute("category", category);
                break;
            case "orders":
                Order order = productService.getOrderById(id);
                ShippingAddress shippingAddress = userService.getShippingAddressInfo(order.getShippingAddressId());
                List<OrderItem> orderItems = productService.getOrderItemsByOrderId(id);
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                request.setAttribute("shippingAddress", shippingAddress);
                break;
            default:

        }

        request.getRequestDispatcher(table + "-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String table = request.getParameter("table");

        switch (table) {
            case "products":

                break;
            case "categories":

                break;
            default:

        }
    }
}
