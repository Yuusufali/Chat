
CREATE TABLE chat_rooms(
    id bigserial primary key ,
    chat_name varchar(55),
    chat_topic varchar(255) NOT NULL ,
    chat_type smallint NOT NULL ,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    creator_id bigint,
    FOREIGN KEY (creator_id) REFERENCES users(id)
);