package com.bookstore.ui.books;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.SupplierDAO;
import com.bookstore.model.Book;
import com.bookstore.ui.util.ComboItem;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class EditBookFrame extends JDialog {

    private JTextField txtTitle, txtAuthor, txtPrice, txtQty;
    private JComboBox<ComboItem> cbCategory, cbSupplier;
    private JButton btnSave, btnCancel;
    private BookListFrame parent;
    private int bookId;

    public EditBookFrame(BookListFrame parent, int bookId) {
        super(parent, "Edit Book", true);
        this.parent = parent;
        this.bookId = bookId;
        setSize(420, 320);
        setLocationRelativeTo(parent);
        init();
        loadBook();
    }

    private void init() {
        txtTitle = new JTextField(25);
        txtAuthor = new JTextField(25);
        txtPrice = new JTextField(10);
        txtQty = new JTextField(10);
        cbCategory = new JComboBox<>();
        cbSupplier = new JComboBox<>();
        btnSave = new JButton("Update");
        btnCancel = new JButton("Cancel");

        loadCombos();

        btnSave.addActionListener(e -> onSave());
        btnCancel.addActionListener(e -> dispose());

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        int row=0;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Title:"), c);
        c.gridx=1; p.add(txtTitle, c);
        row++;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Author:"), c);
        c.gridx=1; p.add(txtAuthor, c);
        row++;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Price:"), c);
        c.gridx=1; p.add(txtPrice, c);
        row++;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Quantity:"), c);
        c.gridx=1; p.add(txtQty, c);
        row++;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Category:"), c);
        c.gridx=1; p.add(cbCategory, c);
        row++;
        c.gridx=0; c.gridy=row; p.add(new JLabel("Supplier:"), c);
        c.gridx=1; p.add(cbSupplier, c);
        row++;
        c.gridx=0; c.gridy=row; c.gridwidth=2; c.anchor = GridBagConstraints.CENTER;
        JPanel buttons = new JPanel();
        buttons.add(btnSave); buttons.add(btnCancel);
        p.add(buttons, c);

        getContentPane().add(p);
    }

    private void loadCombos() {
        cbCategory.removeAllItems();
        cbSupplier.removeAllItems();
        Map<Integer,String> cats = new CategoryDAO().getAllCategories();
        Map<Integer,String> sups = new SupplierDAO().getAllSuppliers();
        for (Map.Entry<Integer,String> e : cats.entrySet()) cbCategory.addItem(new ComboItem(e.getKey(), e.getValue()));
        for (Map.Entry<Integer,String> e : sups.entrySet()) cbSupplier.addItem(new ComboItem(e.getKey(), e.getValue()));
    }

    private void loadBook() {
        Book b = new BookDAO().getBookById(bookId);
        if (b == null) {
            JOptionPane.showMessageDialog(this, "Book not found.");
            dispose();
            return;
        }
        txtTitle.setText(b.getTitle());
        txtAuthor.setText(b.getAuthor());
        txtPrice.setText(String.valueOf(b.getPrice()));
        txtQty.setText(String.valueOf(b.getQuantity()));

        // select category and supplier in combos
        for (int i = 0; i < cbCategory.getItemCount(); i++) {
            ComboItem item = cbCategory.getItemAt(i);
            if (item.getId() == b.getCategoryId()) { cbCategory.setSelectedIndex(i); break; }
        }
        for (int i = 0; i < cbSupplier.getItemCount(); i++) {
            ComboItem item = cbSupplier.getItemAt(i);
            if (item.getId() == b.getSupplierId()) { cbSupplier.setSelectedIndex(i); break; }
        }
    }

    private void onSave() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and author required.");
            return;
        }
        double price;
        int qty;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            qty = Integer.parseInt(txtQty.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price or Quantity invalid.");
            return;
        }
        if (cbCategory.getSelectedItem() == null || cbSupplier.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select category and supplier.");
            return;
        }
        int catId = ((ComboItem) cbCategory.getSelectedItem()).getId();
        int supId = ((ComboItem) cbSupplier.getSelectedItem()).getId();

        Book b = new Book(bookId, title, author, price, qty, catId, supId);
        new BookDAO().updateBook(b);
        JOptionPane.showMessageDialog(this, "Book updated.");
        parent.loadBooks();
        dispose();
    }
}