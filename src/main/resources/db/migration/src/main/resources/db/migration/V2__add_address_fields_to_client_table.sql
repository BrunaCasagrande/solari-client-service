ALTER TABLE solari_client_db
  ADD COLUMN address_street VARCHAR(255) NOT NULL,
  ADD COLUMN address_number VARCHAR(50) NOT NULL,
  ADD COLUMN address_city VARCHAR(100) NOT NULL,
  ADD COLUMN address_state VARCHAR(100) NOT NULL,
  ADD COLUMN address_zip_code VARCHAR(20) NOT NULL;
