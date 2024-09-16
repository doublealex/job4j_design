create table authors(
    id serial primary key,
    authorname varchar(255)
);

create table paintings(
    id serial primary key,
    paintingname varchar(255),
    authors_id int references authors(id)
);