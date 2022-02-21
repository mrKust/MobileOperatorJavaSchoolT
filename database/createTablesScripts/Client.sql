use db_ecare;

create table Client (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(255) not null,
  surname varchar(255) not null,
  date_of_birth date,
  passport_number char(10),
  address varchar(255),
  phone_number varchar(255) not null,
  email_address varchar(255) not null,
  password_log_in varchar(255) not null,
  PRIMARY KEY (id)
);
