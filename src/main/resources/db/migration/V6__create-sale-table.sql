
CREATE TABLE tb_sale(
    id BIGSERIAL PRIMARY KEY,
    observation VARCHAR(255) NOT NULL,
    total_value DECIMAL(10, 0) NOT NULL CHECK(total_value >= 0),
    total_quantity DECIMAL(10, 0) NOT NULL CHECK(total_quantity >= 0),
    date DATE NOT NULL,
    employee_id BIGINT,
    supplier_id BIGINT,

   FOREIGN KEY (employee_id) REFERENCES tb_employee(id),
   FOREIGN KEY (supplier_id) REFERENCES tb_supplier(id)

);


