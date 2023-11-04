
package service;

import dal.PaymentMethodDAO;
import dal.ShippingAddressDAO;
import dal.UserDAO;
import java.util.List;
import model.PaymentMethod;
import model.ShippingAddress;
import model.User;

public class UserService {
    private UserDAO userDAO;
    private ShippingAddressDAO shippingAddressDAO;
    private PaymentMethodDAO paymentMethodDAO;
    
    public UserService() {
        userDAO = new UserDAO();
        shippingAddressDAO = new ShippingAddressDAO();
        paymentMethodDAO = new PaymentMethodDAO();
    }
    
    public User authenticate(String email, String password) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }
    
    public boolean addNewUser(String name, String phoneNumber, String email, String password) {
        return userDAO.addUser(name, phoneNumber, email, password);
    }
    
    public ShippingAddress getShippingAddressInfo(int id) {
        return shippingAddressDAO.getShippingAddressById(id);
    }
    
    public List<ShippingAddress> getAllShippingAddressesByUserId(int userId) {
        return shippingAddressDAO.getShippingAddressesByUserId(userId);
    }
    
    public boolean updateUser(int id, String name, String phoneNumber, String avatar) {
        return userDAO.updateUser(id, name, phoneNumber, avatar);
    }
    
    public boolean updateUser(int id, String name, String password, String phone_number, String avatar) {
        return userDAO.updateUser(id, name, password, phone_number, avatar);
    }
    
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
    
    public boolean deleteShippingAddress(int shippingAddressId) {
        return shippingAddressDAO.deleteShippingAddress(shippingAddressId);
    }
    
    public boolean updateShippingAddress(int id, String name, String phoneNumber, String addressLine, String city, String disctrict, String commune) {
        return shippingAddressDAO.updateShippingAddress(id, name, phoneNumber, addressLine, city, disctrict, commune);
    }
    
    public boolean updateShippingAddress(int userId, int id, String name, String phoneNumber, String addressLine, String city, String disctrict, String commune) {
        return shippingAddressDAO.updateShippingAddress(userId, id, name, phoneNumber, addressLine, city, disctrict, commune);
    }
    
    public boolean addShippingAddress(int userId, String name, String phoneNumber, String addressLine, String city, String district, String commune) {
        return shippingAddressDAO.addShippingAddress(userId, name, phoneNumber, addressLine, city, district, commune);
    }
    
    public int getLastShippingAddressId() {
        return shippingAddressDAO.getLastId();
    }
    
    public PaymentMethod getPaymentMethodById(int id) {
        return paymentMethodDAO.getPaymentMethodById(id);
    }
    
    public boolean addNewPaymentMethod(String name, String description) {
        return paymentMethodDAO.addPaymentMethod(name, description);
    }
    
    public int getLastPaymentMethodId() {
        return paymentMethodDAO.getLastId();
    }
}
