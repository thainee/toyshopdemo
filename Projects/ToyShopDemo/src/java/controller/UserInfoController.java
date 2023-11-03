package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.User;
import service.ProductService;
import service.UserService;

@MultipartConfig
@WebServlet(name = "UserInfoController", urlPatterns = {"/userinfo"})
public class UserInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        int id = Integer.parseInt(getValue(request.getPart("id")));
        String name = getValue(request.getPart("name"));
        String phoneNumber = getValue(request.getPart("phoneNumber"));
        String password = getValue(request.getPart("password"));
        String retypePassword = getValue(request.getPart("retypePassword"));
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

        if (retypePassword.isEmpty()) {
            userService.updateUser(id, name, phoneNumber, image);
        } else {
            userService.updateUser(id, name, password, phoneNumber, image);
        }
        User user = userService.getUserById(id);
        session.setAttribute("user", user);
        response.sendRedirect("userinfo");
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
