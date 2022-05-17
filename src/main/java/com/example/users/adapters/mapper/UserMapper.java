package com.example.users.adapters.mapper;

import com.example.users.core.dtos.UserDTO;
import com.example.users.core.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);

    List<UserDTO> parseList(List<User> users);
}
