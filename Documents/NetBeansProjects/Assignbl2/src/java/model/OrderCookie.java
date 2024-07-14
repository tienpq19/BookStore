/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAL.DAO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TienP
 */
public class OrderCookie {

    private Book bookId;
    private int quantity;
    
    public OrderCookie() {
    }
    
    public OrderCookie(Book bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
    
    public Book getBookId() {
        return bookId;
    }
    
    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OrderCookie> getCartFromCookies(String text) {
        List<OrderCookie> list = new ArrayList<>();
        DAO dao = new DAO();
        boolean check = true;
        try {
            if (text != null && text.length() != 0) {
                String[] s = text.split("/");
                for (String item : s) {
                    String[] n = item.split(":");
                    Book b = dao.select(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    if (!list.isEmpty()) {
                        for (OrderCookie or : list) {
                            if (or.getBookId().getId() == b.getId()) {
                                if (or.getQuantity() + quantity > or.getBookId().getQuantity()) {
                                    or.setQuantity(or.getBookId().getQuantity());
                                }else{
                                or.setQuantity(or.getQuantity() + quantity);}
                                check = false;
                            }
                        }
                    }
                    if (check == true) {
                        OrderCookie p = new OrderCookie(b, quantity);
                        list.add(p);
                    }
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public double Calculate(List<OrderCookie> list) {
        double total = 0;
        for (OrderCookie o : list) {
            total += o.getBookId().getPrice() * o.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        return total;
    }
}
