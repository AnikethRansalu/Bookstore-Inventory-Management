package com.bookstore.model;

import java.util.Date;

public class StockChange {
    private int id;
    private int bookId;
    private String changeType;   // add / remove
    private int quantity;
    private Date changeDate;

    public StockChange() {}

    public StockChange(int id, int bookId, String changeType, int quantity, Date changeDate) {
        this.id = id;
        this.bookId = bookId;
        this.changeType = changeType;
        this.quantity = quantity;
        this.changeDate = changeDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getChangeType() { return changeType; }
    public void setChangeType(String changeType) { this.changeType = changeType; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Date getChangeDate() { return changeDate; }
    public void setChangeDate(Date changeDate) { this.changeDate = changeDate; }
}
