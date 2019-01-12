insert into author (id, `firstname`, `secondname`) values (1, 'Roger', 'Zelazny');
insert into genre (id, `genrename`) values (2, 'fantasy');
insert into book (id, `name`, `description`, `id_author`, `id_genre`) values (3, 'Amber Chronicles', 'Bestseller', 1, 2);
insert into comment (id, `comment`, `id_book`) values (4, 'Cool', 3);