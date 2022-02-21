use db_ecare;

create table Options(
id int NOT NULL AUTO_INCREMENT,
options_name varchar(255) not null,
price int unsigned not null,
cost_to_add int unsigned,
primary key(id)
);