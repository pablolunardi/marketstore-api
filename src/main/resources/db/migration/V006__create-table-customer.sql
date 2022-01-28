create table customer (
	id bigint not null auto_increment,
	full_name varchar(40) not null,
	email varchar(40) not null,
	password varchar(12) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;