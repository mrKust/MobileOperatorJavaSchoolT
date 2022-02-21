use db_ecare;

create table contract(
id int NOT NULL AUTO_INCREMENT,
phone_number varchar(255) not null,
tariff_id int,
client_id int,
primary key (id),
foreign key (client_id) references client(id),
foreign key (tariff_id) references tariff(id)
);