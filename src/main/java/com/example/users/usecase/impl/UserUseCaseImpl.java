package com.example.users.usecase.impl;

import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository repository;

    @Override
    public UserDTO createUser(final UserDTO dto) {
        return null;
    }

    @Override
    public Page<UserDTO> findUsers(final LocalDateTime minDate, final LocalDateTime maxDate) {
        return null;
    }

    @Override
    public void updateUser(UserDTO dto) {

    }

}
