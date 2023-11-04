package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PaymentMethod;

public class PaymentMethodDAO extends DBContext {

    public List<PaymentMethod> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            String sql = "SELECT id, name, description "
                    + "FROM payment_methods";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt("id"));
                paymentMethod.setName(rs.getString("name"));
                paymentMethod.setDescription(rs.getString("description"));

                paymentMethods.add(paymentMethod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paymentMethods;
    }

    public PaymentMethod getPaymentMethodById(int id) {
        PaymentMethod paymentMethod = null;
        try {
            String sql = "SELECT id, name, description "
                    + "FROM payment_methods "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt("id"));
                paymentMethod.setName(rs.getString("name"));
                paymentMethod.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paymentMethod;
    }

    public int getLastId() {
        int id = 0;
        try {
            String sql = "SELECT IDENT_CURRENT('payment_methods') AS currentId;";
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

    public boolean addPaymentMethod(String name, String description) {
        try {
            String sql = "INSERT INTO payment_methods (name, description) "
                    + "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
