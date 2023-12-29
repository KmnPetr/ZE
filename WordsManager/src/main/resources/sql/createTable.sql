CREATE TABLE Person(
                       id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                       email varchar(100) UNIQUE NOT NULL,
                       password varchar NOT NULL,
                       short_name varchar(100),
                       role varchar(100) NOT NULL,
                       created_at timestamp
);

CREATE TABLE Word(
                     id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
                     foreign_word varchar(400)NOT NULL,
                     transcription varchar(100),
                     translation varchar(400)NOT NULL,
                     description varchar(400),
                     groupWrd varchar(50),
                     has_voise varchar(50),
                     has_image varchar(50),
                     sorting_value integer default 0
);
DROP TABLE Word;
-- ALTER TABLE Word ADD COLUMN sorting_value integer default 0;
-- ALTER TABLE Word DROP COLUMN sorting_value;
-- //////////////эти два вызова идут вмест/////////////////
CREATE TABLE properties(
                           key varchar(100)UNIQUE NOT NULL,
                           value varchar(200)
);
INSERT INTO properties(key) VALUES ('update_at');
-- ////////////////////////////////////////////////////////