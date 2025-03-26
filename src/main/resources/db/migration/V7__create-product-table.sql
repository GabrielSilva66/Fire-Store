
CREATE TABLE tb_product(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code_bar VARCHAR(100) NOT NULL,
    unit_measure VARCHAR(50) NOT NULL,
    cost_price DECIMAL(10, 0) NOT NULL CHECK (cost_price >= 0),
    sale_price DECIMAL(10, 0) NOT NULL  CHECK (sale_price >= 0),
    stock DECIMAL(10, 0) NOT NULL  CHECK (stock >= 0)

);


