package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends DBContext {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT id, name, email, "
                    + "password, phone_number, avatar, role_id "
                    + "FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            String sql = "SELECT id, name, email, "
                    + "password, phone_number, avatar, role_id "
                    + "FROM users "
                    + "WHREE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public List<User> getUsersByName(String name) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT id, name, email, "
                    + "password, phone_number, avatar, role_id "
                    + "FROM users "
                    + "WHREE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            String sql = "SELECT id, name, email, "
                    + "password, phone_number, avatar, role_id "
                    + "FROM users "
                    + "WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleId(rs.getInt("role_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    public boolean addUser(String name, String phoneNumber, String email, String password) {
        try {
            String sql = "INSERT INTO users (name, email, password, phone_number, role_id) "
                    + "VALUES (?, ?, ?, ?, 2)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, phoneNumber);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean deleteUser(String email, String password) {
        try {
            String sql = "DELETE FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

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
