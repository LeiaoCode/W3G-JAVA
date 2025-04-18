-- 订单主表
CREATE TABLE `orders` (
                          `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                          `order_id` VARCHAR(100) NOT NULL UNIQUE,
                          `email` VARCHAR(255) NOT NULL,
                          `total` DECIMAL(10,2) NOT NULL,
                          `date` DATETIME NOT NULL,
                          INDEX (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单明细表
CREATE TABLE `order_items` (
                               `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                               `order_id` BIGINT NOT NULL,
                               `name` VARCHAR(255) NOT NULL,
                               `price` DECIMAL(10,2) NOT NULL,
                               `quantity` INT NOT NULL,
                               FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
