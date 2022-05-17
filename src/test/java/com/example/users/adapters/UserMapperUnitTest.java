package com.example.users.adapters;

import com.example.users.adapters.mapper.UserMapper;
import com.example.users.utils.UserDTOUtil;
import com.example.users.utils.UserUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class UserMapperUnitTest {

    @Test
    public void testToEntityWithValidData () {
        var dto = UserDTOUtil.buildValidUserData();

        var user = UserMapper.INSTANCE.toEntity(dto);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getPassword(), user.getPassword());
    }

    @Test
    public void testToEntityWithInvalidData () {
        var dto = UserDTOUtil.buildInvalidUserData();

        var user = UserMapper.INSTANCE.toEntity(dto);

        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    public void testToEntityWithNull () {
        var user = UserMapper.INSTANCE.toEntity(null);

        assertNull(user);
    }

    @Test
    public void testToDTOWithValidData () {
        var user = UserUtil.buildValidUserData();

        var dto = UserMapper.INSTANCE.toDTO(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    public void testToDTOWithInvalidData () {
        var user = UserUtil.buildInvalidUserData();

        var dto = UserMapper.INSTANCE.toDTO(user);

        assertNull(dto.getEmail());
        assertNull(dto.getUsername());
        assertNull(dto.getPassword());
    }

    @Test
    public void testToDTOWithNull () {
        var dto = UserMapper.INSTANCE.toDTO(null);

        assertNull(dto);
    }

    @Test
    public void testToUsersListWithValidData () {
        var usersList = UserUtil.buildValidUsersList();

        var dtoList = UserMapper.INSTANCE.parseList(usersList);

        assertEquals(1, dtoList.size());

        var dto = dtoList.stream().findFirst().get();
        var user = usersList.stream().findFirst().get();

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getEmail(), dto.getEmail());
    }
}
