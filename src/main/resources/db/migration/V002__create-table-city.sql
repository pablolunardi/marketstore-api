create table city (
	id bigint not null auto_increment,
	name varchar(20) not null,
	state_id bigint not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;