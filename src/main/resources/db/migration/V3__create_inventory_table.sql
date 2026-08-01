CREATE TABLE tbl_inventory (

                               id BIGSERIAL PRIMARY KEY,

                               product_id BIGINT NOT NULL UNIQUE,

                               quantity INTEGER NOT NULL DEFAULT 0,

                               reserved_quantity INTEGER NOT NULL DEFAULT 0,

                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               updated_at TIMESTAMP,

                               CONSTRAINT fk_inventory_product
                                   FOREIGN KEY (product_id)
                                       REFERENCES tbl_products(id)
);

CREATE INDEX idx_inventory_product
    ON tbl_inventory(product_id);