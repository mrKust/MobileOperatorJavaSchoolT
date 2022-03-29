use db_ecare;

create table numbers_base (
id int NOT NULL AUTO_INCREMENT,
  phone_number varchar(11),
  status_of_number_available_to_connect boolean,
  PRIMARY KEY (id)
  );