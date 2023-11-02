package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

public class RoleDAO extends DBContext {

    public Role getRoleById(int id) {
        Role role = null;
        try {
            String sql = "SELECT id AS role_id, name AS role_name "
                    + "FROM roles"
                    + "WHREE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role;
    }

}
