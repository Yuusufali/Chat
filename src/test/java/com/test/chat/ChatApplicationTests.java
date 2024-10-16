package com.test.chat;


import com.test.chat.mappers.UsersMapper;
import com.test.chat.models.*;
import com.test.chat.modelsDto.ChatRoomDto;
import com.test.chat.modelsDto.MessageDto;
import com.test.chat.modelsDto.UsersDto;
import com.test.chat.repositories.ChatRoomRepository;
import com.test.chat.repositories.MessageRepository;
import com.test.chat.repositories.PermissionRepository;
import com.test.chat.repositories.UsersRepository;
import com.test.chat.services.ChatRoomService;
import com.test.chat.services.MessageService;
import com.test.chat.services.MyUserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class ChatApplicationTests {

	@Autowired
	private MyUserService userService;
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private MessageService messageService;

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	@Autowired
	private MessageRepository messageRepository;



	@BeforeEach
	public void setUp(){
		Permission role = permissionRepository.findAllByRole("ROLE_USER");
		Users users = Users.builder()
				.email("test1@gmail.com")
				.password("$2a$12$VZVPVFM4.Q08.YtOzIjpO.EU84vMD2pDqOsCKKd2BLZkYY1Y3QxX2")
				.fullName("Test")
				.roles(List.of(role))
				.build();
		usersRepository.save(users);
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(users,null,users.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}
	@Test
	void contextLoads() {
		usersRepository.deleteAll();
	}

	@Test
	public void getAllUsers(){
		List<UsersDto> usersList = userService.getAllUsers();
		Assertions.assertNotNull(usersList);
		usersRepository.deleteAll();
	}

	@Test
	public void searchUser(){
		UsersDto users = userService.searchUser("test1@gmail.com");
		Assertions.assertNotNull(users);
		usersRepository.deleteAll();
	}

	@Test
	public void createGroupChatRoom(){
		Users users = usersRepository.findAll().get(0);
		ChatRoomDto chatRoomDto = chatRoomService.createGroupChatRoom(ChatRoomRequest.builder()
						.chatName("Developers")
						.creator(users)
				.build());
		Assertions.assertNotNull(chatRoomDto);
		Assertions.assertNotNull(chatRoomDto.getChatTopic());
		Assertions.assertEquals(chatRoomDto.getChatType(), ChatRoomDto.ChatType.GROUP);
		Assertions.assertNotNull(chatRoomDto.getUsers());
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void createPersonalChat(){
		ChatRoom chatRoom = chatRoomService.createPersonalChat("test1@gmail.com");
		Assertions.assertNotNull(chatRoom);
		Assertions.assertNotNull(chatRoom.getChatTopic());
		Assertions.assertEquals(chatRoom.getChatType(), ChatRoom.ChatType.PERSONAL);
		Assertions.assertNotNull(chatRoom.getUsers());
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void getChatRoom(){
		ChatRoomDto chatRoomDto = chatRoomService.getChatRoom("test1@gmail.com");
		Assertions.assertNotNull(chatRoomDto);
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void getUsersChat(){
		Users  users = usersRepository.findAllByEmail("test1@gmail.com");
		List<ChatRoomDto> chatRoomDtoList = chatRoomService.getChatRoomsByUser(users.getId());
		Assertions.assertNotNull(chatRoomDtoList);
		usersRepository.deleteAll();
	}

	@Test
	public void updateGroupChatRoom(){
		ChatRoom chatRoom = chatRoomRepository.save(
				ChatRoom.builder()
						.chatTopic("")
						.chatName("Developers")
						.chatType(ChatRoom.ChatType.GROUP)
						.creator(usersRepository.findAllByEmail("test1@gmail.com"))
						.users(List.of(usersRepository.findAllByEmail("test1@gmail.com")))
						.build()
		);
		chatRoom.setChatName("Cars");
		ChatRoomDto chatRoomDto = chatRoomService.updateGroupChatRoom(chatRoom);
		Assertions.assertNotNull(chatRoomDto);
		Assertions.assertEquals(chatRoomDto.getChatName(),"Cars");
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void getOutChat(){
		ChatRoom chatRoom = chatRoomRepository.save(
				ChatRoom.builder()
						.chatTopic("")
						.chatName("Developers")
						.chatType(ChatRoom.ChatType.GROUP)
						.creator(usersRepository.findAllByEmail("test1@gmail.com"))
						.users(List.of(usersRepository.findAllByEmail("test1@gmail.com")))
						.build()
		);
		boolean status = chatRoomService.getOutOfChat(chatRoom.getId());
		Assertions.assertTrue(status);
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void deleteChat(){
		ChatRoom chatRoom = chatRoomRepository.save(
				ChatRoom.builder()
						.chatTopic("")
						.chatName("Developers")
						.chatType(ChatRoom.ChatType.GROUP)
						.creator(usersRepository.findAllByEmail("test1@gmail.com"))
						.users(List.of(usersRepository.findAllByEmail("test1@gmail.com")))
						.build()
		);
		boolean status = chatRoomService.deleteGroupChatRoom(chatRoom.getId());
		Assertions.assertTrue(status);
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void sendMessage(){
		MessageDto messageDto = messageService.sendMessage(null, RequestMessage.builder()
						.messageText("Hello, Bro!")
						.senderEmail("test1@gmail.com")
						.receiverEmail("test1@gmail.com")
				.build());
		Assertions.assertNotNull(messageDto);
		messageRepository.deleteAll();
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	public void getChatMessages(){
		ChatRoom chatRoom = chatRoomRepository.save(
				ChatRoom.builder()
						.chatTopic("")
						.chatName("Developers")
						.chatType(ChatRoom.ChatType.GROUP)
						.creator(usersRepository.findAllByEmail("test1@gmail.com"))
						.users(List.of(usersRepository.findAllByEmail("test1@gmail.com")))
						.build()
		);
		List<MessageDto> messageDtoList = messageService.getChatMessages(chatRoom.getId());
		Assertions.assertNotNull(messageDtoList);
		messageRepository.deleteAll();
		chatRoomRepository.deleteAll();
		usersRepository.deleteAll();
	}
}
