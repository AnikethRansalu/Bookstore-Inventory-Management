USE bookstore;

INSERT INTO roles (role_name) VALUES ('Admin'), ('Staff');

INSERT INTO users (username, password, role_id)
VALUES ('admin', 'admin123', 1),
       ('staff', 'staff123', 2);

INSERT INTO categories (category_name)
VALUES ('Fiction'), ('Science'), ('Technology'), ('Comics');

INSERT INTO suppliers (name, contact, address)
VALUES ('Pearson', '011-1234567', 'Colombo'),
       ('McGraw Hill', '011-7654321', 'Kandy');

INSERT INTO books (title, author, price, quantity, category_id, supplier_id)
VALUES ('Java Basics', 'John Doe', 1200.00, 15, 3, 1),
       ('Data Structures', 'Jane Smith', 1500.00, 10, 3, 2);

