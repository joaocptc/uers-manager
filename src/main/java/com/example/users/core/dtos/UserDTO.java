package com.example.users.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private long id;
    private String username;
    private String email;
    private String password;
}
