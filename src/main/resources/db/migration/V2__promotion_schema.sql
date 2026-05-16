CREATE TABLE tbl_promotions (
                         id BIGSERIAL PRIMARY KEY,
                         productId BIGINT REFERENCES tbl_products(id),
                         type TEXT,
                         value NUMERIC(10,2) NOT NULL,
                         startsAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         endsAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         active BOOLEAN NOT NULL,
                         priority INTEGER

);