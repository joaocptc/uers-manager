package com.example.users.usecase.impl;

import com.example.users.adapters.mapper.UserMapper;
import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.FiltersDTO;
import com.example.users.core.dtos.UserDTO;
import com.example.users.core.entities.User;
import com.example.users.core.enums.ErrorsEnum;
import com.example.users.core.exceptions.InvalidUserDataException;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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
    public List<UserDTO> findUsers(final FiltersDTO filters) {
        var pageRequest = PageRequest.of(filters.getPage(), filters.getPageSize());

        var page = repository.findAll(pageRequest);

        return UserMapper.INSTANCE.parseList(page.getContent());
    }

    @Override
    public void updateUser(final UserDTO dto, final long id) {
        var userDB = repository.findById(id);

        if (userDB.isEmpty() || isNull(dto)) {
            throw new InvalidUserDataException(ErrorsEnum.INVALID_USER_DATA.getMessage());
        }

        var user = changeUserData(dto, id);
        repository.save(user);
    }

    private User changeUserData(final UserDTO dto, long id) {
        return User
                .builder()
                .id(id)
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
