create table product (
	id bigint not null auto_increment,
	name varchar(40) not null,
	description varchar(80)  not null,
	price decimal(10,2) not null,
	enabled tinyint(1) not null,
	merchant_id bigint not null,
	
	constraint fk_product_merchant_id foreign key (merchant_id) references merchant (id),
	
	primary key (id)
) engine=InnoDB default charset=utf8;