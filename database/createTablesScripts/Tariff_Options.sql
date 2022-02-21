use db_ecare;

create table tariff_options(
tariff_id int not null,
options_id int not null,
primary key (tariff_id, options_id),
FOREIGN KEY (tariff_id) REFERENCES tariff(id),
FOREIGN KEY (options_id) REFERENCES options(id)
);