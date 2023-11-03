package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class ProductDAO extends DBContext {

    public List<Product> getProducts(int start, int total) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT id, name, brand, description, discount, price, image "
                    + "FROM products "
                    + "ORDER BY id "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        try {
            String sql = "SELECT id, name, brand, description, discount, price, image "
                    + "FROM products "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return product;
    }

    public List<Product> getProductsByName(String name, int start, int total) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT id, name, brand, description, discount, price, image "
                    + "FROM products "
                    + "WHERE name LIKE ? "
                    + "ORDER BY id "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, start);
            statement.setInt(3, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    public List<Product> getProductsByOrderId(int orderId) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.name, p.brand, p.description, "
                    + "p.discount, p.price, p.image "
                    + "FROM products p "
                    + "JOIN order_items oi ON oi.product_id = p.id "
                    + "WHERE oi.order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }
    
    public List<Product> getProductsOfOrderByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.name, p.image FROM order_items oi "
                    + "JOIN orders o ON o.id = oi.order_id "
                    + "JOIN products p ON p.id = oi.product_id "
                    + "WHERE o.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public List<Product> getProductsByCategory(String categoryName, int start, int total) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.name, p.brand, p.description, "
                    + "p.discount, p.price, p.image "
                    + "FROM products p "
                    + "JOIN product_categories pc ON p.id = pc.product_id "
                    + "JOIN categories c ON pc.category_id = c.id "
                    + "WHERE c.name = ? "
                    + "ORDER BY p.id "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setInt(2, start);
            statement.setInt(3, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setDescription(rs.getString("description"));
                product.setDiscount(rs.getInt("discount"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));

                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public int countProducts() {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS totalCount "
                    + "FROM products";
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

    public int countProductsByName(String name) {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS totalCount "
                    + "FROM products "
                    + "WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt("totalCount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCount;
    }

    public int countProductsByCategory(String category) {
        int totalCount = 0;
        try {
            String sql = "SELECT COUNT(*) AS totalCount FROM products p "
                    + "JOIN product_categories pc ON p.id = pc.product_id "
                    + "JOIN categories c ON pc.category_id = c.id "
                    + "WHERE c.name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt("totalCount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCount;
    }
    
    public boolean updateProduct(int id, String name, String brand, String description, int discount, int price, String image) {
        try {
            String sql = "UPDATE products "
                    + "SET name = ?, brand = ?, description = ?, "
                    + "discount = ?, price = ?, image = ? "
                    + "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, brand);
            statement.setString(3, description);
            statement.setInt(4, discount);
            statement.setInt(5, price);
            statement.setString(6, image);
            statement.setInt(7, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean addProduct(String name, String brand, String description, int discount, int price, String image) {
        try {
            String sql = "INSERT INTO products (name, brand, description, discount, price, image) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, brand);
            statement.setString(3, description);
            statement.setInt(4, discount);
            statement.setInt(5, price);
            statement.setString(6, image);

            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deleteProduct(int id) {
        try {
            String sql = "BEGIN TRANSACTION "
                    + "DELETE FROM product_categories WHERE product_id = ? "
                    + "DELETE FROM order_items where product_id = ? "
                    + "DELETE FROM products WHERE id = ? "
                    + "COMMIT TRANSACTION";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.setInt(3, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
