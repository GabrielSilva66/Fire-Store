CREATE TABLE rl_item_order(
    id BIGSERIAL PRIMARY KEY,
    value DECIMAL(10, 0) NOT NULL,
    quantity DECIMAL(10, 0) NOT NULL,
    order_id BIGINT,
    product_id BIGINT,

   FOREIGN KEY (order_id) REFERENCES tb_order(id),
   FOREIGN KEY (product_id) REFERENCES tb_product(id)

);


