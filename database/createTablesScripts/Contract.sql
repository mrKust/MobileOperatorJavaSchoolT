use db_ecare;

create table contract(
id int NOT NULL AUTO_INCREMENT,
phone_number varchar(12),
tariff_id int,
client_id int,
status_of_number_block boolean,
user_role_blocked_number varchar(10),
contract_cost double,
primary key (id),
foreign key (client_id) references client(id),
foreign key (tariff_id) references tariff(id)
);