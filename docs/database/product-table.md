# Product

### Name:
- tbl_products

### Columns and types:


| Field       | Type          | Constraints |
|-------------|---------------|-------------|
| id          | BIGSERIAL     | Primary key |
| name        | varchar(200)  | Not null    |
| description | TEXT          | Not null    |
| price       | numeric(10,2) | Not null    |
| active      | boolean       | Not null    |
| created_at      | TIMESTAMP       | Not null    |
| updated_at      | TIMESTAMP       | Null        |
