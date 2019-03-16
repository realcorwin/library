DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS COMMENT;
CREATE TABLE AUTHOR(ID BIGINT PRIMARY KEY AUTO_INCREMENT, FIRSTNAME VARCHAR(255), SECONDNAME VARCHAR(255) );
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY AUTO_INCREMENT, GENRENAME VARCHAR(255) );
CREATE TABLE BOOK(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), DESCRIPTION VARCHAR(255), ID_AUTHOR INT, ID_GENRE INT,
                  foreign key (ID_AUTHOR) references AUTHOR(ID),
                  foreign key (ID_GENRE) references GENRE(ID));
CREATE TABLE COMMENT(ID INT PRIMARY KEY AUTO_INCREMENT, COMMENT VARCHAR(255), ID_BOOK INT,
                  foreign key (ID_BOOK) references BOOK(ID) ON DELETE CASCADE);