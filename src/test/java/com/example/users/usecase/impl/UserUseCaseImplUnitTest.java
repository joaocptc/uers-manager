package com.example.users.usecase.impl;

import com.example.users.adapters.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

public class UserUseCaseImplUnitTest {

    private UserUseCaseImpl userUseCase;
    private UserRepository repository;

    @BeforeEach
    public void init () {
        repository = mock(UserRepository.class);
        userUseCase = new UserUseCaseImpl(repository);
    }

}
