/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TienP
 */
public class Book {
    private int id;
    private String name;
    private String image;
    private double price;
    private String author;
    private String publisher;
    private int page;
    private String language;
    private BookCate category;
    private String size;
    private String descrip;
    private int quantity;

    public Book() {
    }

    public Book(int id, String name, String image, double price, String author, String publisher, int page, String language, BookCate category, String size, String descrip, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.page = page;
        this.language = language;
        this.category = category;
        this.size = size;
        this.descrip = descrip;
        this.quantity = quantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getLanguage() {
        return language;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BookCate getCategory() {
        return category;
    }

    public void setCategory(BookCate category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", author=" + author + ", publisher=" + publisher + ", page=" + page + ", language=" + language + ", category=" + category + ", size=" + size + ", descrip=" + descrip + '}';
    }
    
}
