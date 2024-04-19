CREATE TABLE IF NOT EXISTS pet (
  pet_id int AUTO_INCREMENT PRIMARY KEY,
  uuid varchar(36) NOT NULL,
  pet_nm_name VARCHAR(100) NULL,
  bree_uuid varchar(36) NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL
);