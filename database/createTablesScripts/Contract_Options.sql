use db_ecare;

create table contract_options(
contract_id int not null,
options_id int not null,
primary key (contract_id, options_id),
FOREIGN KEY (contract_id) REFERENCES contract(id),
FOREIGN KEY (options_id) REFERENCES options(id)
);