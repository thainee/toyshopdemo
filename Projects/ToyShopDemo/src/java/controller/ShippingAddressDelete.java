
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.UserService;

@WebServlet(name="ShippingAddressDelete", urlPatterns={"/shippingaddressdelete"})
public class ShippingAddressDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        int shippingAddressId = Integer.parseInt(request.getParameter("id"));
        User user = (User)session.getAttribute("user");
        
        userService.deleteShippingAddress(shippingAddressId);
        session.setAttribute("shippingAddresses", userService.getAllShippingAddressesByUserId(user.getId()));
        response.sendRedirect("shippingaddress");
    }

}
