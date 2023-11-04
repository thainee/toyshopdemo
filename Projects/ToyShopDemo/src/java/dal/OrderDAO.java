package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

public class OrderDAO extends DBContext {

    public List<Order> getOrders(int start, int total) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT id AS order_id, user_id, shipping_address_id, "
                    + "payment_method_id, order_status, total, updated_at "
                    + "FROM orders "
                    + "ORDER BY order_id "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setShippingAddressId(rs.getInt("shipping_address_id"));
                order.setTotal(rs.getDouble("total"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setUpdatedAt(rs.getDate("updated_at"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT id AS order_id, user_id, shipping_address_id, "
                    + "payment_method_id, order_status, total, updated_at "
                    + "FROM orders";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setShippingAddressId(rs.getInt("shipping_address_id"));
                order.setTotal(rs.getDouble("total"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setUpdatedAt(rs.getDate("updated_at"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    public Order getOrderById(int id) {
        Order order = null;
        try {
            String sql = "SELECT id, user_id, shipping_address_id, "
                    + "payment_method_id, order_status, total, updated_at "
                    + "FROM orders "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setShippingAddressId(rs.getInt("shipping_address_id"));
                order.setTotal(rs.getDouble("total"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setUpdatedAt(rs.getDate("updated_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT id, user_id, shipping_address_id, "
                    + "payment_method_id, order_status, total, updated_at "
                    + "FROM orders "
                    + "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setShippingAddressId(rs.getInt("shipping_address_id"));
                order.setTotal(rs.getDouble("total"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setUpdatedAt(rs.getDate("updated_at"));
                
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    public int countOrders() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS totalCount "
                    + "FROM orders";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt("totalCount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCount;
    }

    public boolean updateOrder(int id, int shipping_address_id, int payment_method_id, String order_status, double total) {
        try {
            String sql = "UPDATE orders "
                    + "SET shipping_address_id = ?, payment_method_id = ?, "
                    + "order_status = ?, total = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shipping_address_id);
            statement.setInt(2, payment_method_id);
            statement.setString(3, order_status);
            statement.setDouble(4, total);
            statement.setInt(5, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateOrderStatus(int id, String order_status) {
        try {
            String sql = "UPDATE orders "
                    + "SET order_status = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, order_status);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateOrderPaymentMethod(int id, int paymentMethodId, int totalPrice) {
        try {
            String sql = "UPDATE orders "
                    + "SET payment_method_id = ?, total = ?, order_status = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, paymentMethodId);
            statement.setInt(2, totalPrice);
            statement.setString(3, "Processing");
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean addOrder(int user_id, int shipping_address_id, int payment_method_id, String order_status, double total) {
        try {
            String sql = "INSERT INTO orders (user_id, shipping_address_id, payment_method_id, order_status, total) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, shipping_address_id);
            statement.setInt(3, payment_method_id);
            statement.setString(4, order_status);
            statement.setDouble(5, total);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean addOrder(int userId) {
        try {
            String sql = "INSERT INTO orders (user_id, order_status) "
                    + "VALUES (?, 'Opening')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
