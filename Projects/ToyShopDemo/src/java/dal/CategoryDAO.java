package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

public class CategoryDAO extends DBContext {

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT id, name, description "
                    + "FROM categories";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));

                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }
    
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            String sql = "SELECT id, name, description "
                    + "FROM categories "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return category;
    }

    public boolean updateCategory(int id, String name, String description) {
        try {
            String sql = "UPDATE categories "
                    + "SET name = ?, description = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean addCategory(String name, String description) {
        try {
            String sql = "INSERT INTO categories (name, description) "
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

    public boolean deleteCategory(int id) {
        try {
            String sql = "BEGIN TRANSACTION "
                    + "DELETE FROM categories WHERE id = ? "
                    + "DELETE FROM product_categories WHERE category_id = ? "
                    + "COMMIT TRANSACTION";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);

            int updateCount = statement.executeUpdate();

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

}
