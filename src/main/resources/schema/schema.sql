-- schema.sql
-- Create products table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL
);

-- Create inventory table
CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    cost DECIMAL(10, 2)
);
