package com.example.users.core.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private long id;
    private String username;
    private String email;
    private String password;
}
