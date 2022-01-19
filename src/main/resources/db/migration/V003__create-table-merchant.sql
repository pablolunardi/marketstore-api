create table merchant (
	id bigint not null auto_increment,
	name varchar(30) not null,
	active tinyint(1) not null,
	address_zipcode varchar(12) not null,
	address_street_1 varchar(30) not null,
	address_street_2 varchar(10),
	address_district varchar(20) not null,
	address_city_id bigint not null,
	
	constraint fk_merchant_city_id foreign key (address_city_id) references city (id),
	
	primary key (id)
) engine=InnoDB default charset=utf8;