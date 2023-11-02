package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderItem;

public class OrderItemDAO extends DBContext {

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
                orderItem.setProductId(rs.getInt("product_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                
                orderItems.add(orderItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderItems;
    }

}
