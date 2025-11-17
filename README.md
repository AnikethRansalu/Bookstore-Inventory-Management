# 📘 Bookstore Inventory Management System  
> **Java Swing + MySQL + JDBC**

A desktop-based inventory management application for bookstores.  
Supports user login, book management, suppliers, stock tracking, and reporting.

---

## ✅ Table of Contents
1. Overview  
2. Tech Stack  
3. Features  
4. Folder Structure  
5. Database Setup  
6. Application Setup (NetBeans)  
7. Testing the System  
8. Branching Strategy  
9. Git Workflow  
10. Team Roles  
11. Future Enhancements  

---

## ✅ 1) Overview
The **Bookstore Inventory Management System** provides a simple, modular, and scalable system for managing bookstore stock, employees, and suppliers.

This is a desktop application created using **Java Swing** and **MySQL**, with structured layers for:
- DAO  
- Models  
- UI  
- Services  
- Utilities  

---

## ✅ 2) Tech Stack

| Layer       | Technology |
|------------|------------|
| UI         | Java Swing |
| Backend    | Java |
| Database   | MySQL |
| Connector  | JDBC |
| Versioning | Git + GitHub |
| IDE        | NetBeans |

---

## ✅ 3) Key Features
- Admin/Staff login  
- CRUD for Book records  
- Category management  
- Supplier management  
- Stock change logs  
- Dashboard + UI  
- Printable reports (future)  

---

## ✅ 4) Folder Structure

```
BookstoreInventoryManagement/
│
├── database/
│   ├── schema.sql
│   └── seed_data.sql
│
├── docs/
│
└── src/
    └── com.bookstore
        ├── dao
        │    ├── BaseDAO.java
        │    ├── UserDAO.java
        │    └── UserDAOTest.java
        │
        ├── model
        │    ├── User.java
        │    ├── Role.java
        │    ├── Category.java
        │    ├── Supplier.java
        │    ├── Book.java
        │    └── StockChange.java
        │
        ├── service
        │    └── (future modules)
        │
        ├── ui
        │    └── (future Swing forms)
        │
        └── utils
             ├── DBConnection.java
             └── TestConnection.java
```

---

## ✅ 5) Database Setup

### ✅ Step-1 — Open MySQL Workbench

### ✅ Step-2 — Create schema
```
CREATE DATABASE bookstore;
```

### ✅ Step-3 — Execute provided schema
Run:
```
database/schema.sql
```

### ✅ Step-4 — Insert sample records
Run:
```
database/seed_data.sql
```

### ✅ Step-5 — Verify
```
SELECT * FROM bookstore.users;
```

---

## ✅ 6) Application Setup (NetBeans)

### ✅ Step-1 — Open Project
```
File → Open Project
Select project folder
```

### ✅ Step-2 — Add JDBC Driver
Download MySQL Connector:
`mysql-connector-j-8.x.x.jar`

Add in NetBeans:
```
Right-click Project → Properties → Libraries → Classpath → Add JAR
```

### ✅ Step-3 — Edit DBConnection
Update:
```
src/com/bookstore/utils/DBConnection.java
```

### ✅ Step-4 — Test Database
Run:
```
TestConnection.java
```

Expected:
✅ DB Connected!

---

## ✅ 7) Testing the System

### ✅ Test login DAO
Open:
```
src/com/bookstore/dao/UserDAOTest.java
```

Run:
SHIFT + F6

Expected:
✅ Login success → admin

---

## ✅ 8) Branching Strategy

| Branch | Purpose |
|--------|---------|
| main | Stable final code |
| dev | Core development |
| feature/auth | Login + roles |
| feature/books | Book CRUD |
| feature/categories | Category CRUD |
| feature/suppliers | Supplier CRUD |
| feature/stock | Stock transactions |
| feature/reports | Reports |
| feature/ui | UI screens |
| feature/testing-docs | Testing + docs |

---

## ✅ 9) Git Workflow

### Clone
```
git clone <repo-link>
cd BookstoreInventoryManagement
git checkout dev
```

### Start Feature
```
git checkout feature/<name>
git pull origin dev
```

### Commit
```
git add .
git commit -m "message"
git push origin feature/<name>
```

### Pull Request
Target: dev  
```
Create PR → Review → Merge
```

---

## ✅ 10) Team Roles

| Member | Work |
|--------|------|
| 1 | Backend foundation |
| 2 | Login UI + integration |
| 3 | Books CRUD |
| 4 | Category CRUD |
| 5 | Supplier Module |
| 6 | Stock module |
| 7 | Reports |
| 8 | Dashboard |
| 9 | Styling + UI polish |
| 10 | Testing + Docs |

---



