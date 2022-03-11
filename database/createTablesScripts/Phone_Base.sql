use db_ecare;

create table numbers_base (
id int NOT NULL AUTO_INCREMENT,
  phone_number varchar(255),
  status_of_number_available_to_connect boolean,
  PRIMARY KEY (id)
  );