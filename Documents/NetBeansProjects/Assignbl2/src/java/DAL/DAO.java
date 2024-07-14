/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Book;
import model.BookCate;
import model.Carts;
import model.Data;
import model.OrderData;
//import model.CartData;
//import model.CusData;
//import model.Customer;
//import model.Data;

/**
 *
 * @author TienP
 */
public class DAO {

    public static DAO INSTANCE = new DAO();

    private String status = "OK";

    public DAO() {
        if (INSTANCE == null) {
            con = new DBContext().connection;
        } else {
            INSTANCE = this;
        }
    }
    Connection con = new DBContext().connection;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        DAO dao = new DAO();
        ArrayList<OrderData> list = dao.getOrderData();
        for (OrderData st : list) {
            System.out.println(st);
        }
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT p.*,cateName
                         FROM Book p
                         INNER JOIN Category ct ON p.categoryId = ct.id;  """;
          
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                h.setQuantity(rs.getInt("quantity"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT p.*,cateName
                         FROM Book p
                         INNER JOIN Category ct ON p.categoryId = ct.id
                         where p.quantity > 0
                          ;  """;
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                h.setQuantity(rs.getInt("quantity"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> getBookstop(int number) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT TOP " + number + " p.*, cateName \n"
                    + "FROM Book p \n"
                    + "JOIN Category c ON p.categoryId = c.id\n"
                    + "ORDER BY p.id DESC"
                    + "where p.quantity > 0;";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setQuantity(rs.getInt("quantity"));
                h.setDescrip(rs.getString("description"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> getBooks1byCate() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT *
                         FROM (
                             SELECT p.*, cateName,
                                    ROW_NUMBER() OVER (PARTITION BY c.id ORDER BY p.id) AS rn
                             FROM Products p
                             INNER JOIN Category c ON p.categoryId = c.id
                         ) AS sub
                         WHERE rn = 1;""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                BookCate bc = new BookCate();
                h.setQuantity(rs.getInt("quantity"));
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> getBooksbyCate(String cateID) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT p.*,  cateName
                         FROM Book p
                         INNER JOIN Category ct ON p.categoryId = ct.id
                         where ct.id = ? and p.quantity>0;""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setQuantity(rs.getInt("quantity"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> getBooksbyPriceandID(String cateID, int order) {
        ArrayList<Book> books = new ArrayList<>();
        if (order == 1) {
            try {
                String sql = """
                             SELECT p.*,cateName
                             FROM Book p
                             JOIN Category c ON p.categoryId = c.id
                             WHERE p.categoryId = ?
                             ORDER BY p.price ;""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                ps.setString(1, cateID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book h = new Book();
                    h.setId(rs.getInt("id"));
                    h.setName(rs.getString("name"));
                    h.setImage(rs.getString("image"));
                    h.setPrice(rs.getDouble("price"));
                    h.setAuthor(rs.getString("author"));
                    h.setPublisher(rs.getString("publisher"));
                    h.setPage(rs.getInt("page"));
                    h.setQuantity(rs.getInt("quantity"));
                    h.setLanguage(rs.getString("language"));
                    h.setSize(rs.getString("size"));
                    h.setDescrip(rs.getString("description"));
                    BookCate bc = new BookCate();
                    bc.setCateID(rs.getInt("categoryId"));
                    bc.setNameCate(rs.getString("cateName"));
                    h.setCategory(bc);
                    books.add(h);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        } else {
            try {
                String sql = """
                             SELECT p.*,cateName
                             FROM Book p
                             JOIN Category c ON p.categoryId = c.id
                             WHERE p.categoryId = ?
                             ORDER BY p.price DESC;""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                ps.setString(1, cateID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book h = new Book();
                    h.setId(rs.getInt("id"));
                    h.setName(rs.getString("name"));
                    h.setImage(rs.getString("image"));
                    h.setPrice(rs.getDouble("price"));
                    h.setAuthor(rs.getString("author"));
                    h.setPublisher(rs.getString("publisher"));
                    h.setPage(rs.getInt("page"));
                    h.setLanguage(rs.getString("language"));
                    h.setSize(rs.getString("size"));
                    h.setDescrip(rs.getString("description"));
                    BookCate bc = new BookCate();
                    bc.setCateID(rs.getInt("categoryId"));
                    bc.setNameCate(rs.getString("cateName"));
                    h.setCategory(bc);
                    books.add(h);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        return books;
    }
    public ArrayList<Book> getBooksbyPrice(String order) {
        ArrayList<Book> books = new ArrayList<>();
        if ("lth".equals(order)) {
            try {
                String sql = """
                             SELECT p.*,cateName
                             FROM Book p
                             JOIN Category c ON p.categoryId = c.id
                            
                             ORDER BY p.price ;""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book h = new Book();
                    h.setId(rs.getInt("id"));
                    h.setName(rs.getString("name"));
                    h.setImage(rs.getString("image"));
                    h.setPrice(rs.getDouble("price"));
                    h.setAuthor(rs.getString("author"));
                    h.setPublisher(rs.getString("publisher"));
                    h.setPage(rs.getInt("page"));
                    h.setQuantity(rs.getInt("quantity"));
                    h.setLanguage(rs.getString("language"));
                    h.setSize(rs.getString("size"));
                    h.setDescrip(rs.getString("description"));
                    BookCate bc = new BookCate();
                    bc.setCateID(rs.getInt("categoryId"));
                    bc.setNameCate(rs.getString("cateName"));
                    h.setCategory(bc);
                    books.add(h);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        } else {
            try {
                String sql = """
                             SELECT p.*,cateName
                             FROM Book p
                             JOIN Category c ON p.categoryId = c.id
                             
                             ORDER BY p.price DESC;""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    Book h = new Book();
                    h.setId(rs.getInt("id"));
                    h.setName(rs.getString("name"));
                    h.setImage(rs.getString("image"));
                    h.setPrice(rs.getDouble("price"));
                    h.setAuthor(rs.getString("author"));
                    h.setPublisher(rs.getString("publisher"));
                    h.setPage(rs.getInt("page"));
                    h.setLanguage(rs.getString("language"));
                    h.setSize(rs.getString("size"));
                    h.setDescrip(rs.getString("description"));
                    BookCate bc = new BookCate();
                    bc.setCateID(rs.getInt("categoryId"));
                    bc.setNameCate(rs.getString("cateName"));
                    h.setCategory(bc);
                    books.add(h);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        return books;
    }

    public ArrayList<Book> getBooks1byCate2() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT *
                         FROM (
                             SELECT p.*,cateName,
                                    ROW_NUMBER() OVER (PARTITION BY c.id ORDER BY p.id DESC) AS rn
                             FROM Book p
                             INNER JOIN Category c ON p.categoryId = c.id
                         ) AS sub
                         WHERE rn = 1;""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setQuantity(rs.getInt("quantity"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> search(String keyword) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT p.*,cateName
                         FROM Book p
                         INNER JOIN Category ct ON p.categoryId = ct.id
                         where p.name LIKE ? and p.quantity>0;""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setQuantity(rs.getInt("quantity"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public ArrayList<Book> searchMana(String keyword, String cate) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = """
                         SELECT p.*,cateName
                         FROM Book p
                         JOIN Category c ON p.categoryId = c.id
                         WHERE p.name LIKE ?
                         AND p.categoryId = ?""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, cate);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book h = new Book();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setImage(rs.getString("image"));
                h.setPrice(rs.getDouble("price"));
                h.setAuthor(rs.getString("author"));
                h.setPublisher(rs.getString("publisher"));
                h.setPage(rs.getInt("page"));
                h.setLanguage(rs.getString("language"));
                h.setSize(rs.getString("size"));
                h.setDescrip(rs.getString("description"));
                h.setQuantity(rs.getInt("quantity"));
                BookCate bc = new BookCate();
                bc.setCateID(rs.getInt("categoryId"));
                bc.setNameCate(rs.getString("cateName"));
                h.setCategory(bc);
                books.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }

    public Account checkUser(String name) {

        try {
            String sql = """
                         SELECT * from Account
                         where username = ?""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account acc = new Account();
                acc.setUsername((rs.getString("username")));
                acc.setPassword(rs.getString("password"));
                acc.setRole(rs.getInt("role"));
                acc.setAccId(rs.getInt("id"));
                return acc;

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<BookCate> getBookCate() {
        ArrayList<BookCate> types = new ArrayList<>();
        try {
            String sql = "select c.id, c.cateName\n"
                    + "from Category c";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookCate ht = new BookCate();
                ht.setCateID(rs.getInt("id"));
                ht.setNameCate(rs.getString("cateName"));
                types.add(ht);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }

    public BookCate getCate(String cateID) {
        BookCate bc = new BookCate();
        try {
            String sql = "select * from Category c\n"
                    + "where c.id = ?";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            while (rs.next()) {

                bc.setCateID(rs.getInt("id"));
                bc.setNameCate(rs.getString("cateName"));

            }
        } catch (Exception e) {
        }
        return bc;
    }

    public void delete(String id) {
        String query = """
                       UPDATE Book
                       SET quantity = 0
                       WHERE id = ?;""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insert(String name, String image, String price, String author,
            String publisher, String page,
            String language, String category, String size, String descrip, String quantity) {
        String query = "INSERT INTO Book (name, image, price, author, publisher, page, language, categoryId, size, description,quantity) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, author);
            ps.setString(5, publisher);
            ps.setString(6, page);
            ps.setString(7, language);
            ps.setString(8, category);
            ps.setString(9, size);
            ps.setString(10, descrip);
            ps.setString(11, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book select(String id) {
        String query = """
                       SELECT p.*,cateName
                       FROM Book p
                       INNER JOIN Category ct ON p.categoryId = ct.id
                       where p.id = ?;""";

        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookCate ht = new BookCate();
                ht.setCateID(rs.getInt("categoryId"));
                ht.setNameCate(rs.getString("cateName"));

                return new Book(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getString(8), ht, rs.getString(10), rs.getString(11), rs.getInt(12));
            }

        } catch (Exception e) {
        }

        return null;
    }

    public void update(String id, String name, String image, String price, String author,
            String publisher, String page,
            String language, String category, String size, String descrip, String quantity) {
        String query = """
                       UPDATE Book
                       SET 
                           name = ?,
                           image = ?,
                           price = ?,
                           author = ?,
                           publisher = ?,
                           page = ?,
                           language = ?,
                           categoryId = ?,
                           size = ?,
                           description = ?,
                           quantity = ?
                       WHERE id = ?;""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, author);
            ps.setString(5, publisher);
            ps.setString(6, page);
            ps.setString(7, language);
            ps.setString(8, category);
            ps.setString(9, size);
            ps.setString(10, descrip);
            ps.setString(11, quantity);
            ps.setString(12, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertCategory(String name) {
        String query = """
                       INSERT INTO Category (cateName)
                       VALUES (?)""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, name);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account checkAcc(String user, String pass) {
        String query = "SELECT *\n"
                + "FROM Account \n"
                + "WHERE username = ? AND password = ?;";

        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {

                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4));
            }

        } catch (Exception e) {
        }

        return null;
    }

    public void insertAcc(String user, String pass) {
        String query = """
                       insert into Account
                       values (?,?,?);""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, "1");
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void createCart(String accId, String bookid, String quantity) {
        String query = "insert into Cart(Acc_ID, Book_ID, quantity) values(?,?,?)";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, accId);
            ps.setString(2, bookid);
            ps.setString(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Carts> cartById(String accID) {
        ArrayList<Carts> list = new ArrayList<>();
        String query = "select * from Cart where Acc_ID = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, accID);
            ResultSet rss = ps.executeQuery();
            while (rss.next()) {
                Carts ct = new Carts();
                ct.setAccID(rss.getInt("Acc_ID"));
                ct.setQuantity(rss.getInt(3));
                Book bk = this.select(rss.getString("Book_ID"));
                ct.setBookId(bk);
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateCart(String accID, int bookID, String quantity) {
        ArrayList<Carts> list = this.cartById(accID);

        for (Carts ct : list) {
            if (ct.getBookId().getId() == bookID) {
                String query = """
                               update Cart
                               set quantity = ?
                               where Book_ID = ? and Acc_ID=?""";
                try {
                    con = new DBContext().connection;
                    ps = con.prepareStatement(query);
                    ps.setString(1, quantity);
                    ps.setInt(2, bookID);
                    ps.setString(3, accID);
                    ps.executeUpdate();

                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        String query = """
                               insert into Cart(Acc_ID,Book_ID,quantity)
                               values(?,?,?)""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, accID);
            ps.setInt(2, bookID);
            ps.setString(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCart(String accID) {
        String query = """
                              delete from Cart 
                               where Acc_ID=?""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, accID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<OrderData> getOrderData() {
        ArrayList<OrderData> list = new ArrayList<>();
        try {
            String query = """
                          select * from Orders o
                          join OrderDetails od on o.id =od.Order_ID
                           order by o.id
                           """;
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                OrderData od = new OrderData();
                od.setAddress(rst.getString("address"));
                od.setDate(rst.getString("datess"));
                od.setName(rst.getString("name"));
                od.setQuantity(rst.getInt("quantity"));
                od.setPhoneNumber(rst.getString("phoneNumber"));
                od.setPayMethod(rst.getString("payMethod"));
                od.setStatus(rst.getString("status"));
                od.setPrice(rst.getDouble("price"), rst.getInt("quantity"));
                od.setOrderID(rst.getString(1));
                od.setBookid(this.select(rst.getString("Book_ID")));
                list.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<OrderData> getOrderDatabyAccID(String accID) {
        ArrayList<OrderData> list = new ArrayList<>();
        try {
            String query = """
                         select * from Orders o
                          join OrderDetails od on o.id =od.Order_ID
                          join Account a on a.id = o.accID
                          where a.id = ?
                           order by datess""";
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, accID);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                OrderData od = new OrderData();
                od.setAddress(rst.getString("address"));

                od.setDate(rst.getString("datess"));
                od.setName(rst.getString("name"));
                od.setQuantity(rst.getInt("quantity"));
                od.setPhoneNumber(rst.getString("phoneNumber"));
                od.setPayMethod(rst.getString("payMethod"));
                od.setStatus(rst.getString("status"));
                od.setPrice(rst.getDouble("price"), rst.getInt("quantity"));
                od.setBookid(this.select(rst.getString("Book_ID")));
                list.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public void insertOrder(List<Order> orders) {
//        String query = "insert into Orders\n"
//                + "values (?,?,?);";
//        for (Order o : orders) {
//            try {
//                con = new DBContext().connection;
//                ps = con.prepareStatement(query);
//                ps.setInt(1, o.getOrderId());
//                ps.setInt(2, o.getbId().getId());
//                ps.setInt(3, o.getQuantity());
//                ps.executeUpdate();
//
//            } catch (Exception e) {
//            }
//        }
//    }
//
    public void insertOrders(String name, String phoneNumber, String address, String status,
            String accID, String payMethod) {
        String query = """
                       insert into Orders(name,phoneNumber,address,datess,status,accID,payMethod)
                       values(?,?,?,CURRENT_TIMESTAMP,?,?,?)""";

        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phoneNumber);
            ps.setString(3, address);
            ps.setString(4, status);
            ps.setString(5, accID);
            ps.setString(6, payMethod);
            ps.executeUpdate();

        } catch (Exception e) {
        }

    }

    public String takeOrderId() {
        String query = "SELECT MAX(id) AS max_id FROM Orders";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("max_id");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertOrderDetails(String orderid, int bookid, int quantity, double price) {
        String query = """
                      insert into OrderDetails(Order_ID,Book_ID,quantity,price)
                      values(?,?,?,?)""";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setString(1, orderid);
            ps.setInt(2, bookid);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateQuantity(int num, int id) {
        try {
            String sql = "UPDATE Book \n"
                    + "SET quantity = quantity + ?\n"
                    + "WHERE id = ?;";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void updateStatus(String status, String id) {
        try {
            String sql = """
                         UPDATE Orders
                         SET status = ? 
                         WHERE id = ?;""";
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setString(2, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public ArrayList<Data> getDatabyMonth(int cateID, int[] month) {
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < month.length; i++) {
            try {
                String sql = """
                         select sum(od.price*od.quantity) as total
                         from OrderDetails od 
                         join Orders o on od.Order_ID = o.id
                         join Book b on b.id=od.Book_ID
                         where MONTH(o.datess) = ? and b.categoryId= ?""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                Data dt = new Data();
                ps.setInt(1, month[i]);
                ps.setInt(2, cateID);
                rs = ps.executeQuery();
                if (rs.next()) {

                    dt.setData(rs.getDouble("total"));
                } else {
                    dt.setData(0.0);
                }
                list.add(dt);
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return list;
    }

    public ArrayList<Data> getDataTotalbyMonth(int[] month) {
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < month.length; i++) {
            try {
                String sql = """
                         select sum(od.price*od.quantity) as total
                         from OrderDetails od 
                         join Orders o on od.Order_ID = o.id
                         join Book b on b.id=od.Book_ID
                         where MONTH(o.datess) = ?""";
                con = new DBContext().connection;
                ps = con.prepareStatement(sql);
                Data dt = new Data();
                ps.setInt(1, month[i]);
                rs = ps.executeQuery();
                if (rs.next()) {

                    dt.setData(rs.getDouble("total"));
                } else {
                    dt.setData(0.0);
                }
                list.add(dt);
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return list;
    }

//
//    public void insertCustomer(String zip, String name, String address, String phone, int accountId) {
//        String query = "INSERT INTO [customerss]( zip, [name], [address], phoneNumber, accountId)\n"
//                + "VALUES (?, ?, ?, ?, ?);";
//
//        try {
//            con = new DBContext().connection;
//            ps = con.prepareStatement(query);
//            ps.setString(1, zip);
//            ps.setString(2, name);
//            ps.setString(3, address);
//            ps.setString(4, phone);
//            ps.setInt(5, accountId);
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//        }
//
//    }
//
//    public int OrderID() {
//        String query = "SELECT MAX(id) AS id FROM OrderDetails;";
//        int a = 0;
//        try {
//            con = new DBContext().connection;
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                a = rs.getInt(1);
//            }
//        } catch (Exception e) {
//        }
//        return a;
//    }
//
//    public ArrayList<OrderDetail> getOrderDetail() throws ParseException {
//        ArrayList<OrderDetail> books = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM OrderDetails;";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            while (rs.next()) {
//                OrderDetail h = new OrderDetail();
//                h.setId(rs.getInt("id"));
//                String dateString = rs.getString("date");
//                h.setDate(sdf.parse(dateString));
//                h.setPayMethod(rs.getString("payMethod"));
//                h.setTotal(rs.getDouble("totalMoney"));
//                h.setStatus(rs.getString("status"));
//                books.add(h);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return books;
//    }
//
//    public ArrayList<OrderDetail> getOrderDetailbyID(int accID) throws ParseException {
//        ArrayList<OrderDetail> books = new ArrayList<>();
//        try {
//            String sql = "select * from OrderDetails o\n"
//                    + "where o.id  = ?\n"
//                    + "	";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            while (rs.next()) {
//                OrderDetail h = new OrderDetail();
//                h.setId(rs.getInt("id"));
//                String dateString = rs.getString("date");
//                h.setDate(sdf.parse(dateString));
//                h.setPayMethod(rs.getString("payMethod"));
//                h.setTotal(rs.getDouble("totalMoney"));
//                h.setStatus(rs.getString("status"));
//                books.add(h);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return books;
//    }
//
//    public ArrayList<Customer> getCustomer() throws ParseException {
//        ArrayList<Customer> books = new ArrayList<>();
//        try {
//            String sql = "SELECT *\n"
//                    + "FROM customerss s\n"
//                    + "JOIN OrderDetails o ON s.id = o.id;";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            while (rs.next()) {
//
//                Customer h1 = new Customer();
//                OrderDetail h = new OrderDetail();
//                h.setId(rs.getInt("id"));
//                String dateString = rs.getString("date");
//                h.setDate(sdf.parse(dateString));
//                h.setPayMethod(rs.getString("payMethod"));
//                h.setTotal(rs.getDouble("totalMoney"));
//                h1.setAddress(rs.getString("address"));
//                h1.setName(rs.getString("name"));
//                h1.setPhoneNumber(rs.getString("phoneNumber"));
//                h1.setaId(rs.getInt("accountId"));
//                h1.setZip(rs.getInt("zip"));
//                h1.setoId(h);
//                books.add(h1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return books;
//    }
//
//    public ArrayList<Data> getData() {
//        ArrayList<Data> books = new ArrayList<>();
//        try {
//            String sql = "SELECT \n"
//                    + "    c.name,\n"
//                    + "    SUM(o.quantity) AS total_quantity,\n"
//                    + "    SUM(o.quantity * p.price) AS total_sales\n"
//                    + "FROM \n"
//                    + "    Orders o\n"
//                    + "INNER JOIN \n"
//                    + "    products p ON o.bookid = p.id\n"
//                    + "INNER JOIN \n"
//                    + "    categories c ON p.cateId = c.cateId\n"
//                    + "GROUP BY \n"
//                    + "    c.name;";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                Data h = new Data();
//                h.setName(rs.getString("name"));
//
//                h.setQuantity(rs.getInt("total_quantity"));
//
//                h.setSales(rs.getDouble("total_sales"));
//
//                books.add(h);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return books;
//    }
//
//    public ArrayList<CusData> getCustomerData(int accountID) {
//        ArrayList<CusData> books = new ArrayList<>();
//        try {
//            String sql = "SELECT p.name, p.price, p.image, o.quantity, od.date,od.status c.name as cateName\n"
//                    + "FROM OrderDetails od\n"
//                    + "JOIN Orders o ON od.id = o.orderId\n"
//                    + "JOIN Products p ON o.bookId = p.id\n"
//                    + "JOIN Categories c ON p.cateId = c.cateId\n"
//                    + "join customerss ct on ct.id =od.id\n"
//                    + "where ct.accountId = ?";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, accountID);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                CusData h = new CusData();
//                h.setName(rs.getString("name"));
//
//                h.setQuantity(rs.getInt("quantity"));
//                h.setStatus(rs.getString("status"));
//                h.setImage(rs.getString("image"));
//                h.setDate(rs.getString("date"));
//                h.setCateName(rs.getString("cateName"));
//                h.setPrice(rs.getDouble("price"));
//
//                books.add(h);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return books;
//    }
//
//    public boolean checkCartID(int accID) {
//        try {
//            String sql = """
//                         select * from Carts
//                         where AccountID = ?""";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, accID);
//            rs = ps.executeQuery();
//
//            return rs.next();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return false;
//    }
//
//    public void insertAcc(int accId, String bookID, String quantity, Double price) {
//        try {
//            String sql = "INSERT INTO Carts (AccountID, bookID, quantity, price)\n"
//                    + "VALUES (?, ?, ?, ?);";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, accId);
//            ps.setString(2, bookID);
//            ps.setString(3, quantity);
//            ps.setDouble(4, price);
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//    }
//
//    public void insertCart(int accId, String bookID, String quantity, double price) {
//        try {
//            String sql = "UPDATE Carts\n"
//                    + "SET price = ?,\n"
//                    + "    bookID = ?,\n"
//                    + "    quantity = ?\n"
//                    + "WHERE AccountID = ?;";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1, bookID);
//            ps.setString(2, quantity);
//            ps.setDouble(3, price);
//            ps.setInt(4, accId);
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//    }
//
//    public void updateCart(int accId, String bookID, String quantity, double price) {
//        try {
//            String sql = "UPDATE Carts\n"
//                    + "SET price = ?,    \n"
//                    + "    quantity = ?\n"
//                    + "WHERE AccountID = ? and bookID = ?";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//
//            ps.setDouble(1, price);
//            ps.setString(2, quantity);
//            ps.setInt(3, accId);
//            ps.setString(4, bookID);
//
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//    }
//
//    public ArrayList<CartData> getCart(int accId) {
//        ArrayList<CartData> list = new ArrayList<>();
//        try {
//            String sql = "select * from carts\n"
//                    + "where AccountID = ?";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, accId);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                CartData h = new CartData();
//                h.setAccID(rs.getInt("AccountID"));
//
//                h.setQuantity(rs.getInt("quantity"));
//
//                Book b = this.select(rs.getString("bookID"));
//                h.setPrice(rs.getDouble("price"));
//                h.setbId(b);
//                list.add(h);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return list;
//    }
//
//
//    public ArrayList<Order> getOrderbyOrderDetails(String id) {
//        ArrayList<Order> list = new ArrayList<>();
//        try {
//            String sql = "select * from Orders o\n"
//                    + "join OrderDetails od on od.id = o.orderId\n"
//                    + "where orderId = ?";
//            con = new DBContext().connection;
//            ps = con.prepareStatement(sql);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                Order h = new Order();
//                Book b = this.select(rs.getString("bookId"));
//                h.setBookId(b);
//
//                h.setQuantity(rs.getInt("quantity"));
//                h.setOrderId(rs.getInt("orderId"));
//                h.setPrice(rs.getDouble("price"));
//                list.add(h);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//
//        return list;
//    }
//
//
//    public void deleteCart(int i) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
