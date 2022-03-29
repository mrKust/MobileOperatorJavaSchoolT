use db_ecare;

create table Tariff(
id int NOT NULL AUTO_INCREMENT,
tariff_name varchar(50),
price double,
available_to_connect_status boolean,
primary key (id)
);

