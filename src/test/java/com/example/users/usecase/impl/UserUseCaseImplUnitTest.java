package com.example.users.usecase.impl;

import com.example.users.adapters.repository.UserRepository;
import com.example.users.core.dtos.FiltersDTO;
import com.example.users.core.enums.ErrorsEnum;
import com.example.users.core.exceptions.InvalidUserDataException;
import com.example.users.utils.UserDTOUtil;
import com.example.users.utils.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserUseCaseImplUnitTest {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    private UserUseCaseImpl userUseCase;
    private UserRepository repository;

    @BeforeEach
    public void init () {
        repository = mock(UserRepository.class);
        userUseCase = new UserUseCaseImpl(repository);
    }

    @Test
    @DisplayName("Test create user with valid data")
    void testCreateUserWithValidData() {
        var user = UserUtil.buildValidUserData();

        when(repository.save(any())).thenReturn(user);
        var result = userUseCase.createUser(any());

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    @DisplayName("Test find users with valid data")
    void testFindUsersWithValidData() {
        var list = UserUtil.buildValidUsersList();
        var page = new PageImpl<>(list);
        var filters = buildFilters(null, null);
        var pageRequest = PageRequest.of(filters.getPage(), filters.getPageSize());

        when(repository.findAll(pageRequest)).thenReturn(page);
        var result = userUseCase.findUsers(filters);

        assertEquals(list.size(), result.size());

        var dto = result.stream().findFirst().get();
        var user = result.stream().findFirst().get();

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    @DisplayName("Test update user with valid data")
    void testUpdateUserWithValidUser() {
        var dto = UserDTOUtil.buildValidUserData();
        var optionalUser = UserUtil.buildOptionalUserWithValidData();

        when(repository.findById(any())).thenReturn(optionalUser);

        assertDoesNotThrow(() -> userUseCase.updateUser(dto, dto.getId()));
    }

    @Test
    @DisplayName("Test update user with invalid data")
    void testUpdateUserWithInvalidData() {
        var dto = UserDTOUtil.buildInvalidUserData();

        when(repository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(InvalidUserDataException.class, () -> {
            userUseCase.updateUser(dto, dto.getId());
        });

        assertEquals(ErrorsEnum.INVALID_USER_DATA.getMessage(), exception.getMessage());
    }

    private FiltersDTO buildFilters(final Integer page, final Integer pageSize) {
        return FiltersDTO
                .builder()
                .page(isNull(page) ? DEFAULT_PAGE : page)
                .pageSize(isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize)
                .build();
    }
}
