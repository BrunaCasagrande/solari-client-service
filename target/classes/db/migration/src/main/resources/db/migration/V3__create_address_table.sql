CREATE TABLE addresses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  number VARCHAR(50) NOT NULL,
  city VARCHAR(100) NOT NULL,
  state VARCHAR(100) NOT NULL,
  zip_code VARCHAR(20) NOT NULL
);

ALTER TABLE solari_client_db
  ADD COLUMN address_id INT,
  ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES addresses(id);
