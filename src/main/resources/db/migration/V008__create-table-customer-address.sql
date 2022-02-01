create table customer_address (
	id bigint not null auto_increment,
	address_zipcode varchar(12) not null,
	address_street_1 varchar(30) not null,
	address_street_2 varchar(10),
	address_district varchar(20) not null,
	customer_address_city_id bigint not null,
	customer_address_customer_id bigint not null,
	
	
	constraint fk_customer_address_city_id foreign key (customer_address_city_id) references city (id),
	constraint fk_customer_address_customer_id foreign key (customer_address_customer_id) references customer (id),
	
	primary key (id)
) engine=InnoDB default charset=utf8;