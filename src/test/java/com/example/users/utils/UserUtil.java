package com.example.users.utils;

import com.example.users.core.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserUtil {

    public static User buildValidUserData() {
        return User
                .builder()
                .id(01)
                .username("teste")
                .password("teste")
                .email("teste@test.com")
                .build();
    }

    public static Optional<User> buildOptionalUserWithValidData() {
        return Optional.of(buildValidUserData());
    }

    public static User buildInvalidUserData() {
        return User.builder().build();
    }

    public static List<User> buildValidUsersList() {
        List<User> list = new ArrayList<>();
        list.add(buildValidUserData());
        return list;
    }
}
