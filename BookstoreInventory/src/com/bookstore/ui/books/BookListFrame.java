package com.bookstore.ui.books;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.SupplierDAO;
import com.bookstore.model.Book;
import com.bookstore.ui.util.ComboItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class BookListFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;

    public BookListFrame() {
        setTitle("Books - Bookstore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 450);
        setLocationRelativeTo(null);

        initComponents();
        loadBooks();
    }

    private void initComponents() {
        tableModel = new DefaultTableModel(new Object[]{"ID","Title","Author","Price","Quantity","Category","Supplier"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        btnAdd.addActionListener(e -> {
            AddBookFrame add = new AddBookFrame(this);
            add.setVisible(true);
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Select a book to edit."); return; }
            int id = (int) tableModel.getValueAt(row, 0);
            EditBookFrame edit = new EditBookFrame(this, id);
            edit.setVisible(true);
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this, "Select a book to delete."); return; }
            int id = (int) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Delete selected book?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new BookDAO().deleteBook(id);
                loadBooks();
                JOptionPane.showMessageDialog(this, "Book deleted.");
            }
        });

        btnRefresh.addActionListener(e -> loadBooks());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.add(btnAdd);
        top.add(btnEdit);
        top.add(btnDelete);
        top.add(btnRefresh);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    public void loadBooks() {
        SwingUtilities.invokeLater(() -> {
            try {
                tableModel.setRowCount(0);
                BookDAO dao = new BookDAO();
                List<Book> books = dao.getAllBooks();

                // load maps for id->name
                Map<Integer,String> cats = new CategoryDAO().getAllCategories();
                Map<Integer,String> sups = new SupplierDAO().getAllSuppliers();

                for (Book b : books) {
                    String catName = cats.getOrDefault(b.getCategoryId(), String.valueOf(b.getCategoryId()));
                    String supName = sups.getOrDefault(b.getSupplierId(), String.valueOf(b.getSupplierId()));
                    tableModel.addRow(new Object[]{
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getPrice(),
                        b.getQuantity(),
                        catName,
                        supName
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookListFrame().setVisible(true));
    }
}