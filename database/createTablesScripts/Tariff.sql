use db_ecare;

create table Tariff(
id int NOT NULL AUTO_INCREMENT,
tariff_name varchar(255) not null,
price int unsigned not null,
primary key (id)
);

