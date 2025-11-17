package com.bookstore.dao;

import com.bookstore.model.Book;
import com.bookstore.dao.BaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO {

    // ------------------------------
    // ADD BOOK
    // ------------------------------
    public void addBook(Book b) {
        String sql = "INSERT INTO books (title, author, price, quantity, category_id, supplier_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setDouble(3, b.getPrice());
            ps.setInt(4, b.getQuantity());
            ps.setInt(5, b.getCategoryId());
            ps.setInt(6, b.getSupplierId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------
    // GET ALL BOOKS
    // ------------------------------
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM books ORDER BY id DESC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Book b = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getInt("category_id"),
                    rs.getInt("supplier_id")
                );

                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ------------------------------
    // GET BOOK BY ID
    // ------------------------------
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("category_id"),
                        rs.getInt("supplier_id")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ------------------------------
    // UPDATE BOOK
    // ------------------------------
    public void updateBook(Book b) {
        String sql = "UPDATE books SET title=?, author=?, price=?, quantity=?, category_id=?, supplier_id=? "
                   + "WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setDouble(3, b.getPrice());
            ps.setInt(4, b.getQuantity());
            ps.setInt(5, b.getCategoryId());
            ps.setInt(6, b.getSupplierId());
            ps.setInt(7, b.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------
    // DELETE BOOK
    // ------------------------------
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}