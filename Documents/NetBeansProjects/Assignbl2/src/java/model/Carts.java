/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author TienP
 */
public class Carts {
    private int accID;
    private Book bookId;
    private int quantity;

    public Carts() {
    }

    public Carts(int accID, Book bookId, int quantity) {
        this.accID = accID;
        this.bookId = bookId;
        this.quantity = quantity;
    }
    
    
    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
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
    public  double Calculate(List<Carts> list) {
        double total = 0;
        for (Carts o : list) {
            total += o.getBookId().getPrice() * o.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        return total;
    }

    @Override
    public String toString() {
        return "Carts{" + "accID=" + accID + ", bookId=" + bookId.getId() + ", quantity=" + quantity + '}';
    }
    
}
