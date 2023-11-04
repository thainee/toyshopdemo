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

    public List<ShippingAddress> getShippingAddressesByUserId(int userId) {
        List<ShippingAddress> shippingAddresses = new ArrayList<>();
        try {
            String sql = "SELECT id, user_id, name, phone_number, "
                    + "address_line, city, district, commune "
                    + "FROM shipping_addresses "
                    + "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setId(rs.getInt("id"));
                shippingAddress.setUserId(rs.getInt("user_id"));
                shippingAddress.setName(rs.getString("name"));
                shippingAddress.setPhoneNumber(rs.getString("phone_number"));
                shippingAddress.setAddressLine(rs.getString("address_line"));
                shippingAddress.setCity(rs.getString("city"));
                shippingAddress.setDistrict(rs.getString("district"));
                shippingAddress.setCommune(rs.getString("commune"));

                shippingAddresses.add(shippingAddress);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return shippingAddresses;
    }

    public boolean addShippingAddress(int userId, String name, String phoneNumber, String addressLine, String city, String district, String commune) {
        try {
            String sql = "INSERT INTO shipping_addresses (name, phone_number, address_line, city, district, commune, user_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, addressLine);
            statement.setString(4, city);
            statement.setString(5, district);
            statement.setString(6, commune);
            statement.setInt(7, userId);


            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateShippingAddress(int id, String name, String phoneNumber, String addressLine, String city, String disctrict, String commune) {
        try {
            String sql = "UPDATE shipping_addresses "
                    + "SET name = ?, phone_number = ?, address_line = ?, "
                    + "city = ?, district = ?, commune = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, addressLine);
            statement.setString(4, city);
            statement.setString(5, disctrict);
            statement.setString(6, commune);
            statement.setInt(7, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateShippingAddress(int userId, int id, String name, String phoneNumber, String addressLine, String city, String disctrict, String commune) {
        try {
            String sql = "UPDATE shipping_addresses "
                    + "SET name = ?, phone_number = ?, address_line = ?, "
                    + "city = ?, district = ?, commune = ? "
                    + "WHERE id = ? AND user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phoneNumber);
            statement.setString(3, addressLine);
            statement.setString(4, city);
            statement.setString(5, disctrict);
            statement.setString(6, commune);
            statement.setInt(7, id);
            statement.setInt(8, userId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deleteShippingAddress(int id) {
        try {
            String sql = "DELETE FROM shipping_addresses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public int getLastId() {
        int id = 0;
        try {
            String sql = "SELECT IDENT_CURRENT('shipping_addresses') AS currentId;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = (rs.getInt("currentId"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
