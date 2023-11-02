
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductCategory;

public class ProductCategoryDAO extends DBContext{
    public List<ProductCategory> getProductCategories() {
        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            String sql = "SELECT product_id, category_id "
                    + "FROM product_categories";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductId(rs.getInt("product_id"));
                productCategory.setCategoryId(rs.getInt("category_id"));
                
                productCategories.add(productCategory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productCategories;
    }
    
        public boolean updateProductCategory(int productId, int categoryId) {
        try {
            String sql = "UPDATE product_categories "
                    + "SET product_id = ?, category_id = ? "
                    + "WHERE product_id = ? AND category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, categoryId);
            statement.setInt(3, productId);
            statement.setInt(4, categoryId);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean addProductCategory(int productId, int categoryId) {
        try {
            String sql = "INSERT INTO product_categories (product_id, category_id) "
                    + "VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, categoryId);

            int updateCount = statement.executeUpdate();

            if (updateCount == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean deleteProductCategory(int productId, int categoryId) {
        try {
            String sql = "DELETE FROM product_categories "
                    + "WHERE product_id = ? AND category_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, categoryId);

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
