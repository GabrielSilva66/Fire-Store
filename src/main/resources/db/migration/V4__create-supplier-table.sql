
CREATE TABLE tb_supplier(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telephone DECIMAL(13, 0),
    cnpj VARCHAR(14),
    address_id BIGINT,
    is_active BOOLEAN DEFAULT TRUE,

   FOREIGN KEY (address_id) REFERENCES tb_address(id)

);


