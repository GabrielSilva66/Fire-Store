CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE state(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL,
    acronym varchar(5) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    create_in timestamp DEFAULT NOW(),
    update_in timestamp DEFAULT NOW()

);


