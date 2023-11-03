
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

@WebServlet(name="ShippingAddressController", urlPatterns={"/shippingaddress"})
public class ShippingAddressUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("shippingaddress.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        int shippingAddressId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String commune = request.getParameter("commune");
        String addressLine = request.getParameter("addressLine");
        
        userService.updateShippingAddress(shippingAddressId, name, phoneNumber, addressLine, city, district, commune);
        session.setAttribute("currentShippingAddress", userService.getShippingAddressInfo(shippingAddressId));
        response.sendRedirect("order");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
