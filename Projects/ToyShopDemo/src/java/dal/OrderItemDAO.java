package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderItem;
import model.Product;

public class OrderItemDAO extends DBContext {

    ProductDAO productDAO = new ProductDAO();

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            String sql = "SELECT order_id, product_id, quantity "
                    + "FROM order_items "
                    + "WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();

                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setProduct(productDAO.getProductById(rs.getInt("product_id")));
                orderItem.setQuantity(rs.getInt("quantity"));

                orderItems.add(orderItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderItems;
    }

    public List<OrderItem> getOrderItemsOfOrderByUserId(int userId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            String sql = "SELECT oi.id, oi.product_id, oi.quantity "
                    + "FROM order_items oi "
                    + "JOIN orders o ON o.id = oi.order_id "
                    + "JOIN products p ON p.id = oi.product_id "
                    + "WHERE o.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setProduct(productDAO.getProductById(rs.getInt("product_id")));
                orderItem.setQuantity(rs.getInt("quantity"));

                orderItems.add(orderItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderItems;
    }

    public boolean deleteOrderItem(int orderId, int productId) {
        try {
            String sql = "DELETE FROM order_items WHERE product_id = ? AND order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, orderId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateOrderItem(int orderId, int productId, int quantity) {
        try {
            String sql = "UPDATE order_items SET quantity = ? "
                    + "WHERE order_id = ? AND product_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, orderId);
            statement.setInt(3, productId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean addOrderItem(int orderId, int productId, int quantity) {
        try {
            String sql = "INSERT INTO order_items (order_id , product_id , quantity) "
                    + "VALUES(? , ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
