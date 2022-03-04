use db_ecare;

create table Client (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(255),
  surname varchar(255),
  date_of_birth varchar(10),
  passport_number char(10),
  address varchar(255),
  phone_number varchar(255),
  email_address varchar(255),
  password_log_in varchar(255),
  status_of_number_block boolean,
  user_role varchar(10),
  user_role_blocked_number varchar(10),
  PRIMARY KEY (id)
);
