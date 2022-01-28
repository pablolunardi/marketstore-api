set foreign_key_checks = 0;

delete from state;
delete from city;
delete from merchant;
delete from product;
delete from customer_address;
delete from customer;
delete from order_item;
delete from payment_method;
delete from customer_order;

set foreign_key_checks = 1;

alter table state auto_increment = 1;
alter table city auto_increment = 1;
alter table merchant auto_increment = 1;
alter table product auto_increment = 1;
alter table customer_address auto_increment = 1;
alter table customer auto_increment = 1;
alter table order_item auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table customer_order auto_increment = 1;

insert into state (name) values ('RS');
insert into state (name) values ('SC');
insert into state (name) values ('SP');

insert into city (name, state_id) values ('Passo Fundo', 1);
insert into city (name, state_id) values ('Balneário Camboriú', 2);
insert into city (name, state_id) values ('São Paulo', 3);

insert into merchant (name, active, delivery_fee, address_zipcode, address_street_1, address_district, address_city_id) values ('Comercial Tio Patinhas', true, 5.60, '99074-360', 'Av. Aspirante Jenner, 489', 'Vl Sta Maria', 1);
insert into merchant (name, active, delivery_fee, address_zipcode, address_street_1, address_district, address_city_id) values ('SM Eletrônicos', true, 7.00, '99074-360', 'Av. Aspirante Jenner, 489', 'Vl Sta Maria', 1);

insert into product (name, description, price, enabled, merchant_id) values ('Salgadinho Ruffles', 'Salgadinho de batata bem crocante', 12.50, false, 1);
insert into product (name, description, price, enabled, merchant_id) values ('Refrigerante Coca-cola 2L', 'Refrigerante de cola muito refrescante', 10.50, false, 1);

insert into customer (full_name, email, password) values ('Pablo Augusto Lunardi', 'pablolunardi@gmail.com', '123456');
insert into customer_address (address_zipcode, address_street_1, address_street_2, address_district, customer_address_city_id, customer_address_customer_id) values ('99010-210', 'Rua Enzo 12', 'ap', 'Centro', 1, 1);

insert into payment_method (description) values ('DEBIT');
insert into payment_method (description) values ('CREDIT');
insert into payment_method (description) values ('CASH');

insert into customer_order (order_status, order_sub_total, order_total, delivery_fee, received_date, merchant_id, payment_method_id, customer_id, address_zipcode, address_street_1, address_street_2, address_district, address_city_id) values ('RECEIVED', 12.50, 16.50, 4.00, CURRENT_TIMESTAMP(), 1, 1, 1, '99010-210', 'Rua Enzo 12', 'ap', 'Centro', 1);

insert into order_item (product_id, customer_order_id, unity_price, total_price, quantity, notes) values (1, 1, 12.50, 12.50, 1, 'Seja rápido');