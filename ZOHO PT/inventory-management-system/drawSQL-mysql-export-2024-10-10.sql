CREATE TABLE `Categories`(
    `categoryId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `Items`(
    `itemId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL,
    `quantity` FLOAT(53) NOT NULL,
    `categoryId` BIGINT NOT NULL,
    `costPrice` FLOAT(53) NOT NULL,
    `sellingPrice` FLOAT(53) NOT NULL,
    `expirationDays` INT NOT NULL
);

CREATE TABLE `Customers`(
    `customerId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` BIGINT NOT NULL,
    `phNumber` BIGINT NOT NULL
);

CREATE TABLE `Sales`(
    `salesId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    `customerId` BIGINT UNSIGNED NOT NULL,
    `totalAmount` FLOAT(53) NOT NULL,
    FOREIGN KEY(`customerId`) references Customers(`customerId`)
);

CREATE TABLE `SalesItems`(
    `salesId` BIGINT UNSIGNED NOT NULL,
    `itemId` BIGINT UNSIGNED NOT NULL,
    `quantity` FLOAT(53) NOT NULL,
    `amount` FLOAT(53) NOT NULL,
    PRIMARY KEY(`itemId`,`salesId`),
    foreign key(`itemId`) references Items(`itemId`),
    foreign key(`salesId`) references Sales(`salesId`)
);


CREATE TABLE `Suppliers`(
    `supplierId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL,
    `phNumber` VARCHAR(10) NOT NULL
);

CREATE TABLE `Purchases`(
    `purchaseId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `supplierId` BIGINT UNSIGNED NOT NULL,
    `date` DATE NOT NULL,
    `totalAmount` FLOAT(53) NOT NULL,
    FOREIGN KEY (`supplierId`) references Suppliers(`supplierId`)
);

CREATE TABLE `PurchaseItems`(
    `purchaseId` BIGINT UNSIGNED,
    `itemId` BIGINT UNSIGNED NOT NULL,
    `quatity` FLOAT(53) NOT NULL,
    `amount` FLOAT(53) NOT NULL,
    PRIMARY KEY(`itemId`,`purchaseId`),
    foreign key(`itemId`) references Items(`itemId`),
    foreign key(`purchaseId`) references Purchases(`purchaseId`)
);


CREATE TABLE `AdjustedGoods`(
    `purchaseId` BIGINT unsigned NOT NULL,
    `itemId` BIGINT UNSIGNED NOT NULL,
    `reason` BIGINT NULL,
    `dateRemoved` DATE NOT NULL,
    `quantity` FLOAT(53) NOT NULL,
    PRIMARY KEY(`purchaseId`,`itemId`),
    foreign key(`purchaseId`) references Purchases(`purchaseId`),
    foreign key(`itemId`) references Items(`itemId`)
);

CREATE TABLE `Warehouses`(
    `warehouseId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL,
    `location` VARCHAR(100) NOT NULL,
    `capacity` INT NOT NULL
);

CREATE TABLE `WarehouseProducts`(
    `warehouseId` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `itemId` BIGINT UNSIGNED NOT NULL,
    `quantity` FLOAT(53),
    PRIMARY KEY(`itemId`,`warehouseId`),
    foreign key(`itemId`) references Items(`itemId`),
    foreign key(`warehouseId`) references Warehouses(`warehouseId`)
);
