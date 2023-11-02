
package service;

import dal.ShippingAddressDAO;
import dal.UserDAO;
import model.ShippingAddress;
import model.User;

public class UserService {
    private UserDAO userDAO;
    private ShippingAddressDAO shippingAddressDAO;
    
    public UserService() {
        userDAO = new UserDAO();
        shippingAddressDAO = new ShippingAddressDAO();
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
}
