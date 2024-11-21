
CREATE TABLE rl_item_intake(
    id BIGSERIAL PRIMARY KEY,
    value DECIMAL(10, 0) NOT NULL,
    quantity DECIMAL(10, 0) NOT NULL,
    intake_id BIGINT,
    product_id BIGINT,

   FOREIGN KEY (intake_id) REFERENCES tb_intake(id),
   FOREIGN KEY (product_id) REFERENCES tb_product(id)

);


