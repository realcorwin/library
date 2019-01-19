insert into author (id, firstname, secondname) values (101, 'firstname101', 'secondname101');
insert into author (id, firstname, secondname) values (99, 'firstname99', 'secondname99');
insert into genre (id, genrename) values (22, 'genrename22');
insert into genre (id, genrename) values (23, 'genrename23');
insert into book (id, name, description, id_author, id_genre) values (1003, 'name1003', 'description1003', 101, 22);
insert into book (id, name, description, id_author, id_genre) values (1004, 'name1004', 'description1004', 101, 22);
insert into comment (id, comment, id_book) values (501, 'Cool', 1003);
insert into comment (id, comment, id_book) values (502, 'Good', 1004);
insert into comment (id, comment, id_book) values (503, 'Fantastic', 1004);