/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author TienP
 */
public class OrderData {
    private Book bookid;
    private int quantity;
    private double price;
    private String status;
    private String address;
    private String phoneNumber;
    private String date;
    private String name;
    private String payMethod;
    private String OrderID;
    public OrderData() {
    }

    public OrderData(Book bookid, int quantity, double price, String status, String address, String phoneNumber, String date, String name,String pay,String orderid) {
        this.bookid = bookid;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.name = name;
        this.payMethod = pay;
        this.OrderID = orderid;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Book getBookid() {
        return bookid;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public void setBookid(Book bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price,int quantity) {
        double total = 0;
        total += price * quantity;
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        this.price= total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderData{" + "bookid=" + bookid.getPrice()+ ", quantity=" + quantity + ", price=" + price + ", status=" + status + ", address=" + address + ", phoneNumber=" + phoneNumber + ", date=" + date + ", name=" + name + '}';
    }
    
    
            
}
