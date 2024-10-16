CREATE TABLE messages (
    id bigserial primary key ,
    message_date timestamp default current_timestamp NOT NULL ,
    message_text varchar(255) NOT NULL ,
    chat_room_id bigint NOT NULL ,
    sender_id bigint NOT NULL ,
    FOREIGN KEY (chat_room_id) REFERENCES chat_rooms(id),
    FOREIGN KEY (sender_id) REFERENCES users(id)
);