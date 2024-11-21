
CREATE TABLE tb_address(
    id BIGSERIAL PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    number DECIMAL(10,0) NOT NULL CHECK (number > 0)

);


