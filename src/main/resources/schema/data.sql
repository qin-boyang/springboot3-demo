-- data.sql
-- Insert sample products
INSERT INTO products (name, description, price) VALUES
('Laptop', 'High-performance laptop for work and gaming', 1299.99),
('Smartphone', 'Latest model smartphone with advanced features', 899.99),
('Headphones', 'Wireless noise-cancelling headphones', 199.99),
('Tablet', '10-inch tablet with high-resolution display', 499.99),
('Smart Watch', 'Fitness tracking smartwatch with heart monitor', 299.99);

-- Insert sample inventory records
INSERT INTO inventory (quantity, cost) VALUES
(50, 1050.00),  -- Inventory for Laptop
(100, 750.00),  -- Inventory for Smartphone
(200, 150.00),  -- Inventory for Headphones
(75, 400.00),   -- Inventory for Tablet
(150, 225.00);  -- Inventory for Smart Watch
