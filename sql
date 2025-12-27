create database Project1;
use Project1;

CREATE TABLE Customer (
    CustomerId INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Address VARCHAR(200) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    Email VARCHAR(100) UNIQUE
);

INSERT INTO Customer VALUES
(1, 'Ravi Kumar', 'Chennai', '9876543210', 'ravi@example.com'),
(2, 'Priya Sharma', 'Delhi', '9123456780', 'priya@example.com'),
(3, 'Arun Singh', 'Mumbai', '9988776655', 'arun@example.com'),
(4, 'Meena Reddy', 'Hyderabad', '9012345678', 'meena@example.com'),
(5, 'Suresh Iyer', 'Bangalore', '9098765432', 'suresh@example.com'),
(6, 'Anjali Verma', 'Kolkata', '9123987654', 'anjali@example.com'),
(7, 'Rahul Desai', 'Pune', '9345678901', 'rahul@example.com');

CREATE TABLE Meter (
    MeterId INT PRIMARY KEY,
    CustomerId INT,
    InstallationDate DATE NOT NULL,
    LastReadingDate DATE NOT NULL,
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId) ON DELETE CASCADE
);


INSERT INTO Meter VALUES
(101, 1, '2024-01-15', '2024-11-20'),
(102, 2, '2023-12-10', '2024-11-25'),
(103, 3, '2025-01-05', '2025-11-26'),
(104, 4, '2024-02-10', '2024-11-18'),
(105, 5, '2023-08-25', '2024-10-30'),
(106, 6, '2025-03-12', '2025-11-20'),
(107, 7, '2024-05-05', '2024-11-22');


CREATE TABLE ElectricityUsage (
    UsageId INT PRIMARY KEY,
    MeterId INT,
    ReadingDate DATE NOT NULL,
    UsageUnits NUMERIC CHECK (UsageUnits >= 0),
    FOREIGN KEY (MeterId) REFERENCES Meter(MeterId) ON DELETE CASCADE
);


INSERT INTO ElectricityUsage VALUES
(1001, 101, '2024-11-01', 150),
(1002, 101, '2024-11-15', 100),
(1003, 102, '2024-11-10', 250),
(1004, 103, '2025-11-20', 300),
(1005, 104, '2024-11-10', 180),
(1006, 105, '2024-10-28', 220),
(1007, 106, '2025-11-18', 275),
(1008, 107, '2024-11-21', 190);


CREATE TABLE Bill (
    BillId INT PRIMARY KEY,
    MeterId INT,
    BillDate DATE NOT NULL,
    AmountDue NUMERIC CHECK (AmountDue >= 0),
    DueDate DATE NOT NULL,
    Paid INT NOT NULL DEFAULT 0 CHECK (Paid IN (0,1)),
    FOREIGN KEY (MeterId) REFERENCES Meter(MeterId) ON DELETE CASCADE
);


INSERT INTO Bill VALUES
(2001, 101, '2024-11-05', 500, '2024-11-30', 0),
(2002, 102, '2024-11-12', 800, '2024-12-01', 1),
(2003, 103, '2025-11-22', 1200, '2025-12-10', 0),
(2004, 104, '2024-11-15', 600, '2024-12-05', 1),
(2005, 105, '2024-10-30', 750, '2024-11-25', 0),
(2006, 106, '2025-11-19', 950, '2025-12-08', 0),
(2007, 107, '2024-11-23', 500, '2024-12-12', 1);


CREATE TABLE Payment (
    PaymentId INT PRIMARY KEY,
    BillId INT,
    PaymentDate DATE NOT NULL,
    AmountPaid NUMERIC CHECK (AmountPaid >= 0),
    FOREIGN KEY (BillId) REFERENCES Bill(BillId) ON DELETE CASCADE
);


INSERT INTO Payment VALUES
(3001, 2002, '2024-11-15', 800),
(3002, 2001, '2024-11-20', 500),
(3003, 2003, '2025-11-25', 600),
(3004, 2004, '2024-11-18', 600),
(3005, 2005, '2024-11-02', 400),
(3006, 2006, '2025-11-21', 500),
(3007, 2007, '2024-11-24', 500),
(3008, 2005, '2024-11-15', 350);

-- k. Fetch all records

SELECT * FROM Customer;
SELECT * FROM Meter;
SELECT * FROM ElectricityUsage;
SELECT * FROM Bill;
SELECT * FROM Payment;

SELECT *
FROM Customer c
LEFT JOIN Meter m ON c.CustomerId = m.CustomerId
LEFT JOIN ElectricityUsage eu ON m.MeterId = eu.MeterId
LEFT JOIN Bill b ON m.MeterId = b.MeterId
LEFT JOIN Payment p ON b.BillId = p.BillId;


-- l. Total electricity usage per Meter (only >200 units)

SELECT MeterId, SUM(UsageUnits) AS TotalUsage
FROM ElectricityUsage
GROUP BY MeterId
HAVING SUM(UsageUnits) > 200;


-- m. Customers and their total unpaid bill amounts (descending)

SELECT c.CustomerId, c.Name, SUM(b.AmountDue) AS TotalUnpaid
FROM Customer c
JOIN Meter m ON c.CustomerId = m.CustomerId
JOIN Bill b ON m.MeterId = b.MeterId
WHERE b.Paid = 0
GROUP BY c.CustomerId, c.Name
ORDER BY TotalUnpaid DESC;



-- n. All bills with payment status (sorted by BillDate ascending)

SELECT BillId, MeterId, BillDate,
       CASE WHEN Paid = 1 THEN 'Paid' ELSE 'Unpaid' END AS Status
FROM Bill
ORDER BY BillDate ASC;

-- o. Customers with at least one meter installed after '2023-12-31'

SELECT  distinct c.Name,m.InstallationDate
FROM Customer c
JOIN Meter m ON c.CustomerId = m.CustomerId
WHERE m.InstallationDate > '2023-12-31';


-- p. Meters with last reading date and total usage (descending order of usage)

SELECT m.MeterId, m.LastReadingDate, SUM(e.UsageUnits) AS TotalUsage
FROM Meter m
JOIN ElectricityUsage e ON m.MeterId = e.MeterId
GROUP BY m.MeterId
ORDER BY TotalUsage DESC;

