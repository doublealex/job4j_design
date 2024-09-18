insert into roles(rolename) values ('student'), ('teacher');
insert into users(username, roles_id) values ('Greg', 1);
insert into rules(description) values ('зачет'), ('незачет');
insert into roles_rules(roles_id, rules_id) values (2, 1), (2, 2);
insert into status(description) values ('на рассмотрении');
insert into categories(description) values ('важная заявка');
insert into items(description, user_id, categories_id, status_id) values ('заявка №1', 1, 1, 1);
insert into comments(description, items_id) values ('комментарий к заявке', 1);
insert into attachs(description, items_id) values ('вложенный файл', 1);