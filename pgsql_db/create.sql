create table roles(
	id serial primary key,
	rolename text
);

create table users(
	id serial primary key,
	username text,
	roles_id int references roles(id)
);

create table rules(
	id serial primary key,
	description text
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table status(
	id serial primary key,
	description text
);

create table categories(
	id serial primary key,
	description text
);

create table items(
	id serial primary key,
	description text,
	user_id int references users(id),
	categories_id int references categories(id),
	status_id int references status(id)
);

create table comments(
	id serial primary key,
	description text,
	items_id int references items(id)
);

create table attachs(
	id serial primary key,
	description text,
	items_id int references items(id)
);
