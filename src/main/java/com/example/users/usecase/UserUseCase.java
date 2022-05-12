package com.example.users.usecase;

import com.example.users.core.dtos.FiltersDTO;
import com.example.users.core.dtos.UserDTO;

import java.util.List;

public interface UserUseCase {

    UserDTO createUser(UserDTO dto);

    List<UserDTO> findUsers(FiltersDTO filters);

    void updateUser(UserDTO dto);
}
