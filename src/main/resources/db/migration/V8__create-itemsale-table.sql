
CREATE TABLE rl_item_sale(
    id BIGSERIAL PRIMARY KEY,
    value DECIMAL(10, 0) NOT NULL,
    quantity DECIMAL(10, 0) NOT NULL,
    sale_id BIGINT,
    product_id BIGINT,

   FOREIGN KEY (sale_id) REFERENCES tb_sale(id),
   FOREIGN KEY (product_id) REFERENCES tb_product(id)

);


