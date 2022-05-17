package com.example.users.utils;

import com.example.users.core.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTOUtil {

    public static UserDTO buildValidUserData() {
        return UserDTO
                .builder()
                .id(01)
                .username("teste")
                .password("teste")
                .email("teste@test.com")
                .build();
    }

    public static UserDTO buildInvalidUserData() {
        return UserDTO.builder().build();
    }

    public static List<UserDTO> buildValidUsersList() {
        List<UserDTO> list = new ArrayList<>();
        list.add(buildValidUserData());
        return list;
    }
}
