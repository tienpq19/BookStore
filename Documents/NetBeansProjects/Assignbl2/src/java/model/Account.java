/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TienP
 */
public class Account {
    private int AccId;
    private String username;
    private String password;
    private int role;

    public Account() {
    }

    public Account(int AccId, String username, String password, int role) {
        this.AccId = AccId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int AccId) {
        this.AccId = AccId;
    }
    
    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", role=" + role + '}';
    }
    
    
}
