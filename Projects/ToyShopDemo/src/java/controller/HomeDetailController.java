
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import service.ProductService;

@WebServlet(name="HomeDetailController", urlPatterns={"/homedetail"})
public class HomeDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductService productService = new ProductService();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("home-detail.jsp").forward(request, response);
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
