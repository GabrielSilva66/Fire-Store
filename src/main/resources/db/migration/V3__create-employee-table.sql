
CREATE TABLE tb_employee(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telephone DECIMAL(13, 0),
    address_id BIGINT,
    is_active BOOLEAN DEFAULT TRUE,

   FOREIGN KEY (address_id) REFERENCES tb_address(id)

);


