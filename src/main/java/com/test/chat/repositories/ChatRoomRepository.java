package com.test.chat.repositories;

import com.test.chat.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findAllById(Long id);
    ChatRoom findAllByChatTopicEquals(String chatTopic);
    List<ChatRoom> findAllByCreator_Id(Long creatorId);
    @Query("SELECT cr FROM ChatRoom cr JOIN cr.users u WHERE u.id = :userId")
    List<ChatRoom> findAllByUser(@Param("userId") Long userId);
}
