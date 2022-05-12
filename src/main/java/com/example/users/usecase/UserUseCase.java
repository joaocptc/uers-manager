package com.example.users.usecase;

import com.example.users.core.dtos.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface UserUseCase {

    UserDTO createUser(UserDTO dto);

    List<UserDTO> findUsers(LocalDateTime minDate, LocalDateTime maxDate);

    void updateUser(UserDTO dto);
}
