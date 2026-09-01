CREATE TABLE IF NOT EXISTS product_categories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS veterinarian_categories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS clients (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    cpf VARCHAR(255) NOT NULL UNIQUE,
    street VARCHAR(255), city VARCHAR(255), state VARCHAR(255),
    zip_code VARCHAR(255), complement VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tb_users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS veterinarian (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    crmv VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    category_id UUID NOT NULL REFERENCES veterinarian_categories(id)
);

CREATE TABLE IF NOT EXISTS animals (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    species VARCHAR(255) NOT NULL,
    breed VARCHAR(255),
    birth_date DATE,
    client_id UUID REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    version BIGINT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price NUMERIC(19,2) NOT NULL CHECK (price > 0),
    quantity_in_stock INTEGER NOT NULL DEFAULT 0 CHECK (quantity_in_stock >= 0),
    category_id UUID NOT NULL REFERENCES product_categories(id)
);

CREATE TABLE IF NOT EXISTS sales (
    id UUID PRIMARY KEY,
    version BIGINT,
    sale_date TIMESTAMP NOT NULL,
    payment_type VARCHAR(30),
    total_value NUMERIC(19,2),
    status VARCHAR(30) NOT NULL,
    notes VARCHAR(255),
    client_id UUID NOT NULL REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS product_sales (
    id UUID PRIMARY KEY,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(19,2) NOT NULL,
    product_id UUID NOT NULL REFERENCES products(id),
    sale_id UUID NOT NULL REFERENCES sales(id),
    UNIQUE (sale_id, product_id)
);

CREATE TABLE IF NOT EXISTS monetary_types (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS financial (
    id UUID PRIMARY KEY,
    version BIGINT,
    description VARCHAR(255) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    balance NUMERIC(19,2) NOT NULL,
    date_created DATE NOT NULL,
    due_date DATE,
    payment_date DATE,
    is_paid BOOLEAN NOT NULL,
    installment INTEGER NOT NULL,
    notes VARCHAR(255),
    client_id UUID NOT NULL REFERENCES clients(id),
    sale_id UUID REFERENCES sales(id)
);

CREATE TABLE IF NOT EXISTS financial_payments (
    id UUID PRIMARY KEY,
    paid_amount NUMERIC(19,2) NOT NULL,
    payment_date DATE NOT NULL,
    notes VARCHAR(255),
    monetary_type UUID REFERENCES monetary_types(id),
    financial_id UUID NOT NULL REFERENCES financial(id)
);

CREATE TABLE IF NOT EXISTS stock_movements (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL REFERENCES products(id),
    type VARCHAR(30) NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    date_movement TIMESTAMP NOT NULL,
    description VARCHAR(255),
    price NUMERIC(19,2) NOT NULL,
    sale_id UUID REFERENCES sales(id),
    invoice VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medical_appointment (
    id UUID PRIMARY KEY,
    version BIGINT,
    notes VARCHAR(255),
    diagnosis VARCHAR(500),
    treatment VARCHAR(700),
    client_id UUID NOT NULL REFERENCES clients(id),
    animal_id UUID NOT NULL REFERENCES animals(id),
    veterinarian_id UUID NOT NULL REFERENCES veterinarian(id),
    appointment_start_time TIMESTAMP NOT NULL,
    appointment_end_time TIMESTAMP NOT NULL,
    duration_minutes INTEGER NOT NULL CHECK (duration_minutes > 0),
    appointment_status VARCHAR(30) NOT NULL
);

ALTER TABLE tb_users ADD COLUMN IF NOT EXISTS role VARCHAR(30) NOT NULL DEFAULT 'USER';
ALTER TABLE products ADD COLUMN IF NOT EXISTS version BIGINT;
ALTER TABLE sales ADD COLUMN IF NOT EXISTS version BIGINT;
ALTER TABLE financial ADD COLUMN IF NOT EXISTS version BIGINT;
ALTER TABLE medical_appointment ADD COLUMN IF NOT EXISTS version BIGINT;
ALTER TABLE products ADD CONSTRAINT products_stock_non_negative CHECK (quantity_in_stock >= 0);

CREATE INDEX IF NOT EXISTS idx_appointments_veterinarian_time
    ON medical_appointment (veterinarian_id, appointment_start_time, appointment_end_time);
CREATE INDEX IF NOT EXISTS idx_appointments_client_time
    ON medical_appointment (client_id, appointment_start_time, appointment_end_time);
