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
import model.Product;
import model.ProductCategory;
import model.User;
import service.ProductService;

@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();

        User user = (User) session.getAttribute("user");
        String table = request.getParameter("table");
        int fromWhichProduct = 0;
        int totalProducts = productService.getTotalCountProducts();

        List<Product> products = productService.getAllProducts(fromWhichProduct, totalProducts);
        List<Category> categories = productService.getAllCategories();
        List<ProductCategory> productCategories = productService.getAllProductCategories();
        List<Order> orders = productService.getAllOrders();
        request.setAttribute("orders", orders);
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("productCategories", productCategories);
        request.setAttribute("table", table);

        if (user == null || user.getRoleId() != 1) {
            PrintWriter out = response.getWriter();
            out.println("Quyền truy cập bị từ chối, vui lòng đăng nhập bằng tài khoản được cấp quyền admin.");
        } else {
            request.getRequestDispatcher("admin-table.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
