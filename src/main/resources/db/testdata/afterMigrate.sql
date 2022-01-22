set foreign_key_checks = 0;

delete from state;
delete from city;
delete from merchant;
delete from product;

set foreign_key_checks = 1;

alter table state auto_increment = 1;
alter table city auto_increment = 1;
alter table merchant auto_increment = 1;
alter table product auto_increment = 1;

insert into state (name) values ('RS');
insert into state (name) values ('SC');

insert into city (name, state_id) values ('Passo Fundo', 1);
insert into city (name, state_id) values ('Balneário Camboriú', 2);

insert into merchant (name, active, address_zipcode, address_street_1, address_district, address_city_id) values ('Comercial Tio Patinhas', true, '99074-360', 'Av. Aspirante Jenner, 489', 'Vl Sta Maria', 1);
insert into merchant (name, active, address_zipcode, address_street_1, address_district, address_city_id) values ('SM Eletrônicos', true, '99074-360', 'Av. Aspirante Jenner, 489', 'Vl Sta Maria', 1);

insert into product (name, description, price, enabled, merchant_id) values ('Salgadinho Ruffles', 'Salgadinho de batata bem crocante', 12.50, false, 1);
insert into product (name, description, price, enabled, merchant_id) values ('Refrigerante Coca-cola 2L', 'Refrigerante de cola muito refrescante', 10.50, false, 1);