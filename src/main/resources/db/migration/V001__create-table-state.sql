create table state (
	id bigint not null auto_increment,
	name varchar(2) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;