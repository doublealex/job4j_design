create table users(
    id serial primary key,
    username varchar(255)
);

create table people(
    id serial primary key,
    peoplename varchar(255)
);

create table users_people(
    id serial primary key,
    passport_id int references users(id) unique,
    people_id int references people(id) unique
);