package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import model.Product;
import service.ProductService;

@MultipartConfig
@WebServlet(name = "InsertController", urlPatterns = {"/insert"})
public class InsertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String table = request.getParameter("table");

        request.getRequestDispatcher(table + "-insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();

        String table = (String) session.getAttribute("currentTable");
        String name;
        String description;

        switch (table) {
            case "products":
                name = getValue(request.getPart("name"));
                String brand = getValue(request.getPart("brand"));
                description = getValue(request.getPart("brand"));
                int discount = !"".equals(getValue(request.getPart("discount"))) ? Integer.parseInt(getValue(request.getPart("discount"))) : 0;
                int price = Integer.parseInt(getValue(request.getPart("price")));
                Part filePart = request.getPart("image");
                String image = "";

                if (filePart != null && filePart.getSize() > 0) {
                    // Get the submitted file name
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                    // Define the path to save the file
                    String uploadPath = "E:\\FPT_Courses\\PRJ301\\Projects\\ToyShopDemo\\web\\assets\\img" + File.separator + fileName;
                    image = "./assets/img/" + fileName;
                    // Use InputStream from the uploaded file to copy it to the target location
                    try ( InputStream input = filePart.getInputStream()) {
                        Files.copy(input, Paths.get(uploadPath), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

                productService.addNewProduct(name, brand, description, discount, price, image);
                break;
            case "categories":
                name = request.getParameter("name");
                description = request.getParameter("description") != null ? request.getParameter("description") : "";

                productService.addNewCategory(name, description);
                break;
            case "product_categories":
                int productId = Integer.parseInt(request.getParameter("productId"));
                int categoryId = Integer.parseInt(request.getParameter("categoryID"));
                if (productService.getProductById(productId) != null && productService.getCategoryById(categoryId) != null) {
                    productService.addNewProductCategory(productId, categoryId);
                } else {
                    String msg ="Product ID hoặc Category ID không hợp lệ.";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher(table+"-insert.jsp").forward(request, response);
                }
                
                break;
            default:

        }
        response.sendRedirect("admin?table=" + table);
    }

    String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

}
