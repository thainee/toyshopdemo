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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import service.ProductService;

@MultipartConfig
@WebServlet(name = "UpdateController", urlPatterns = {"/update"})
public class UpdateController extends HttpServlet {

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
        String name;
        String description;

        switch (table) {
            case "products":
                id = Integer.parseInt(getValue(request.getPart("id")));
                name = getValue(request.getPart("name"));
                String brand = getValue(request.getPart("brand"));
                description = getValue(request.getPart("description"));
                int discount = Integer.parseInt(getValue(request.getPart("discount")));
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
                } else {
                    image = getValue(request.getPart("defaultImage"));
                }
                productService.updateProduct(id, name, brand, description, discount, price, image);
                break;
            case "categories":
                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                description = request.getParameter("description");
                productService.updateCategory(id, name, description);
                break;
            default:
                id = Integer.parseInt(request.getParameter("id"));
                String orderStatus = request.getParameter("orderStatus");
                productService.updateOrderStatus(id, orderStatus);
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
