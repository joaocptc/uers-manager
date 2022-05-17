package com.example.users.controllers;

import com.example.users.controllers.user.UserController;
import com.example.users.usecase.UserUseCase;
import com.example.users.utils.UserDTOUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerUnitTest {

    private UserController userController;
    private UserUseCase userUseCase;

    @BeforeEach
    public void init () {
        userUseCase = mock(UserUseCase.class);
        userController = new UserController(userUseCase);
    }

    @Test
    public void testSaveWithValidData () {
        var dto = UserDTOUtil.buildValidUserData();

        when(userUseCase.createUser(any())).thenReturn(dto);
        var response = userController.save(dto);

        var body = response.getBody();

        assertEquals(dto.getUsername(), body.getUsername());
        assertEquals(dto.getPassword(), body.getPassword());
        assertEquals(dto.getEmail(), body.getEmail());
    }

    @Test
    public void testFindWithPageAndPageSize () {
        var list = UserDTOUtil.buildValidUsersList();

        when(userUseCase.findUsers(any())).thenReturn(list);
        var response = userController.find(0, 5);

        var dtoList = response.getBody();

        var dto = dtoList.stream().findFirst().get();
        var user = list.stream().findFirst().get();

        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    public void testFindWithoutPageAndPageSize () {
        var list = UserDTOUtil.buildValidUsersList();

        when(userUseCase.findUsers(any())).thenReturn(list);
        var response = userController.find(null, null);

        var dtoList = response.getBody();

        var dto = dtoList.stream().findFirst().get();
        var user = list.stream().findFirst().get();

        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    public void testUpdateWithValidData () {
        var dto = UserDTOUtil.buildValidUserData();

        var response = userController.update(1, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
