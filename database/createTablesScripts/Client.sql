use db_ecare;

create table Client (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(50),
  surname varchar(50),
  date_of_birth varchar(10),
  passport_number varchar(10),
  address varchar(100),
  email_address varchar(50),
  password_log_in varchar(200),
  user_role varchar(10),
  money_balance double,
  PRIMARY KEY (id)
);
