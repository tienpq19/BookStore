/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author TienP
 */
public class Order {
    private int id;
    private String name;
    private String phoneNum;
    private String address;
    private LocalDateTime  Date;
    private String status;
    private int accID;
    private String payMethod;

    public Order() {
    }

    public Order(int id, String name, String phoneNum, String address, LocalDateTime Date, String status, int accID, String payMethod) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.Date = Date;
        this.status = status;
        this.accID = accID;
        this.payMethod = payMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime Date) {
        this.Date = Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    

    
    
    
}
