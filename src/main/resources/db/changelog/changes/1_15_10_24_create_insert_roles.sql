CREATE TABLE permissions (
  id bigserial primary key ,
  role varchar(50) NOT NULL
);

INSERT INTO permissions(role) VALUES ('ROLE_USER'),
                                     ('ROLE_ADMIN');