package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ShippingAddress;

public class ShippingAddressDAO extends DBContext {

    public ShippingAddress getShippingAddressById(int id) {
        ShippingAddress shippingAddress = null;
        try {
            String sql = "SELECT id, user_id, name, phone_number, "
                    + "address_line, city, district, commune "
                    + "FROM shipping_addresses "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                shippingAddress = new ShippingAddress();
                shippingAddress.setId(rs.getInt("id"));
                shippingAddress.setUserId(rs.getInt("user_id"));
                shippingAddress.setName(rs.getString("name"));
                shippingAddress.setPhoneNumber(rs.getString("phone_number"));
                shippingAddress.setAddressLine(rs.getString("address_line"));
                shippingAddress.setCity(rs.getString("city"));
                shippingAddress.setDistrict(rs.getString("district"));
                shippingAddress.setCommune(rs.getString("commune"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return shippingAddress;
    }
    
        public ShippingAddress getShippingAddressByUserId(int userId) {
        ShippingAddress shippingAddress = null;
        try {
            String sql = "SELECT id, user_id, name, phone_number, "
                    + "address_line, city, district, commune "
                    + "FROM shipping_addresses "
                    + "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                shippingAddress = new ShippingAddress();
                shippingAddress.setId(rs.getInt("id"));
                shippingAddress.setUserId(rs.getInt("user_id"));
                shippingAddress.setName(rs.getString("name"));
                shippingAddress.setPhoneNumber(rs.getString("phone_number"));
                shippingAddress.setAddressLine(rs.getString("address_line"));
                shippingAddress.setCity(rs.getString("city"));
                shippingAddress.setDistrict(rs.getString("district"));
                shippingAddress.setCommune(rs.getString("commune"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return shippingAddress;
    }

    public boolean addShippingAddress(int userId, String name, String phoneNumber, String addressLine, String city, String disctrict, String commune) {
        try {
            String sql = "INSERT INTO shipping_addresses (user_id, name, phone_number, address_line, city, disctrict, commune) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setString(3, phoneNumber);
            statement.setString(4, addressLine);
            statement.setString(5, city);
            statement.setString(6, disctrict);
            statement.setString(7, commune);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean deleteShippingAddress(int id) {
        try {
            String sql = "DELETE FROM shipping_addresses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

}
