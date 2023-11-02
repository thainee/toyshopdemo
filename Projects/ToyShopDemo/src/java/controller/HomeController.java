package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Product;
import service.ProductService;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ProductService productService = new ProductService();
        HttpSession session = request.getSession();

        String currentPaginationPage_str = request.getParameter("page");
        String category = request.getParameter("category");
        int currentPaginationPage;
        int fromWhichProduct;
        int totalCount = 8;
        int totalProducts;
        int totalPaginationPage;
        int startPage;
        int endPage;
        List<Product> products;

        //setCurrentPage
        if (currentPaginationPage_str == null) {
            currentPaginationPage = 1;
            fromWhichProduct = 0;
        } else {
            currentPaginationPage = Integer.parseInt(currentPaginationPage_str);
            fromWhichProduct = (currentPaginationPage - 1) * totalCount;
        }

        //get Products and Categories
        if (category == null || category.isEmpty()) {
            products = productService.getAllProducts(fromWhichProduct, totalCount);
            totalProducts = productService.getTotalCountProducts();
            session.removeAttribute("productName");
        } else {
            products = productService.getProductsByCategory(category, fromWhichProduct, totalCount);
            totalProducts = productService.getTotalCountProductsByCategory(category);
            session.removeAttribute("productName");
            session.setAttribute("categoryName", category);
        }
        List<Category> categories = productService.getAllCategories();
        

        //set Pagination info
        totalPaginationPage = totalProducts / totalCount;
        if (totalProducts % totalCount != 0) {
            totalPaginationPage++;
        }
        if (currentPaginationPage > 2) {
            startPage = currentPaginationPage - 2;
        } else {
            startPage = 1;
        }
        if (currentPaginationPage < (totalPaginationPage - 2)) {
            endPage = currentPaginationPage + 2;
        } else {
            endPage = totalPaginationPage;
        }
        
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("currentPaginationPage", currentPaginationPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalPaginationPage", totalPaginationPage);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
