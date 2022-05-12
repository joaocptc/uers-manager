package com.example.users.usecase.impl;

import com.example.users.adapters.mapper.UserMapper;
import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository repository;

    @Override
    public UserDTO createUser(final UserDTO dto) {
        var user = UserMapper.INSTANCE.toEntity(dto);
        return UserMapper.INSTANCE.toDTO(repository.save(user));
    }

    @Override
    public List<UserDTO> findUsers(final LocalDateTime minDate, final LocalDateTime maxDate) {
        return UserMapper.INSTANCE.parseList(repository.findAll());
    }

    @Override
    public void updateUser(UserDTO dto) {

    }

}
