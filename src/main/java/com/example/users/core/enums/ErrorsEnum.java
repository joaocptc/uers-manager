package com.example.users.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorsEnum {

    INVALID_USER_DATA("Dados do usuário incorretos");

    private String message;
}
