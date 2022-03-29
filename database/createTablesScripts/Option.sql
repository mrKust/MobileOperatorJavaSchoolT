use db_ecare;

create table Options(
id int NOT NULL AUTO_INCREMENT,
options_name varchar(50),
price double,
cost_to_add double,
options_type_id int,
available_for_connect_status boolean,
primary key(id),
foreign key (options_type_id) references db_ecare.options_type(id)
);