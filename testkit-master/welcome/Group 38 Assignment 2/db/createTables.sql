/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Eric
 * Created: 14/05/2020
 */

CREATE TABLE Users (
    userID int NOT NULL,
    userPassword int(10),
    userRegistrationDate date,
    PRIMARY KEY (userID)
);

CREATE TABLE Staff (
    staffID int NOT NULL,
    staffName varchar(50),
    staffEmail varchar(50),
    staffPhone int(12),
    staffHours varchar(50),
    staffUserID int,
    staffRole varchar(20),
    PRIMARY KEY (staffID),
    FOREIGN KEY (staffUserID) REFERENCES Users(userID)
);

CREATE TABLE Customer (
    custID int NOT NULL,
    custName varchar(50),
    custPhone int(10),
    custEmail varchar(20),
    custUserID int,
    PRIMARY KEY (custID),
    FOREIGN KEY (custUserID) REFERENCES Users(userID)
);

CREATE TABLE Product (
    productID int NOT NULL,
    productName varchar(20),
    productPrice double(10),
    productDescription varchar(150),
    productStock int(5),
    productImageLink varchar(1000),
    productImageIconLink varchar(1000),
    productType varchar(1000),
    productManufacturer varchar(1000),
    PRIMARY KEY (productID)
);

CREATE TABLE Shipping(
    shippingID int NOT NULL,
    shippingStreetNumber int(3),
    shippingStreetName varchar(40),
    shippingPostcode int(4),
    shippingState varchar(3),
    shippingCountry varchar(30),
    shippingApartmentNo int(15),
    PRIMARY KEY (shippingID)
)

CREATE TABLE Orders (
    orderID int NOT NULL,
    orderDate date,
    custID int(8),
    shippingID int(8),
    PRIMARY KEY (orderID),
    FOREIGN KEY (custID) REFERENCES Customer(custID),
    FOREIGN KEY (shippingID) REFERENCES Shipping(shippingID)
);

CREATE TABLE OrderLine (
    productID int NOT NULL,
    orderID int NOT NULL,
    quantity int(4),
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (orderID) REFERENCES Order(orderID)
);

CREATE TABLE Payment (
    paymentID int NOT NULL,
    paymentCreditCard boolean,
    paymentConfirm boolean,
    paymentPaypal boolean,
    PRIMARY KEY (paymentID)
)

CREATE TABLE CreditCard (
    cardID int NOT NULL,
    creditName varchar(50),
    creditNo int(16),
    creditSecurityNo int(3),
    creditExpiryDate Date,
    PRIMARY KEY (cardID)
)

CREATE TABLE Paypal (
    paypalID int NOT NULL,
    paypalToken varchar(200),
    PRIMARY KEY (paypalID)
)

CREATE TABLE AccplicationAccessLog (
    userAccessed int(8),
    staffAccessed int(8),
    dateTimeAccessed date,
    ipAddress varchar(15),
    FOREIGN KEY (userAccessed) REFERENCES User(userID),
    FOREIGN KEY (staffAccessed) REFERENCES Staff(staffID)
)