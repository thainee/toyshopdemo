package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ProductService;

@WebServlet(name = "DeleteController", urlPatterns = {"/delete"})
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();

        String table = (String) session.getAttribute("currentTable");
        int id;

        switch (table) {
            case "products":
                id = Integer.parseInt(request.getParameter("id"));
                productService.deleteProduct(id);
                break;
            case "categories":
                id = Integer.parseInt(request.getParameter("id"));
                productService.deleteCategory(id);
                break;
            case "product_categories":
                int productId = Integer.parseInt(request.getParameter("productId"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                productService.deleteProductCategory(productId, categoryId);
                break;
            default:

        }
        response.sendRedirect("admin?table=" + table);
    }

}
