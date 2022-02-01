create table `order` (
	id bigint not null auto_increment,
	order_status varchar(30) not null,
	order_sub_total decimal(10,2) not null,
	order_total decimal(10,2) not null,
	delivery_fee decimal(10,2) not null,
	received_date datetime not null,
	confirmed_date datetime,
	on_delivery_route_date datetime,
	delivered_date datetime,
	cancelled_date datetime,
	address_zipcode varchar(12) not null,
	address_street_1 varchar(30) not null,
	address_street_2 varchar(10),
	address_district varchar(20) not null,
	address_city_id bigint not null,
	merchant_id bigint not null,
	payment_method_id bigint not null,
	customer_id bigint not null,
	
	constraint fk_order_merchant_id foreign key (merchant_id) references merchant (id),
	constraint fk_order_payment_method_id foreign key (merchant_id) references payment_method(id),
	constraint fk_order_customer_id foreign key (customer_id) references customer (id),
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table order_item (
	id bigint not null auto_increment,
	product_id bigint not null,
	order_id bigint not null,
	unity_price decimal(10,2) not null,
	total_price decimal(10,2) not null,
	quantity smallint(6) not null,
	notes varchar(200),
	
	constraint fk_order_item_product_id foreign key (product_id) references product (id),
	constraint fk_item_order_id foreign key (order_id) references `order` (id),
	
	primary key (id)
) engine=InnoDB default charset=utf8;