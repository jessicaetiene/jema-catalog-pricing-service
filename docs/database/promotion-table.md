# Product

### Name:
- tbl_promotions

### Columns and types:


| Field      | Type          | Constraints |
|------------|---------------|-------------|
| id         | BIGSERIAL     | Primary key |
| productId  | BIGSERIAl     | Not null    |
| type       | TEXT          | Not null    |
| value      | numeric(10,2) | Not null    |
| startsAt      | TIMESTAMP     | Not null    |
| endsAt      | TIMESTAMP     | Not null    |
| active     | boolean       | Not null    |
| priority | INTEGER       | Not null    |
