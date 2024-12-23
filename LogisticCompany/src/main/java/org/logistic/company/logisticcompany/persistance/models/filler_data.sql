-- Insert test data into offices
INSERT INTO offices (name, address, city, "working-hours")
VALUES
    ('Central Office', '123 Main St', 'Metropolis', '9:00 AM - 5:00 PM'),
    ('North Branch', '456 North St', 'North City', '10:00 AM - 6:00 PM'),
    ('South Branch', '789 South St', 'South City', '8:00 AM - 4:00 PM');

-- Insert test data into users
INSERT INTO users (username, role, office_id)
VALUES
    ('john_doe', 'employee', 1),
    ('jane_smith', 'employee', 2),
    ('alice_walker', 'client', NULL),
    ('bob_marley', 'client', NULL);

-- Insert test data into packages
INSERT INTO packages (source_id, destination_id, employee_id, sender_id, recipient_id, price, status, send_at, received_at)
VALUES
    (1, 2, 1, 3, 4, 49.99, 'In Transit', '2023-12-01', '2023-12-05'),
    (2, 3, 2, 4, 3, 29.99, 'Delivered', '2023-11-20', '2023-11-22'),
    (3, 1, 1, 4, 3, 99.99, 'Pending', '2023-12-07', '2023-12-10'),
    (1, 3, 2, 3, 4, 75.00, 'Delivered', '2023-11-30', '2023-12-03');
