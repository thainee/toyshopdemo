package service;

import dal.CategoryDAO;
import dal.OrderDAO;
import dal.OrderItemDAO;
import dal.ProductCategoryDAO;
import dal.ProductDAO;
import java.util.List;
import model.Category;
import model.Order;
import model.OrderItem;
import model.Product;
import model.ProductCategory;

public class ProductService {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;
    private final ProductCategoryDAO productCategoryDAO;
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;

    public ProductService() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        productCategoryDAO = new ProductCategoryDAO();
        orderDAO = new OrderDAO();
        orderItemDAO = new OrderItemDAO();
    }

    public List<Product> getAllProducts(int start, int total) {
        return productDAO.getProducts(start, total);
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public List<Product> getProductsByCategory(String category, int start, int total) {
        return productDAO.getProductsByCategory(category, start, total);
    }

    public List<Product> getProductsByName(String name, int start, int total) {
        return productDAO.getProductsByName(name, start, total);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getCategories();
    }

    public List<Order> getAllOrders() {
        return orderDAO.getOrders();
    }
    
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }
    
    public List<Order> getOrdersByUserId(int id) {
        return orderDAO.getOrdersByUserId(id);
    }
    
    public List<OrderItem> getOrderItemsByOrderId(int id) {
        return orderItemDAO.getOrderItemsByOrderId(id);
    }
    
    public List<OrderItem> getOrderItemsOfOrderByUserId(int userId) {
        return orderItemDAO.getOrderItemsByOrderId(userId);
    }

    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryDAO.getProductCategories();
    }

    public int getTotalCountProducts() {
        return productDAO.countProducts();
    }

    public int getTotalCountProductsByCategory(String category) {
        return productDAO.countProductsByCategory(category);
    }

    public int getTotalCountProductsByName(String name) {
        return productDAO.countProductsByName(name);
    }

    public boolean addNewProduct(String name, String brand, String description, int discount, int price, String image) {
        return productDAO.addProduct(name, brand, description, discount, price, image);
    }

    public boolean updateProduct(int id, String name, String brand, String description, int discount, int price, String image) {
        return productDAO.updateProduct(id, name, brand, description, discount, price, image);
    }
    
    public boolean updateOrderStatus(int id, String order_status) {
        return orderDAO.updateOrderStatus(id, order_status);
    }
    
    public boolean updateOrderPaymentMethod(int id, int paymentMethodId, int totalPrice) {
        return orderDAO.updateOrderPaymentMethod(id, paymentMethodId, totalPrice);
    }
    
    public boolean addNewOrder(int userId) {
        return orderDAO.addOrder(userId);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public boolean addNewCategory(String name, String description) {
        return categoryDAO.addCategory(name, description);
    }
    
    public boolean addNewOrderItem(int orderId, int productId, int quantity) {
        return orderItemDAO.addOrderItem(orderId, productId, quantity);
    }

    public boolean updateOrderItemQuantity(int orderId, int productId, int quantity) {
        return orderItemDAO.updateOrderItem(orderId, productId, quantity);
    }

    public boolean updateCategory(int id, String name, String description) {
        return categoryDAO.updateCategory(id, name, description);
    }

    public boolean deleteCategory(int id) {
        return categoryDAO.deleteCategory(id);
    }
    
    public boolean deleteOrderItem(int orderId, int productId) {
        return orderItemDAO.deleteOrderItem(orderId, productId);
    }

    public boolean addNewProductCategory(int productId, int categoryId) {
        return productCategoryDAO.addProductCategory(productId, categoryId);
    }

    public boolean updateProductCategory(int productId, int categoryId) {
        return productCategoryDAO.updateProductCategory(productId, categoryId);
    }

    public boolean deleteProductCategory(int productId, int categoryId) {
        return productCategoryDAO.deleteProductCategory(productId, categoryId);
    }

}
