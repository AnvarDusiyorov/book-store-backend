create table if not exists users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null default true,
	first_name varchar(30),
	second_name varchar(30),
	email varchar(30),
	city varchar(70),
	country varchar(70)
);
create table if not exists authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users
	        foreign key(username) references users(username) on delete cascade
);

create unique index ix_auth_username on authorities (username,authority);