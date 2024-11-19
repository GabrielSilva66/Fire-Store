CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE state(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(100) NOT NULL,
    acronym varchar(5) NOT NULL,
    create_in timestamp,
    update_in timestamp
);