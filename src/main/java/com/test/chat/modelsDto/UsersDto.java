package com.test.chat.modelsDto;
import com.test.chat.models.Permission;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersDto{
    private Long id;
    private String email;
    private String fullName;
    private List<Permission> roles;
}