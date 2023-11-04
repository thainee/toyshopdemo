package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.User;
import service.ProductService;
import service.UserService;

@WebServlet(name = "PaymentMethod", urlPatterns = {"/payment"})
public class PaymentMethod extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        HttpSession session = request.getSession();

        String choice = request.getParameter("choice");
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        User user = (User) session.getAttribute("user");
        if (choice.equals("cash")) {
            String cardHolder = request.getParameter("cardHolder");
            String cardNumber = request.getParameter("cardNumber");
            userService.addNewPaymentMethod("Trả bằng thẻ", "Chủ thẻ: " + cardHolder + ", Số thẻ: " + cardNumber);
        } else {
            userService.addNewPaymentMethod("Thanh toán khi nhận hàng", "Lấy tiền khi khách nhận được hàng");
        }
        productService.updateOrderPaymentMethod(currentOrder.getId(), userService.getLastPaymentMethodId(), totalPrice);
        productService.addNewOrder(user.getId());
        session.removeAttribute("totalPrice");
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
