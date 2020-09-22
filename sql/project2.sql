create table user_roles(
	role_id serial,
	role_name varchar(25) unique not null,
	
	constraint user_roles_pk
	primary key (role_id)
);


create table users (
	user_id serial unique not null,
	username varchar(25) unique not null,
	user_password varchar(25) not null,
	first_name varchar(25),
	last_name varchar(25),
	email varchar(100) unique not null,
	zip_code int,
	role_id int not null,
	
	constraint users_pk
	primary key (user_id),
	
	constraint users_roles_fk
	foreign key (role_id)
	references user_roles
);


create table locations (
	location_id serial,
	city varchar(100),
	state varchar(50),
	country varchar(50),
	location_zip_code int unique not null,
	
	constraint locations_pk
	primary key (location_id)
);

create table user_locations (
	user_id int not null,
	location_id int not null,
	
	constraint user_locations_pk
	primary key (user_id),
	
	constraint user_locations_user_fk
	foreign key (user_id)
	references users,
	
	constraint user_locations_locations_fk
	foreign key (location_id)
	references locations
);

create table home_locations (
	user_id int unique not null,
	location_id int not null,
	
	constraint home_locations_pk
	primary key (user_id),
	
	constraint home_users_fk
	foreign key (user_id)
	references users,
	
	constraint home_locations_fk
	foreign key (location_id)
	references locations
);

commit;

insert into user_roles (role_name) values ('User');
insert into user_roles (role_name) values ('Admin');