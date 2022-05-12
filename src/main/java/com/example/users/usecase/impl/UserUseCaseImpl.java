package com.example.users.usecase.impl;

import com.example.users.adapters.mapper.UserMapper;
import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.FiltersDTO;
import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    // TODO criar classe para fazer envelope da resposta

    @Override
    public List<UserDTO> findUsers(final FiltersDTO filters) {
        var pageRequest = PageRequest.of(filters.getPage(), filters.getPageSize());

        var page = repository.findAll(pageRequest);

        return UserMapper.INSTANCE.parseList(page.getContent());
    }

    @Override
    public void updateUser(UserDTO dto) {

    }


}
