package com.example.users.usecase;

import com.example.users.core.dtos.UserDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface UserUseCase {

    UserDTO createUser(UserDTO dto);

    Page<UserDTO> findUsers(LocalDateTime minDate, LocalDateTime maxDate);

    void updateUser(UserDTO dto);
}
