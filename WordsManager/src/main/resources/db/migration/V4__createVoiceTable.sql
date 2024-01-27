CREATE TABLE voice_files (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    file_name varchar(500) UNIQUE NOT NULL,
    file_data BYTEA NOT NULL
);