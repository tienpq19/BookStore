/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TienP
 */
public class BookCate {
    private int cateID;
    private String nameCate;

    public BookCate() {
    }

    public BookCate(int cateID, String nameCate) {
        this.cateID = cateID;
        this.nameCate = nameCate;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    @Override
    public String toString() {
        return "BookCate{" + "cateID=" + cateID + ", nameCate=" + nameCate + '}';
    }
    
}
