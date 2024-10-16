CREATE TABLE chat_rooms_users (
    chat_room_id bigint NOT NULL ,
    users_id bigint NOT NULL ,
    FOREIGN KEY (chat_room_id) REFERENCES chat_rooms(id),
    FOREIGN KEY (users_id) REFERENCES users(id)
);