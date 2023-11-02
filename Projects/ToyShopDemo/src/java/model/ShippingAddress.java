package model;

public class ShippingAddress {

    private int id;
    private int userId;
    private String name;
    private String phoneNumber;
    private String addressLine;
    private String city;
    private String district;
    private String commune;

    public ShippingAddress() {
    }

    public ShippingAddress(int id, int userId, String name, String phoneNumber, String addressLine, String city, String district, String commune) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressLine = addressLine;
        this.city = city;
        this.district = district;
        this.commune = commune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
    
}
