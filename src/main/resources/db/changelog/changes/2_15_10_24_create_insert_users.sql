CREATE TABLE users(
    id bigserial primary key ,
    email varchar(100) NOT NULL ,
    password varchar(255) NOT NULL ,
    full_name varchar(255)
);
INSERT INTO users(email, password, full_name) VALUES
                                                 ('test1@gmail.com','$2a$12$Q4X81o4IapSKUoWcahoNO.905WlHeiH1.SE0Gg3qVPHjUj0c.Rp7W','Test Testov'),
                                                 ('test2@gmail.com','$2a$12$Q4X81o4IapSKUoWcahoNO.905WlHeiH1.SE0Gg3qVPHjUj0c.Rp7W','Test2 Testov');