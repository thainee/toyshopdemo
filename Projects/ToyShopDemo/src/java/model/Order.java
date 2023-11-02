package model;

import java.sql.Date;

public class Order {

    private int id;
    private int userId;
    private int shippingAddressId;
    private int paymentMethodId;
    private String orderStatus;
    private double total;
    private Date updatedAt;

    public Order() {
    }

    public Order(int id, int userId, int shippingAddressId, int paymentMethodId, String orderStatus, double total, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.shippingAddressId = shippingAddressId;
        this.paymentMethodId = paymentMethodId;
        this.orderStatus = orderStatus;
        this.total = total;
        this.updatedAt = updatedAt;
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

    public int getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(int shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
