create table cooks(
    id serial primary key,
    cookname varchar(255)
);

create table meals(
    id serial primary key,
    mealname varchar(255)
);

create table cooks_meals(
    id serial primary key,
    cooks_id int references cooks(id),
    meals_id int references meals(id)
);