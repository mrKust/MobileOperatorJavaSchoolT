use db_ecare;

create table Options(
id int NOT NULL AUTO_INCREMENT,
options_name varchar(255),
price int unsigned,
cost_to_add int unsigned,
available_for_connect_status boolean,
primary key(id)
);