/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Eric, Sheng, Eva, Ricky, Samuel
 * Created: 12/05/2020
 */

CREATE TABLE Users (
    userID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    userEmail varchar(50),
    userPassword varchar(50),
    PRIMARY KEY (userID)
);

CREATE TABLE Staff (
    staffID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    staffName varchar(50),
    staffPhone varchar(10),
    userID int,
    staffRole varchar(20),
    PRIMARY KEY (staffID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
);
CREATE TABLE Customer (
    custID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    custName varchar(50),
    custPhone varchar(10),
    userID int,
    active boolean,
    PRIMARY KEY (custID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
);


CREATE TABLE Product (
    productID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    productName varchar(100),
    productPrice double,
    productDescription varchar(1000),
    productStock int,
   /* productImageLink varchar(1000),
    productImageIconLink varchar(1000),*/
    productType varchar(1000),
    productBrand varchar(1000),
    PRIMARY KEY (productID)
);

CREATE TABLE Shipping(
    shippingID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    shippingStreetNumber int,
    shippingStreetName varchar(40),
    shippingPostcode int,
    shippingState varchar(3),
    shippingCountry varchar(30),
    shippingApartmentNo int,
    PRIMARY KEY (shippingID)
);

CREATE TABLE Orders (
    orderID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    orderDate date,
    custID int,
    shippingID int,
    PRIMARY KEY (orderID),
    FOREIGN KEY (custID) REFERENCES Customer(custID) ON DELETE CASCADE,
    FOREIGN KEY (shippingID) REFERENCES Shipping(shippingID) ON DELETE CASCADE
);

CREATE TABLE OrderLine (
    productID int NOT NULL,
    orderID int NOT NULL,
    quantity int,
    FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE,
    FOREIGN KEY (orderID) REFERENCES Orders(orderID) ON DELETE CASCADE
);

CREATE TABLE Payment (
    paymentID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    paymentCreditCard boolean,
    paymentConfirm boolean,
    paymentPaypal boolean,
    PRIMARY KEY (paymentID)
);

CREATE TABLE CreditCard (
    cardID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    creditName varchar(50),
    creditNo bigint,
    creditSecurityNo int,
    creditExpiryDate Date,
    paymentID int,
    PRIMARY KEY (cardID),
    FOREIGN KEY (paymentID) references Payment(paymentID) ON DELETE CASCADE
);

CREATE TABLE Paypal (
    paypalID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    paypalToken varchar(200),
    paymentID int,
    PRIMARY KEY (paypalID),
    FOREIGN KEY (paymentID) references Payment(paymentID) ON DELETE CASCADE
);

CREATE TABLE AppAccessLog (
    accessID int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    userID int,
    dateAccessed varchar(10),
    timeAccessed varchar(8),
    userAction varchar(6),
    PRIMARY KEY (accessID),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE
);

CREATE TABLE PaymentOrder (
    poDate date,
    paymentID int,
    orderID int,
    PRIMARY KEY (paymentID, orderID),
    FOREIGN KEY (paymentID) REFERENCES Payment(paymentID) ON DELETE CASCADE,
    FOREIGN KEY (orderID) REFERENCES Orders(orderID) ON DELETE CASCADE
);