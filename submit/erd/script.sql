CREATE TABLE `users` (
                         `user_id`	VARCHAR(50)	NOT NULL,
                         `user_name`	VARCHAR(50)	NOT NULL,
                         `user_password`	VARCHAR(200)	NOT NULL,
                         `user_birth`	VARCHAR(8)	NOT NULL,
                         `user_auth`	VARCHAR(10)	NOT NULL,
                         `user_point`	int	NOT NULL	DEFAULT 1000000,
                         `created_at`	DATETIME	NOT NULL,
                         `latest_login_at`	DATETIME	NULL	DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE `products` (
                            `product_id`	VARCHAR(50)	NOT NULL,
                            `product_name`	VARCHAR(50)	NOT NULL,
                            `product_quantity`	int	NOT NULL,
                            `product_image`	VARCHAR(600)	NULL,
                            `product_detail_image`	VARCHAR(600)	NOT NULL,
                            `product_original_price`	int	NOT NULL,
                            `product_sale_price`	int	NOT NULL,
                            `product_content`	VARCHAR(1000)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';

CREATE TABLE `orders` (
                          `order_id`	VARCHAR(50)	NOT NULL,
                          `order_total_price`	double	NOT NULL,
                          `order_date`	TIMESTAMP	NULL,
                          `order_status`	VARCHAR(20)	NULL,
                          `order_refund`	VARCHAR(200)	NULL,
                          `order_name`	VARCHAR(20)	NOT NULL,
                          `order_zipcode`	VARCHAR(6)	NOT NULL,
                          `order_address`	VARCHAR(300)	NOT NULL,
                          `order_detail_address`	VARCHAR(100)	NOT NULL,
                          `order_phone_number`	VARCHAR(20)	NOT NULL,
                          `order_request`	VARCHAR(100)	NULL,
                          `order_count`	int	NULL,
                          `user_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';

CREATE TABLE `category` (
                            `category_id`	VARCHAR(50)	NOT NULL,
                            `category_name`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';

CREATE TABLE `orders_detail` (
                                 `order_detail_id`	VARCHAR(50)	NOT NULL,
                                 `order_detail_price`	double	NULL,
                                 `order_detail_count`	int	NULL,
                                 `product_id`	VARCHAR(50)	NOT NULL,
                                 `order_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상세';

CREATE TABLE `address` (
                           `address_id`	VARCHAR(50)	NOT NULL,
                           `address`	VARCHAR(300)	NOT NULL,
                           `detail_address`	VARCHAR(100)	NOT NULL,
                           `zipcode`	VARCHAR(6)	NOT NULL,
                           `person_name`	VARCHAR(20)	NOT NULL,
                           `person_phone_number`	VARCHAR(20)	NOT NULL,
                           `user_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주소';

CREATE TABLE `carts` (
                         `cart_id`	VARCHAR(50)	NOT NULL,
                         `cart_count`	int	NULL,
                         `product_id`	VARCHAR(50)	NOT NULL,
                         `user_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';

CREATE TABLE `product_category` (
                                    `product_id`	VARCHAR(50)	NOT NULL,
                                    `category_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품카테고리 관계';

CREATE TABLE `point_log` (
                             `point_log_id`	VARCHAR(50)	NOT NULL,
                             `point_date`	TIMESTAMP NOT NULL,
                             `point`	int	NOT NULL,
                             `user_id`	VARCHAR(50)	NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포인트 내역';

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
                                                           `user_id`
    );

ALTER TABLE `products` ADD CONSTRAINT `PK_PRODUCTS` PRIMARY KEY (
                                                                 `product_id`
    );

ALTER TABLE `orders` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (
                                                             `order_id`
    );

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
                                                                 `category_id`
    );

ALTER TABLE `orders_detail` ADD CONSTRAINT `PK_ORDERS_DETAIL` PRIMARY KEY (
                                                                           `order_detail_id`
    );

ALTER TABLE `address` ADD CONSTRAINT `PK_ADDRESS` PRIMARY KEY (
                                                               `address_id`
    );

ALTER TABLE `carts` ADD CONSTRAINT `PK_CARTS` PRIMARY KEY (
                                                           `cart_id`
    );

ALTER TABLE `point_log` ADD CONSTRAINT `PK_POINT_LOG` PRIMARY KEY (
                                                                   `point_log_id`
    );

ALTER TABLE `orders` ADD CONSTRAINT `FK_users_TO_orders_1` FOREIGN KEY (
                                                                        `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );

ALTER TABLE `orders_detail` ADD CONSTRAINT `FK_products_TO_orders_detail_1` FOREIGN KEY (
                                                                                         `product_id`
    )
    REFERENCES `products` (
                           `product_id`
        );

ALTER TABLE `orders_detail` ADD CONSTRAINT `FK_orders_TO_orders_detail_1` FOREIGN KEY (
                                                                                       `order_id`
    )
    REFERENCES `orders` (
                         `order_id`
        );

ALTER TABLE `address` ADD CONSTRAINT `FK_users_TO_address_1` FOREIGN KEY (
                                                                          `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );

ALTER TABLE `carts` ADD CONSTRAINT `FK_products_TO_carts_1` FOREIGN KEY (
                                                                         `product_id`
    )
    REFERENCES `products` (
                           `product_id`
        );

ALTER TABLE `carts` ADD CONSTRAINT `FK_users_TO_carts_1` FOREIGN KEY (
                                                                      `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );

ALTER TABLE `product_category` ADD CONSTRAINT `FK_products_TO_Untitled_1` FOREIGN KEY (
                                                                                       `product_id`
    )
    REFERENCES `products` (
                           `product_id`
        );


ALTER TABLE `product_category` ADD CONSTRAINT `FK_category_TO_Untitled_1` FOREIGN KEY (
                                                                                       `category_id`
    )
    REFERENCES `category` (
                           `category_id`
        );

ALTER TABLE `point_log` ADD CONSTRAINT `FK_users_TO_point_log_1` FOREIGN KEY (
                                                                              `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );