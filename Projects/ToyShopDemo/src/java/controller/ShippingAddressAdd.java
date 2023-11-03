
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

@WebServlet(name="ShippingAddressAdd", urlPatterns={"/shippingaddressadd"})
public class ShippingAddressAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("shippingaddress-add.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String commune = request.getParameter("commune");
        String addressLine = request.getParameter("addressLine");
        
        userService.addShippingAddress(user.getId(), name, phoneNumber, addressLine, city, district, commune);
        userService.updateShippingAddress(user.getId(), userService.getLastShippingAddressId(), name, phoneNumber, addressLine, city, district, commune);
        session.setAttribute("shippingAddresses", userService.getAllShippingAddressesByUserId(user.getId()));
        response.sendRedirect("shippingaddress");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
